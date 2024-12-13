package com.grkicbreport.service;

import com.grkicbreport.dto.CodeExtractor;
import com.grkicbreport.model.*;
import com.grkicbreport.repository.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileGeneratorService {

    private final KreditRepository kreditRepository;
    private final DokumRepository dokumRepository;
    private final DokRepository dokRepository;
    private final Analiz_schetService aliz_schetService;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final String[] balValues = {"12401", "12405", "12409", "12499", "12501", "14801", "14899", "15701"};
    private static final String FOLDER_PATH = "C:/Users/user/Desktop/GRKI"; // Укажите здесь вашу папку


    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Map<String, Integer> dailyFlightNumbers = new HashMap<>();
    private static final Logger logger = Logger.getLogger(FileGeneratorService.class.getName());

    public FileGeneratorService(KreditRepository kreditRepository, DokumRepository dokumRepository, DokRepository dokRepository, Analiz_schetService alizSchetService, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository) {
        this.kreditRepository = kreditRepository;
        this.dokumRepository = dokumRepository;
        this.dokRepository = dokRepository;
        aliz_schetService = alizSchetService;
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
    }


    // Генерируем следующий номер рейса (RR) на основе даты
    private String getNextFlightNumber(String date) {
        int flightNumber = dailyFlightNumbers.getOrDefault(date, 0) + 1;
        dailyFlightNumbers.put(date, flightNumber);

        return String.format("%02d", flightNumber);
    }

    // Генерация имени файла на основе даты и других параметров
    public String generateFilename(String date, String TTT) {
        String N = "N"; // Константа, идентификатор файла от АС кредитной организации
        String BBBBB = "07113"; // Код кредитной организации

        // Генерируем следующий номер рейса (RR)
        String RR = getNextFlightNumber(date);

        // Формируем название файла
        return N + BBBBB + "." + TTT;
    }

    private List<Analiz_schetDTO> convertToDTO(List<String> rawData) {
        List<Analiz_schetDTO> dtos = new ArrayList<>();

        for (String data : rawData) {
            // Предполагаем, что данные разделены запятой
            String[] parts = data.split(",");

            if (parts.length >= 4) {
                Analiz_schetDTO dto = new Analiz_schetDTO();

                // Присваиваем значения DTO из данных
                dto.setBal(parts[0].trim());        // Первое значение
                dto.setNamer(parts[1].trim());      // Второе значение
                dto.setDeb(new BigDecimal(parts[2].trim())); // Третье значение
                dto.setKred(new BigDecimal(parts[3].trim())); // Четвертое значение

                // Добавляем DTO в список
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public String createFiles(String date) {
        // Парсим строку даты в объект java.sql.Date
        Date currentDate;
        try {
            currentDate = Date.valueOf(date);  // Преобразование строки в java.sql.Date
        } catch (IllegalArgumentException e) {
            return "Неверный формат даты.";
        }

        char separator = '\u001D';

        // Получаем дату для предыдущего дня относительно указанной даты
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date previousDay = new Date(calendar.getTime().getTime());

        // Преобразуем даты в нужный формат yyyyMMdd
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outputSdfReverse = new SimpleDateFormat("ddMMyyyy");
        String dateString = outputSdf.format(currentDate);
        String dateStringReverse = outputSdfReverse.format(currentDate);
        String previousDateString = outputSdf.format(previousDay);

        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Создание файлов с разными расширениями
        String fileName008 = FOLDER_PATH + "/" + generateFilename(dateString, "008");
        String fileName009 = FOLDER_PATH + "/" + generateFilename(dateString, "009");

        // Создание и запись в файл с расширением .008
        try {
            BufferedWriter writer008 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName008), "windows-1251"));
            // Итерация по всем значениям bal
            for (String bal : balValues) {
                // Получаем сырые данные в виде List<String>
                List<String> rawDataList = aliz_schetService.Analiz_schet(currentDate, bal);
                // Преобразуем сырые данные в List<Analiz_schetDTO>
                List<Analiz_schetDTO> currentDataList = convertToDTO(rawDataList);
                // Печать для отладки
                System.out.println("Current Data List for BAL " + bal + ": " + currentDataList);

                List<String> prewDateRawDataList = aliz_schetService.Analiz_schet(previousDay, bal);
                List<Analiz_schetDTO> previousDataList = convertToDTO(prewDateRawDataList);
                // Печать для отладки
                System.out.println("Previous Data List for BAL " + bal + ": " + previousDataList);

                // Итерация по каждому элементу currentDataList
                for (Analiz_schetDTO record : currentDataList) {
                    // Извлекаем код из поля namer
                    String extractedCode = CodeExtractor.extractCode(record.getNamer());
                    if (extractedCode == null) {
                        // Пропуск записи, если код не найден
                        System.err.println("Код не найден для " + record.getNamer());
                        continue;
                    }

                    // Проверяем наличие записи в таблице Kredit
                    Optional<Kredit> kredit = kreditRepository.findByNumdog(extractedCode);
                    Kredit getGRKIId = kredit.orElse(null);

                    // Получаем данные о кредите и дебите
                    List<Dokument> ls = dokumRepository.getDokumentByLs(record.getBal());
                    List<Dokument> lscor = dokumRepository.getDokumentByLscor(record.getBal());

                    // Если данных нет, заполняем нулями
                    Dokument lsKredit = ls.isEmpty() ? null : ls.get(0);
                    Dokument lsDebit = lscor.isEmpty() ? null : lscor.get(0);

                    BigDecimal debitSum = (lsDebit != null && lsDebit.getSums() != null) ? lsDebit.getSums() : BigDecimal.ZERO;
                    BigDecimal kreditSum = (lsKredit != null && lsKredit.getSums() != null) ? lsKredit.getSums() : BigDecimal.ZERO;

                    // Находим значение дебета за предыдущий день
                    BigDecimal previousDayDeb = previousDataList.stream()
                            .filter(prevRecord -> extractedCode.equals(CodeExtractor.extractCode(prevRecord.getNamer())))
                            .map(Analiz_schetDTO::getDeb)
                            .findFirst()
                            .orElse(BigDecimal.ZERO);

                    if (!(getGRKIId == null)) {
                        String cleanedNumdog = getGRKIId.getNumdog().replaceAll("[-K\\\\]", "").trim();


                        // Формируем строку для записи
                        String line008 = dateStringReverse + separator +
                                "03" + separator +
                                "07113" + separator +
                                ((getGRKIId != null && getGRKIId.getGrkiContractId() != null) ? getGRKIId.getGrkiContractId() : "0") + separator +
                                cleanedNumdog + separator +
                                record.getBal() + separator +
                                previousDayDeb.intValue() + separator +
                                debitSum.intValue() + separator +
                                kreditSum.intValue() + separator +
                                record.getDeb().intValue() + separator + "\n";

                        // Записываем строку в файл с расширением .008
                        writer008.write(line008);
                        logger.info("Записана строка в .008 файл: " + line008);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при создании файла .008: " + e.getMessage();
        }

        // Создание и запись в файл с расширением .009
        try {
            BufferedWriter writer009 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName009), "windows-1251"));

            // Логика для записи данных в файл .009
            List<Dok> dokList = dokRepository.findAllByDatProv(currentDate);
            for (Dok dok : dokList) {
                String extractedCode = CodeExtractor.extractCode(dok.getNazn());

                if (extractedCode == null) {
                    // Пропуск записи, если код не найден
                    System.err.println("Код не найден для " + dok.getNazn());
                    continue;
                }
                kreditRepository.findByNumdog(extractedCode).ifPresent(kredit -> {
                    Optional<AzolikFiz> azolikFiz = azolikFizRepository.findByKodchlen(kredit.getKod());
                    Optional<AzolikYur> azolikYur = azolikYurRepository.findByKodchlen(kredit.getKod());

                    AzolikFiz fiz = azolikFiz.orElse(null);
                    AzolikYur yur = azolikYur.orElse(null);

                    if (dok.getNazn().startsWith("Выдача")) {

                        if (fiz == null) {
                            String line009 = dateStringReverse + separator +
                                    "03" + separator +
                                    "07113" + separator +
                                    ((kredit != null && kredit.getGrkiClaimId() != null) ? kredit.getGrkiClaimId() : "0") + separator +
                                    extractedCode + separator +
                                    dok.getKod() + separator +
                                    "0103" + separator +
                                    "1" + separator +
                                    "3" + separator +
                                    dok.getNumdok() + separator +
                                    "119" + separator +
                                    dok.getLs() + separator +
                                    "119" + separator +
                                    dok.getLscor() + separator +
                                    dok.getSums() + separator +
                                    "Star Kredit Lombard MCHJ" + separator +
                                    yur.getName() + separator +
                                    dok.getLs().substring(0, 5) + separator +
                                    dok.getNazn();


                            // Записываем строку в файл с расширением .009
                            try {
                                writer009.write(line009);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            logger.info("Записана строка в .009 файл: " + line009);
                        } else {
                            String line009 = dateStringReverse + separator +
                                    "03" + separator +
                                    "07113" + separator +
                                    ((kredit != null && kredit.getGrkiClaimId() != null) ? kredit.getGrkiClaimId() : "0") + separator +
                                    extractedCode + separator +
                                    dok.getKod() + separator +
                                    "0103" + separator +
                                    "1" + separator +
                                    "3" + separator +
                                    dok.getNumdok() + separator +
                                    "119" + separator +
                                    dok.getLs() + separator +
                                    "119" + separator +
                                    dok.getLscor() + separator +
                                    dok.getSums() + separator +
                                    "Star Kredit Lombard MCHJ" + separator +
                                    fiz.getName() + separator +
                                    dok.getLs().substring(0, 5) + separator +
                                    dok.getNazn();


                            // Записываем строку в файл с расширением .009
                            try {
                                writer009.write(line009);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            logger.info("Записана строка в .009 файл: " + line009);
                        }
                    } else if (dok.getNazn().startsWith("Погашение")) {

                        String nalCard = "";
                        String typeOption = "";
                        if (dok.getLscor().startsWith("10509")) {
                            nalCard = "3";
                        } else {
                            nalCard = "1";
                        }

                        if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10509")) {
                            typeOption = "0300";
                        } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10101")) {
                            typeOption = "0303";
                        } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10101")) {
                            typeOption = "0305";
                        } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10509")) {
                            typeOption = "0307";
                        } else if (dok.getLs().startsWith("12409")) {
                            typeOption = "0312";
                        } else if (dok.getLs().startsWith("12501")) {
                            typeOption = "0313";
                        } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10101")) {
                            typeOption = "0403";
                        } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10509")) {
                            typeOption = "0400";
                        } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10101")) {
                            typeOption = "0419";
                        } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10509")) {
                            typeOption = "0400";
                        } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("16377")) {
                            typeOption = "0407";
                        }

                        if (fiz == null) {
                            String line009 = dateStringReverse + separator +
                                    "03" + separator +
                                    "07113" + separator +
                                    ((kredit != null && kredit.getGrkiClaimId() != null) ? kredit.getGrkiClaimId() : "0") + separator +
                                    extractedCode + separator +
                                    dok.getKod() + separator +
                                    typeOption + separator +
                                    nalCard + separator +
                                    "3" + separator +
                                    dok.getNumdok() + separator +
                                    "119" + separator +
                                    dok.getLscor() + separator +
                                    "119" + separator +
                                    dok.getLs() + separator +
                                    dok.getSums() + separator +
                                    yur.getName() + separator +
                                    "Star Kredit Lombard MCHJ" + separator +
                                    dok.getLs().substring(0, 5) + separator +
                                    dok.getNazn();


                            // Записываем строку в файл с расширением .009
                            try {
                                writer009.write(line009);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Записана строка в .009 файл: " + line009);
                        } else {
                            String line009 = dateStringReverse + separator +
                                    "03" + separator +
                                    "07113" + separator +
                                    ((kredit != null && kredit.getGrkiClaimId() != null) ? kredit.getGrkiClaimId() : "0") + separator +
                                    extractedCode + separator +
                                    dok.getKod() + separator +
                                    typeOption + separator +
                                    nalCard + separator +
                                    "3" + separator +
                                    dok.getNumdok() + separator +
                                    "119" + separator +
                                    dok.getLs() + separator +
                                    "119" + separator +
                                    dok.getLscor() + separator +
                                    dok.getSums() + separator +
                                    "Star Kredit Lombard MCHJ" + separator +
                                    fiz.getName() + separator +
                                    dok.getLs().substring(0, 5) + separator +
                                    dok.getNazn();


                            // Записываем строку в файл с расширением .009
                            try {
                                writer009.write(line009);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("Записана строка в .009 файл: " + line009);
                        }
                    }
                });



            }

        } catch (IOException e) {
            // Получаем стек вызовов
            StackTraceElement[] stackTrace = e.getStackTrace();

            // Формируем сообщение об ошибке с информацией о строке
            StringBuilder errorMessage = new StringBuilder("Ошибка при создании файла .009: " + e.getMessage());
            if (stackTrace.length > 0) {
                StackTraceElement firstElement = stackTrace[0];
                errorMessage.append(" В классе ").append(firstElement.getClassName())
                        .append(", методе ").append(firstElement.getMethodName())
                        .append(", строке ").append(firstElement.getLineNumber());
            }

            // Логируем сообщение об ошибке
            logger.info(errorMessage.toString());
        }

        String zipFileName = generateZipFileName(dateString); // Генерируем имя архива
        String zipFilePath = FOLDER_PATH + "/" + zipFileName + ".zip";
        String zipFilePathWithoutExtension = FOLDER_PATH + "/" + zipFileName; // Архив без расширения

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            // Добавляем файл .008 в архив
            addFileToZip(fileName008, zipOut);

            // Добавляем файл .009 в архив
            addFileToZip(fileName009, zipOut);

        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при создании архива: " + e.getMessage();
        }

// Создаем копию архива без расширения
        try {
            Files.copy(Paths.get(zipFilePath), Paths.get(zipFilePathWithoutExtension), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при создании копии архива без расширения: " + e.getMessage();
        }

        return "Файлы созданы и заархивированы: " + zipFilePath + " и " + zipFilePathWithoutExtension;

    }


    // Метод для генерации имени архива в формате NBBBBBRR.YMD
    private String generateZipFileName(String dateString) {
        // N = Константа (например, 'N')
        String N = "N";
        // BBBBB = Код кредитной организации (например, '06005')
        String BBBBB = "07113";
        // RR = Номер рейса (например, '01')
        String RR = "01";

        // Преобразование даты из строки в LocalDate
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Y = Год, преобразованный в буквенный формат (A = 2010, B = 2011, ...)
        char Y = (char) ('A' + (date.getYear() - 2010));

        // M = Месяц, преобразованный в буквенно-числовой формат (1–9, A = 10, B = 11, C = 12)
        String M = date.getMonthValue() <= 9
                ? String.valueOf(date.getMonthValue()) // 1-9 остаются числами
                : String.valueOf((char) ('A' + date.getMonthValue() - 10)); // 10 = A, 11 = B, 12 = C

        // D = День, преобразованный в буквенно-числовой формат (1–9, A = 10, ..., V = 31)
        String D = date.getDayOfMonth() <= 9
                ? String.valueOf(date.getDayOfMonth()) // 1-9 остаются числами
                : String.valueOf((char) ('A' + date.getDayOfMonth() - 10)); // 10 = A, ..., 31 = V

        // YMD = Собираем Y, M и D
        String YMD = "" + Y + M + D;

        return N + BBBBB + RR + "." + YMD;
    }

    // Метод для добавления файла в ZIP-архив
    private void addFileToZip(String filePath, ZipOutputStream zipOut) throws IOException {
        File fileToZip = new File(filePath);
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

}


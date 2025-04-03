package com.grkicbreport.service;

import com.grkicbreport.dto.CbOtchDTO;
import com.grkicbreport.dto.CodeExtractor;
import com.grkicbreport.model.*;
import com.grkicbreport.repository.*;
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

import com.grkicbreport.component.InformHelper;


@Service
public class FileGeneratorService {

    private final KreditRepository kreditRepository;
    private final DokRepository dokRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final InformHelper informHelper;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Map<String, Integer> dailyFlightNumbers = new HashMap<>();
    private static final Logger logger = Logger.getLogger(FileGeneratorService.class.getName());

    public FileGeneratorService(KreditRepository kreditRepository, DokRepository dokRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository, com.grkicbreport.component.InformHelper informHelper) {
        this.kreditRepository = kreditRepository;
        this.dokRepository = dokRepository;
        this.azolikFizRepository = azolikFizRepository;
        this.azolikYurRepository = azolikYurRepository;
        this.informHelper = informHelper;
    }


    // Генерируем следующий номер рейса (RR) на основе даты
    private String getNextFlightNumber(String date) {
        int flightNumber = dailyFlightNumbers.getOrDefault(date, 0) + 1;
        dailyFlightNumbers.put(date, flightNumber);

        return String.format("%02d", flightNumber);
    }

    // Генерация имени файла на основе даты и других параметров
    public String generateFilename(String date, String TTT) {
        Inform inform = informHelper.fetchSingleRow();
        String N = "N"; // Константа, идентификатор файла от АС кредитной организации
        String BBBBB = inform.getNumks(); // Код кредитной организации

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
        Inform inform = informHelper.fetchSingleRow();

        final String FOLDER_PATH = inform.getGrki_file_path(); // Укажите здесь вашу папку

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
            List<String> cb_otch = kreditRepository.cb_otch(previousDay, currentDate);
            List<CbOtchDTO> resultList = new ArrayList<>();
            // Итерация по всем значениям bal
            for (String record : cb_otch) {
                // Убираем завершающие символы, если необходимо
                record = record.replace(",,", "");
                // Разбиваем строку по символу "#"
                String[] parts = record.split("#");

                // Проверяем, что после 3-й решетки (индекс 3) значение начинается с "12401"
                if (parts.length > 9 && parts[3].startsWith("12401")) {
                    CbOtchDTO dto = new CbOtchDTO();
                    dto.setAccount(parts[3]);       // например, "12401000999000969001"
                    dto.setPrev_amount(parts[6]);   // "-1000000000"
                    dto.setDeb(parts[7]);   // "0"
                    dto.setKred(parts[8]);   // "0"
                    dto.setCurrent_amount(parts[9]);   // "-1000000000"
                    resultList.add(dto);
                }
            }
            // Обрабатываем каждую строку из resultList
            for (CbOtchDTO dto : resultList) {
                // По номеру кредита (account) получаем объект Kredit
                Optional<Kredit> creditOpt = kreditRepository.findKreditByLskred(dto.getAccount());
                if (creditOpt.isPresent()) {
                    Kredit kredit = creditOpt.get();
                    // Предполагаем, что у объекта Kredit есть метод getGRKIId() и он возвращает нужный объект
                    if (kredit.getGrkiContractId() != null) {
                        // Очищаем номер договора от нежелательных символов
                        String cleanedNumdog = kredit.getNumdog()
                                .replaceAll("[-KК/\\\\]", "")
                                .trim();

                        // Формируем строку для записи.
                        String line008 = dateStringReverse + separator +
                                "02" + separator +
                                inform.getNumks() + separator +
                                (kredit.getGrkiContractId() != null ? kredit.getGrkiContractId() : "0") + separator +
                                cleanedNumdog + separator +
                                dto.getAccount() + separator +    // Предполагается, что метод getBal() существует в CbOtchDTO
                                dto.getPrev_amount().replace("-", "") + separator +
                                dto.getDeb().replace("-", "") + separator +
                                dto.getKred().replace("-", "") + separator +
                                dto.getCurrent_amount().replace("-", "") + separator + "\n";

                        // Записываем строку в файл
                        writer008.write(line008);
                        writer008.flush();
                        logger.info("Записана строка в .008 файл: " + line008);
                    } else {
                        logger.info("У кредита с номером " + dto.getAccount() + " отсутствует GRKIId");
                    }
                } else {
                    logger.info("Кредит с номером " + dto.getAccount() + " не найден");
                }
            }
        } catch (Exception e) {
            logger.info("Ошибка при обработке .008 файла " + e);
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
                    String cleanedNumdog = extractedCode.replaceAll("[-KК/\\\\]", "").trim();

                    String lsKod = "";

                    // dic 060 -> 01007
                    if (dok.getLs().startsWith("10101") && dok.getLscor().startsWith("12401")) {
                        lsKod = "01007";
                    } else if (dok.getLs().startsWith("10503") && dok.getLscor().startsWith("12401")) {
                        lsKod = "01007";
                    } else if (dok.getLs().startsWith("10101") && dok.getLscor().startsWith("14801")) {
                        lsKod = "01007";
                    } else if (dok.getLs().startsWith("10503") && dok.getLscor().startsWith("14801")) {
                        lsKod = "01007";
                    } else if (dok.getLs().startsWith("10503") && dok.getLscor().startsWith("12501")) {
                        lsKod = "01007";
                    }
                    // dic 060 -> 01008
                    else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14901") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("12409") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14809") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    } else if (dok.getLs().startsWith("14901") && dok.getLscor().startsWith("22812")) {
                        lsKod = "01008";
                    }
                    // dic 060 -> 01009
                    else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12405")) {
                        lsKod = "01009";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("12405")) {
                        lsKod = "01009";
                    } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("12405")) {
                        lsKod = "01009";
                    }
                    // dic 060 -> 01010
                    else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12409")) {
                        lsKod = "01010";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("12409")) {
                        lsKod = "01010";
                    } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("12409")) {
                        lsKod = "01010";
                    } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("12409")) {
                        lsKod = "01010";
                    }
                    // dic 060 -> 01011
                    else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("15701")) {
                        lsKod = "01011";
                    }
                    // dic 060 -> 01012
                    else if (dok.getLs().startsWith("12499") && dok.getLscor().startsWith("56802")) {
                        lsKod = "01012";
                    }
                    // dic 060 -> 01013
                    else if (dok.getLs().startsWith("96345") && dok.getLscor().startsWith("95413")) {
                        lsKod = "01013";
                    }
                    // dic 060 -> 01014
                    else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16307")) {
                        lsKod = "01014";
                    } else if (dok.getLs().startsWith("42005") && dok.getLscor().startsWith("16377")) {
                        lsKod = "01014";
                    }
                    // dic 060 -> 01015
                    else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16309") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01015";
                    } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01015";
                    }
                    // dic 060 -> 01016
                    else if (dok.getLs().startsWith("96335") && dok.getLscor().startsWith("91501")) {
                        lsKod = "01016";
                    }
                    // dic 060 -> 01017
                    else if (dok.getLs().startsWith("91501") && dok.getLscor().startsWith("96335")) {
                        lsKod = "01017";
                    }
                    // dic 060 -> 01018
                    else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("16377")) {
                        lsKod = "01018";
                    }
                    // dic 060 -> 01019
                    else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10101")) {
                        lsKod = "01019";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10503")) {
                        lsKod = "01019";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10509")) {
                        lsKod = "01019";
                    }
                    // dic 060 -> 01020
                    else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("42001")) {
                        lsKod = "01020";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("42005")) {
                        lsKod = "01020";
                    }

                    if (!((dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10509") ||
                            (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10509") ||
                                    (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10509") ||
                                            (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10101"))))))) {
                        if (dok.getNazn().startsWith("Выдача")) {

                            if (fiz == null) {
                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((kredit != null && kredit.getGrkiContractId() != null) ? kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        "0103" + separator +
                                        "1" + separator +
                                        "03" + separator +
                                        dok.getNumdok().replaceAll(" ", "") + separator +
                                        inform.getNumks() + separator +
                                        dok.getLscor() + separator +
                                        inform.getNumks() + separator +
                                        dok.getLs() + separator +
                                        dok.getSums().intValue() + "00" + separator +
                                        inform.getName() + separator +
                                        yur.getName() + separator +
                                        lsKod + separator +
                                        dok.getNazn() + separator + "\n";


                                // Записываем строку в файл с расширением .009
                                try {
                                    writer009.write(line009);
                                    writer009.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                logger.info("Записана строка в .009 файл: " + line009);
                            } else {

                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((kredit != null && kredit.getGrkiContractId() != null) ? kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        "0103" + separator +
                                        "1" + separator +
                                        "03" + separator +
                                        dok.getNumdok().replaceAll(" ", "") + separator +
                                        inform.getNumks() + separator +
                                        dok.getLscor() + separator +
                                        inform.getNumks() + separator +
                                        dok.getLs() + separator +
                                        dok.getSums().intValue() + "00" + separator +
                                        inform.getName() + separator +
                                        fiz.getName() + separator +
                                        lsKod + separator +
                                        dok.getNazn() + separator + "\n";


                                // Записываем строку в файл с расширением .009
                                try {
                                    writer009.write(line009);
                                    writer009.flush();
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
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0301";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("12409") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("14809") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("14901") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("14901") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0313";
                            } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0313";
                            } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0307";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0306";
                            } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0315";
                            } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0313";
                            } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0313";
                            } else if (dok.getLs().startsWith("12409")) {
                                typeOption = "0312";
                            } else if (dok.getLs().startsWith("12501")) {
                                typeOption = "0313";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0403";
                            } else if (dok.getLs().startsWith("16309") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0403";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0401";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0401";
                            } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0405";
                            } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0419";
                            } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0417";
                            } else if (dok.getLs().startsWith("16405") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0417";
                            } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0407";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("16377")) {
                                typeOption = "0912";
                            }


                            if (fiz == null) {
                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((kredit != null && kredit.getGrkiContractId() != null) ? kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        typeOption + separator +
                                        nalCard + separator +
                                        "03" + separator +
                                        dok.getNumdok().replaceAll(" ", "") + separator +
                                        inform.getNumks() + separator +
                                        dok.getLscor() + separator +
                                        inform.getNumks() + separator +
                                        dok.getLs() + separator +
                                        dok.getSums().intValue() + "00" + separator +
                                        yur.getName() + separator +
                                        inform.getName() + separator +
                                        lsKod + separator +
                                        dok.getNazn() + separator + "\n";


                                // Записываем строку в файл с расширением .009
                                try {
                                    writer009.write(line009);
                                    writer009.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Записана строка в .009 файл: " + line009);
                            } else {
                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((kredit != null && kredit.getGrkiContractId() != null) ? kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        typeOption + separator +
                                        nalCard + separator +
                                        "03" + separator +
                                        dok.getNumdok().replaceAll(" ", "") + separator +
                                        inform.getNumks() + separator +
                                        dok.getLscor() + separator +
                                        inform.getNumks() + separator +
                                        dok.getLs() + separator +
                                        dok.getSums().intValue() + "00" + separator +
                                        inform.getName() + separator +
                                        fiz.getName() + separator +
                                        lsKod + separator +
                                        dok.getNazn() + separator + "\n";


                                // Записываем строку в файл с расширением .009
                                try {
                                    writer009.write(line009);
                                    writer009.flush();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println("Записана строка в .009 файл: " + line009);
                            }

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
        Inform inform = informHelper.fetchSingleRow();

        // N = Константа (например, 'N')
        String N = "N";
        // BBBBB = Код кредитной организации (например, '06005')
        String BBBBB = inform.getNumks(); // Код кредитной организации

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

        int flightNumber = 1;

        String zipFileName;
        File file;

        final String FOLDER_PATH = inform.getGrki_file_path(); // Укажите здесь вашу папку

        do {
            // Формируем строку RR (номер рейса)
            String RR = String.format("%02d", flightNumber); // Делаем двухзначный формат, например, '01'
            // Генерируем имя файла
            zipFileName = N + BBBBB + RR + "." + YMD;
            // Проверяем, существует ли файл
            file = new File(FOLDER_PATH, zipFileName);
            flightNumber++; // Увеличиваем номер рейса
        } while (file.exists());

        return zipFileName;
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


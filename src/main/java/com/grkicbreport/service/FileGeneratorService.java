package com.grkicbreport.service;

import com.grkicbreport.dto.CodeExtractor;
import com.grkicbreport.model.*;
import com.grkicbreport.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileGeneratorService {

    private final KreditRepository kreditRepository;
    private final DokumRepository dokumRepository;
    private final DokRepository dokRepository;
    private final Analiz_schetService aliz_schetService;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final String[] balValues = {"12401", "12405", "12409", "12499", "12501", "14801", "14899", "15701"};

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Map<String, Integer> dailyFlightNumbers = new HashMap<>();

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
        String N = "A"; // Константа, идентификатор файла от АС кредитной организации
        String BBBBB = "6005"; // Код кредитной организации

        // Генерируем следующий номер рейса (RR)
        String RR = getNextFlightNumber(date);

        // Формируем название файла
        return N + BBBBB + RR + "." + TTT;
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
        java.sql.Date currentDate;
        try {
            currentDate = java.sql.Date.valueOf(date);  // Преобразование строки в java.sql.Date
        } catch (IllegalArgumentException e) {
            return "Неверный формат даты.";
        }

        // Получаем дату для предыдущего дня относительно указанной даты
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        java.sql.Date previousDay = new java.sql.Date(calendar.getTime().getTime());

        // Преобразуем даты в нужный формат yyyyMMdd
        SimpleDateFormat outputSdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = outputSdf.format(currentDate);
        String previousDateString = outputSdf.format(previousDay);

        // Создание файлов с разными расширениями
        String fileName008 = generateFilename(dateString, "008");
        String fileName009 = generateFilename(dateString, "009");

        // Создание и запись в файл с расширением .008
        try (BufferedWriter writer008 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName008), "windows-1251"))) {
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

                    // Формируем строку для записи
                    String line008 = dateString + "↔" +
                            "02" + "↔" +
                            "6005" + "↔" +
                            ((getGRKIId != null && getGRKIId.getGrkiClaimId() != null) ? getGRKIId.getGrkiClaimId() : "0") + "↔" +
                            extractedCode + "↔" +
                            record.getBal() + "↔" +
                            previousDayDeb + "↔" +
                            debitSum + "↔" +
                            kreditSum + "↔" +
                            record.getDeb() + "\n";

                    // Записываем строку в файл с расширением .008
                    writer008.write(line008);
                    System.out.println("Записана строка в .008 файл: " + line008);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при создании файла .008: " + e.getMessage();
        }

        // Создание и запись в файл с расширением .009
        try (BufferedWriter writer009 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName009), "windows-1251"))) {
            // Логика для записи данных в файл .009
            List<Dok> dokList = dokRepository.findAllByDatProv(currentDate);
            for (Dok dok : dokList) {
                String extractedCode = CodeExtractor.extractCode(dok.getNazn());

                if (extractedCode == null) {
                    // Пропуск записи, если код не найден
                    System.err.println("Код не найден для " + dok.getNazn());
                    continue;
                }
                Optional<Kredit> kredit = kreditRepository.findByNumdog(extractedCode);
                Kredit getGRKIId = kredit.orElse(null);
                Optional<AzolikFiz> azolikFiz = azolikFizRepository.findByKodchlen(getGRKIId.getKod());
                Optional<AzolikYur> azolikYur = azolikYurRepository.findByKodchlen(getGRKIId.getKod());
                AzolikFiz fiz = azolikFiz.orElse(null);
                AzolikYur yur = azolikYur.orElse(null);

                if (dok.getNazn().startsWith("Выдача")) {

                    if (fiz == null) {
                        String line009 = dateString + "↔" +
                                "02" + "↔" +
                                "6005" + "↔" +
                                ((getGRKIId != null && getGRKIId.getGrkiClaimId() != null) ? getGRKIId.getGrkiClaimId() : "0") + "↔" +
                                extractedCode + "↔" +
                                dok.getKod() + "↔" +
                                //dok.7
                                //8
                                //9
                                dok.getNumdok() + "↔" +
                                "119" + "↔" +
                                dok.getLs() + "↔" +
                                "119" + "↔" +
                                dok.getLscor() + "↔" +
                                dok.getSums() + "↔" +
                                "KAFOLATLI SARMOYA MIKROMOLIYA TASHKILOTI" + "↔" +
                                yur.getName() + "↔" +
                                //18 +
                                dok.getNazn();


                        // Записываем строку в файл с расширением .009
                        writer009.write(line009);
                        System.out.println("Записана строка в .009 файл: " + line009);
                    } else {
                        String line009 = dateString + "↔" +
                                "02" + "↔" +
                                "6005" + "↔" +
                                ((getGRKIId != null && getGRKIId.getGrkiClaimId() != null) ? getGRKIId.getGrkiClaimId() : "0") + "↔" +
                                extractedCode + "↔" +
                                dok.getKod() + "↔" +
                                //dok.7
                                //8
                                //9
                                dok.getNumdok() + "↔" +
                                "119" + "↔" +
                                dok.getLs() + "↔" +
                                "119" + "↔" +
                                dok.getLscor() + "↔" +
                                dok.getSums() + "↔" +
                                "KAFOLATLI SARMOYA MIKROMOLIYA TASHKILOTI" + "↔" +
                                fiz.getName() + "↔" +
                                //18 +
                                dok.getNazn();


                        // Записываем строку в файл с расширением .009
                        writer009.write(line009);
                        System.out.println("Записана строка в .009 файл: " + line009);
                    }
                } else if (dok.getNazn().startsWith("Погашение")) {

                    if (fiz == null) {
                        String line009 = dateString + "↔" +
                                "02" + "↔" +
                                "6005" + "↔" +
                                ((getGRKIId != null && getGRKIId.getGrkiClaimId() != null) ? getGRKIId.getGrkiClaimId() : "0") + "↔" +
                                extractedCode + "↔" +
                                dok.getKod() + "↔" +
                                //dok.7
                                //8
                                //9
                                dok.getNumdok() + "↔" +
                                "119" + "↔" +
                                dok.getLscor() + "↔" +
                                "119" + "↔" +
                                dok.getLs() + "↔" +
                                dok.getSums() + "↔" +
                                yur.getName() + "↔" +
                                "KAFOLATLI SARMOYA MIKROMOLIYA TASHKILOTI" + "↔" +
                                //18 +
                                dok.getNazn();


                        // Записываем строку в файл с расширением .009
                        writer009.write(line009);
                        System.out.println("Записана строка в .009 файл: " + line009);
                    } else {
                        String line009 = dateString + "↔" +
                                "02" + "↔" +
                                "6005" + "↔" +
                                ((getGRKIId != null && getGRKIId.getGrkiClaimId() != null) ? getGRKIId.getGrkiClaimId() : "0") + "↔" +
                                extractedCode + "↔" +
                                dok.getKod() + "↔" +
                                //dok.7
                                //8
                                //9
                                dok.getNumdok() + "↔" +
                                "119" + "↔" +
                                dok.getLs() + "↔" +
                                "119" + "↔" +
                                dok.getLscor() + "↔" +
                                dok.getSums() + "↔" +
                                "KAFOLATLI SARMOYA MIKROMOLIYA TASHKILOTI" + "↔" +
                                fiz.getName() + "↔" +
                                //18 +
                                dok.getNazn();


                        // Записываем строку в файл с расширением .009
                        writer009.write(line009);
                        System.out.println("Записана строка в .009 файл: " + line009);
                    }
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при создании файла .009: " + e.getMessage();
        }

        return "Файлы созданы: " + fileName008 + " и " + fileName009;
    }

}


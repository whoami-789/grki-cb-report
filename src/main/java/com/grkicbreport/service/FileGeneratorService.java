package com.grkicbreport.service;

import com.grkicbreport.components.InformHelper;
import com.grkicbreport.dto.CbOtchDTO;
import com.grkicbreport.dto.DokWithSource;
import com.grkicbreport.model.*;
import com.grkicbreport.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.persistence.Query;


@Service
public class FileGeneratorService {

    private final KreditRepository kreditRepository;
    private final DokRepository dokRepository;
    private final AzolikFizRepository azolikFizRepository;
    private final AzolikYurRepository azolikYurRepository;
    private final InformHelper informHelper;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Map<String, Integer> dailyFlightNumbers = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(FileGeneratorService.class);

    public FileGeneratorService(KreditRepository kreditRepository, DokRepository dokRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository, InformHelper informHelper) {
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

    public Optional<Kredit> byls_kred(String lskred) {
        // Очистка кэша сессии перед запросом
        entityManager.clear();


        // Поля, по которым будем искать
        List<String> fields = List.of(
                "lskred",
                "lsprosr_kred",
                "lssud_kred",
                "lsproc",
                "lsprocvne",
                "ls_spiskred",
                "lsprosr_proc",
                "lsrezerv"
        );

        // Сначала ищем по полному номеру счета
        for (String field : fields) {
            String sql = "SELECT * FROM kredit WHERE " + field + " = :lskred";
            Query query = entityManager.createNativeQuery(sql, Kredit.class);
            query.setParameter("lskred", lskred);

            try {
                Kredit kredit = (Kredit) query.getSingleResult();

                return Optional.of(kredit);
            } catch (NoResultException e) {
                // Продолжаем поиск
            } catch (Exception e) {
                logger.warn("Ошибка при поиске кредита по полю " + field + ": " + e.getMessage());
            }
        }

        // Если не нашли по полному номеру, пробуем найти по сокращенному (первые 5 цифр + суффикс)
        if (lskred != null && lskred.length() >= 11) {
            String suffix = lskred.substring(lskred.length() - 11);
            String altPrefix = lskred.startsWith("12499") ? "15799" : lskred.startsWith("15799") ? "12499" : null;

            if (altPrefix != null) {
                String likePattern = altPrefix + "%" + suffix;

                for (String field : fields) {
                    String sql = "SELECT * FROM kredit WHERE " + field + " LIKE :likePattern";
                    Query query = entityManager.createNativeQuery(sql, Kredit.class);
                    query.setParameter("likePattern", likePattern);

                    try {
                        Kredit kredit = (Kredit) query.getSingleResult();
                        return Optional.of(kredit);
                    } catch (NoResultException e) {
                        // Продолжаем поиск
                    } catch (Exception e) {
                        logger.warn("Ошибка при поиске по шаблону " + likePattern + " в поле " + field, e);
                    }
                }
            }
        }

        logger.warn("Кредит не найден ни по основному номеру " + lskred + ", ни по альтернативным вариантам");
        return Optional.empty();
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

        String baseFileName = generateZipFileName(dateString).replaceAll("\\.[^.]+$", ""); // удаляем расширение
        String excelFileName = FOLDER_PATH + "/" + baseFileName + ".xlsx";
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Report");

        // Заголовки
        Row headerRow = sheet.createRow(0);
        String[] headers = new String[]{
                "Дата", "Тип", "Numks", "GRKI ID", "Номер договора",
                "Счет", "Входящий остаток", "Дебет", "Кредит", "Выходящий остаток"
        };
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        int rowNum = 1; // Начиная со второй строки

        // Создание и запись в файл с расширением .008
        try {
            BufferedWriter writer008 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName008), "windows-1251"));
            List<String> cb_otch = kreditRepository.cb_otch(currentDate, currentDate);
            List<CbOtchDTO> resultList = new ArrayList<>();
            List<CbOtchDTO> allWrittenRecords = new ArrayList<>();


            // Для агрегации сумм по счетам (оригинальная логика)
            Map<String, CbOtchDTO> accountSums = new LinkedHashMap<>();
            // Для агрегации сумм по типам счетов (первые 5 цифр)
            Map<String, CbOtchDTO> accountTypeSums = new LinkedHashMap<>();

            // Итерация по всем значениям bal
            for (String record : cb_otch) {
                try {
                    record = record.replace(",,", "");
                    String[] parts = record.split("#");

                    if (parts.length > 9 && (parts[3].startsWith("12401") || parts[3].startsWith("16307")
                            || parts[3].startsWith("16377"))) {

                        String account = parts[3];
                        String accountType = account.length() >= 5 ? account.substring(0, 5) : account;

                        // Обработка для оригинальной логики (по полным номерам счетов)
                        CbOtchDTO aggregatedDto = accountSums.get(account);
                        if (aggregatedDto == null) {
                            aggregatedDto = new CbOtchDTO();
                            aggregatedDto.setAccount(account);
                            aggregatedDto.setPrev_amount("0");
                            aggregatedDto.setDeb("0");
                            aggregatedDto.setKred("0");
                            aggregatedDto.setCurrent_amount("0");
                            accountSums.put(account, aggregatedDto);
                        }

                        // Обработка для агрегации по типам счетов
                        CbOtchDTO typeAggregatedDto = accountTypeSums.get(accountType);
                        if (typeAggregatedDto == null) {
                            typeAggregatedDto = new CbOtchDTO();
                            typeAggregatedDto.setAccount(accountType);
                            typeAggregatedDto.setPrev_amount("0");
                            typeAggregatedDto.setDeb("0");
                            typeAggregatedDto.setKred("0");
                            typeAggregatedDto.setCurrent_amount("0");
                            accountTypeSums.put(accountType, typeAggregatedDto);
                        }

                        BigDecimal amount6 = new BigDecimal(parts[6]);
                        BigDecimal amount7 = new BigDecimal(parts[7]);
                        BigDecimal amount8 = new BigDecimal(parts[8]);
                        BigDecimal amount9 = new BigDecimal(parts[9]);

                        // Суммируем значения для оригинальной логики
                        aggregatedDto.setPrev_amount(new BigDecimal(aggregatedDto.getPrev_amount()).add(amount6).toString());
                        aggregatedDto.setDeb(new BigDecimal(aggregatedDto.getDeb()).add(amount7).toString());
                        aggregatedDto.setKred(new BigDecimal(aggregatedDto.getKred()).add(amount8).toString());
                        aggregatedDto.setCurrent_amount(new BigDecimal(aggregatedDto.getCurrent_amount()).add(amount9).toString());

                        // Суммируем значения для агрегации по типам счетов
                        typeAggregatedDto.setPrev_amount(new BigDecimal(typeAggregatedDto.getPrev_amount()).add(amount6).toString());
                        typeAggregatedDto.setDeb(new BigDecimal(typeAggregatedDto.getDeb()).add(amount7).toString());
                        typeAggregatedDto.setKred(new BigDecimal(typeAggregatedDto.getKred()).add(amount8).toString());
                        typeAggregatedDto.setCurrent_amount(new BigDecimal(typeAggregatedDto.getCurrent_amount()).add(amount9).toString());
                    }
                } catch (Exception e) {
                    logger.error("Ошибка при обработке записи: " + record, e);
                }
            }

            // 1. Вывод сумм по типам счетов (первые 5 цифр)
            logger.info("\n=== СУММЫ ПО ВИДАМ СЧЕТОВ (первые 5 цифр) ===");
            accountTypeSums.forEach((accountType, dto) -> {
                logger.info("Тип счета: {}", accountType);
                logger.info("Начальный остаток: {}", dto.getPrev_amount());
                logger.info("Дебет: {}", dto.getDeb());
                logger.info("Кредит: {}", dto.getKred());
                logger.info("Конечный остаток: {}", dto.getCurrent_amount());
                logger.info("----------------------------------");
            });

            // Добавляем агрегированные данные в resultList (оригинальная логика)
            resultList.addAll(accountSums.values());

            // Оригинальная логика обработки для записи в файл (без изменений)
            // Запись в файл (оригинальная логика)
            for (CbOtchDTO dto : resultList) {
                try {
                    Optional<Kredit> creditOpt = byls_kred(dto.getAccount());
                    if (creditOpt.isPresent()) {
                        Kredit kredit = creditOpt.get();

                        String cleanedNumdog = kredit.getNumdog()
                                .replaceAll("[-KК/\\\\]", "")
                                .trim();

                        String line008 = dateStringReverse + separator +
                                "02" + separator +
                                inform.getNumks() + separator +
                                (kredit.getGrkiContractId() != null ? kredit.getGrkiContractId() : "0") + separator +
                                cleanedNumdog + separator +
                                dto.getAccount() + separator +
                                dto.getPrev_amount() + separator +
                                dto.getDeb() + separator +
                                dto.getKred() + separator +
                                dto.getCurrent_amount() + separator + "\n";

                        writer008.write(line008);
                        writer008.flush();

                        // Сохраняем записанные данные для последующей агрегации
                        CbOtchDTO writtenRecord = new CbOtchDTO();
                        writtenRecord.setAccount(dto.getAccount());
                        writtenRecord.setPrev_amount(dto.getPrev_amount());
                        writtenRecord.setDeb(dto.getDeb());
                        writtenRecord.setKred(dto.getKred());
                        writtenRecord.setCurrent_amount(dto.getCurrent_amount());
                        allWrittenRecords.add(writtenRecord);

                        // Оригинальная запись в Excel
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(dateStringReverse);
                        row.createCell(1).setCellValue("02");
                        row.createCell(2).setCellValue(inform.getNumks());
                        row.createCell(3).setCellValue(kredit.getGrkiContractId());
                        row.createCell(4).setCellValue(cleanedNumdog);
                        row.createCell(5).setCellValue(dto.getAccount());
                        row.createCell(6).setCellValue(trimZeros(dto.getPrev_amount()));
                        row.createCell(7).setCellValue(trimZeros(dto.getDeb()));
                        row.createCell(8).setCellValue(trimZeros(dto.getKred()));
                        row.createCell(9).setCellValue(trimZeros(dto.getCurrent_amount()));

                    }
                } catch (Exception e) {
                    logger.error("Ошибка при обработке DTO: " + dto, e);
                }
            }

            writer008.close();

            writer008.close();

// Агрегация записей прямо из файла
            Map<String, CbOtchDTO> writtenTypeSums = new LinkedHashMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName008))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(Pattern.quote(String.valueOf(separator))); // экранируем, если это символ вроде "|"

                    if (parts.length < 10) {
                        logger.warn("Пропущена строка из-за недостаточного количества полей: " + line);
                        continue;
                    }

                    String account = parts[5].trim();
                    String prev_amount = parts[6].trim();
                    String deb = parts[7].trim();
                    String kred = parts[8].trim();
                    String current_amount = parts[9].trim();

                    String accountType = account.length() >= 5 ? account.substring(0, 5) : account;

                    CbOtchDTO typeSum = writtenTypeSums.get(accountType);
                    if (typeSum == null) {
                        typeSum = new CbOtchDTO();
                        typeSum.setAccount(accountType);
                        typeSum.setPrev_amount("0");
                        typeSum.setDeb("0");
                        typeSum.setKred("0");
                        typeSum.setCurrent_amount("0");
                        writtenTypeSums.put(accountType, typeSum);
                    }

                    typeSum.setPrev_amount(new BigDecimal(typeSum.getPrev_amount()).add(new BigDecimal(prev_amount)).toString());
                    typeSum.setDeb(new BigDecimal(typeSum.getDeb()).add(new BigDecimal(deb)).toString());
                    typeSum.setKred(new BigDecimal(typeSum.getKred()).add(new BigDecimal(kred)).toString());
                    typeSum.setCurrent_amount(new BigDecimal(typeSum.getCurrent_amount()).add(new BigDecimal(current_amount)).toString());
                }
            } catch (IOException e) {
                logger.error("Ошибка при чтении из .008 файла для агрегации", e);
            }


            // Вывод агрегированных сумм по типам счетов (на основе записанных данных)
            logger.info("\n=== АГРЕГИРОВАННЫЕ СУММЫ ПО ТИПАМ СЧЕТОВ (НА ОСНОВЕ ЗАПИСАННЫХ ДАННЫХ) ===");
            writtenTypeSums.forEach((accountType, dto) -> {
                logger.info("Тип счета: {}", accountType);
                logger.info("Начальный остаток: {}", dto.getPrev_amount());
                logger.info("Дебет: {}", dto.getDeb());
                logger.info("Кредит: {}", dto.getKred());
                logger.info("Конечный остаток: {}", dto.getCurrent_amount());
                logger.info("----------------------------------");
            });

            // === СРАВНЕНИЕ ДО И ПОСЛЕ ЗАПИСИ В ФАЙЛ ===
            logger.info("\n=== СРАВНЕНИЕ СУММ ДО И ПОСЛЕ ЗАПИСИ В ФАЙЛ ===");

            Set<String> allKeys = new HashSet<>();
            allKeys.addAll(accountTypeSums.keySet());
            allKeys.addAll(writtenTypeSums.keySet());

            for (String key : allKeys) {
                CbOtchDTO before = accountTypeSums.get(key);
                CbOtchDTO after = writtenTypeSums.get(key);

                if (before == null) {
                    logger.warn("‼️ Запись ПРИСУТСТВУЕТ в файле, но ОТСУТСТВУЕТ в исходных данных: " + key);
                    continue;
                }

                if (after == null) {
                    logger.warn("‼️ Запись ПРИСУТСТВУЕТ в исходных данных, но ОТСУТСТВУЕТ в файле: " + key);
                    continue;
                }

                boolean mismatch = false;

                if (compareAmounts(before.getPrev_amount(), after.getPrev_amount()) != 0) {
                    logger.warn("🔁 Несовпадение 'Начальный остаток' для типа " + key + ": до = " + before.getPrev_amount() + ", после = " + after.getPrev_amount());
                    mismatch = true;
                }

                if (compareAmounts(before.getDeb(), after.getDeb()) != 0) {
                    logger.warn("🔁 Несовпадение 'Дебет' для типа " + key + ": до = " + before.getDeb() + ", после = " + after.getDeb());
                    mismatch = true;
                }

                if (compareAmounts(before.getKred(), after.getKred()) != 0) {
                    logger.warn("🔁 Несовпадение 'Кредит' для типа " + key + ": до = " + before.getKred() + ", после = " + after.getKred());
                    mismatch = true;
                }

                if (compareAmounts(before.getCurrent_amount(), after.getCurrent_amount()) != 0) {
                    logger.warn("🔁 Несовпадение 'Конечный остаток' для типа " + key + ": до = " + before.getCurrent_amount() + ", после = " + after.getCurrent_amount());
                    mismatch = true;
                }

                if (!mismatch) {
                    logger.info("✔️ Совпадает тип счёта " + key);
                }
            }


            // Сохранение Excel файла
            try (FileOutputStream fileOut = new FileOutputStream(excelFileName)) {
                workbook.write(fileOut);
                workbook.close();
                logger.info("Excel файл успешно сохранён: {}", excelFileName);
            } catch (IOException e) {
                logger.error("Ошибка при сохранении файлов", e);
            }

        } catch (Exception e) {
            logger.error("Критическая ошибка при обработке .008 файла", e);
        }


        // Создание и запись в файл с расширением .009
        try {
            BufferedWriter writer009 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName009), "windows-1251"));

            List<CbOtchDTO> allWrittenRecords = new ArrayList<>();

            String[] balValues = {"12401", "16307", "16377"};

// Итоговые суммы по типам счетов
            Map<String, BigDecimal> debitTypeTotalsFinal = new LinkedHashMap<>();
            Map<String, BigDecimal> creditTypeTotalsFinal = new LinkedHashMap<>();

            for (String prefix : balValues) {
                List<DokWithSource> dokListCombined = new ArrayList<>();

                // Сохраняем, по какому полю нашли Dok
                dokRepository.findByLsStartingWithAndDats(prefix, currentDate.toLocalDate())
                        .forEach(d -> dokListCombined.add(new DokWithSource(d, "ls")));

                dokRepository.findByLscorStartingWithAndDats(prefix, currentDate.toLocalDate())
                        .forEach(d -> dokListCombined.add(new DokWithSource(d, "lscor")));

                for (DokWithSource item : dokListCombined) {
                    Dok dok = item.getDok();
                    String source = item.getSource(); // "ls" или "lscor"

                    // Агрегируем по типам счетов
                    String accountTypeKey;
                    BigDecimal amount = dok.getSums();

                    if ("ls".equals(source) && dok.getLs() != null && dok.getLs().length() >= 5) {
                        accountTypeKey = dok.getLs().substring(0, 5);
                        debitTypeTotalsFinal.merge(accountTypeKey, amount, BigDecimal::add);
                    } else if ("lscor".equals(source) && dok.getLscor() != null && dok.getLscor().length() >= 5) {
                        accountTypeKey = dok.getLscor().substring(0, 5);
                        creditTypeTotalsFinal.merge(accountTypeKey, amount, BigDecimal::add);
                    }

                    String accountToSearch = source.equals("ls") ? dok.getLs() : dok.getLscor();

                    Optional<Kredit> creditOpt = byls_kred(accountToSearch);

                    if (creditOpt.isPresent()) {
                        Kredit kredit = creditOpt.get();
                        String cleanedNumdog = kredit.getNumdog().replaceAll("[-KК/\\\\]", "").trim();

                        kreditRepository.findByNumdog(cleanedNumdog).ifPresent(found_kredit -> {
                            Optional<AzolikFiz> azolikFiz = azolikFizRepository.findByKodchlen(found_kredit.getKod());
                            Optional<AzolikYur> azolikYur = azolikYurRepository.findByKodchlen(found_kredit.getKod());

                            AzolikFiz fiz = azolikFiz.orElse(null);
                            AzolikYur yur = azolikYur.orElse(null);

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
                            } else if (dok.getLs().startsWith("42005") && dok.getLscor().startsWith("16307")) {
                                lsKod = "01014";
                            }
                            // dic 060 -> 01015
                            else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("10101")) {
                                lsKod = "01015";
                            } else if (dok.getLs().startsWith("16309") && dok.getLscor().startsWith("10101")) {
                                lsKod = "01015";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("22812")) {
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

                            String nalCard = "";
                            String typeOption = "";
                            if (dok.getLscor().startsWith("10509")) {
                                nalCard = "3";
                            } else {
                                nalCard = "1";
                            }

                            if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0301";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10503")) {
                                typeOption = "0301";
                            } else if (dok.getLs().startsWith("10101") && dok.getLscor().startsWith("12401")) {
                                typeOption = "0103";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0303";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0901";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0902";
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
                                typeOption = "0405";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10101")) {
                                typeOption = "0307";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("10509")) {
                                typeOption = "0305";
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
                            } else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16307")) {
                                typeOption = "0201";
                            } else if (dok.getLs().startsWith("42005") && dok.getLscor().startsWith("16307")) {
                                typeOption = "0201";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0401";
                            } else if (dok.getLs().startsWith("12499") && dok.getLscor().startsWith("56802")) {
                                typeOption = "0801";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12405")) {
                                typeOption = "0501";
                            }


                            if (fiz == null) {
                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((found_kredit != null && found_kredit.getGrkiContractId() != null) ? found_kredit.getGrkiContractId() : "0") + separator +
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
//                                            allWrittenRecords.add(dto); // <<<<< добавляем только реально записанные
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                String line009 = dateStringReverse + separator +
                                        "02" + separator +
                                        inform.getNumks() + separator +
                                        ((found_kredit != null && found_kredit.getGrkiContractId() != null) ? found_kredit.getGrkiContractId() : "0") + separator +
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
//                                            allWrittenRecords.add(dto); // <<<<< добавляем только реально записанные
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        });
                    }
                }
            }
            // ============ Лог финальных сумм по типам счетов ============
            logger.info("\n=== СУММЫ ПО ВИДАМ СЧЕТОВ ТРАНЗАКЦИЙ (ПОСЛЕ ЗАПИСИ В .009) ===");

            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(debitTypeTotalsFinal.keySet());
            allKeys.addAll(creditTypeTotalsFinal.keySet());

            for (String accountType : allKeys) {
                BigDecimal debit = debitTypeTotalsFinal.getOrDefault(accountType, BigDecimal.ZERO);
                BigDecimal credit = creditTypeTotalsFinal.getOrDefault(accountType, BigDecimal.ZERO);

                logger.info("Тип счета: {}", accountType);
                logger.info("Дебет: {}", debit);
                logger.info("Кредит: {}", credit);
                logger.info("----------------------------------");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String zipFileName = generateZipFileName(dateString); // Генерируем имя архива
        String zipFilePath = FOLDER_PATH + "/" + zipFileName + ".zip";
        String zipFilePathWithoutExtension = FOLDER_PATH + "/" + zipFileName; // Архив без расширения

        try (
                FileOutputStream fos = new FileOutputStream(zipFilePath);
                ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            // Добавляем файл .008 в архив
            addFileToZip(fileName008, zipOut);

            // Добавляем файл .009 в архив
            addFileToZip(fileName009, zipOut);

        } catch (
                IOException e) {
            e.printStackTrace();
            return "Ошибка при создании архива: " + e.getMessage();
        }

// Создаем копию архива без расширения
        try {
            Files.copy(Paths.get(zipFilePath), Paths.get(zipFilePathWithoutExtension), StandardCopyOption.REPLACE_EXISTING);
        } catch (
                IOException e) {
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

    private String trimZeros(String value) {
        if (value == null) return "";
        // Удаляем минус и обрезаем два последних символа, если они "00"
        String cleaned = value.replace("-", "");
        if (cleaned.length() >= 2 && cleaned.endsWith("00")) {
            return cleaned.substring(0, cleaned.length() - 2);
        }
        return cleaned;
    }

// Вспомогательные методs

    private String cleanContractNumber(String numdog) {
        return numdog.replaceAll("[-KК/\\\\]", "").trim();
    }

    private void createCell(Row row, int column, String value) {
        row.createCell(column).setCellValue(value);
    }

    private String getContractNumberForAccount(String account) {
        try {
            Optional<Kredit> creditOpt = byls_kred(account);
            if (creditOpt.isPresent()) {
                return creditOpt.get().getNumdog()
                        .replaceAll("[-KК/\\\\]", "")
                        .trim();
            }
        } catch (Exception e) {
            logger.error("Ошибка при получении номера договора для счета: " + account, e);
        }
        return "НЕ НАЙДЕН";
    }

    // Метод для сложения BigDecimal (корректная арифметика)
    private String sumBigDecimals(String num1, String num2) {
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        return bd1.add(bd2).toString();
    }

    // Методы остаются без изменений
    private String sumValues(String a, String b) {
        return new BigDecimal(a).add(new BigDecimal(b)).toString();
    }

    private String formatSum(String amount) {
        return new DecimalFormat("#,##0.00")
                .format(new BigDecimal(amount))
                .replace(",", " ")
                .replace(".", ",");
    }

    private int compareAmounts(String a, String b) {
        return new BigDecimal(a).compareTo(new BigDecimal(b));
    }


}
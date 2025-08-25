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


    // Генерация имени файла на основе даты и других параметров
    public String generateFilename(String date, String TTT) {
        Inform inform = informHelper.fetchSingleRow();
        String N = "N"; // Константа, идентификатор файла от АС кредитной организации
        String BBBBB = inform.getNumks(); // Код кредитной организации

        // Формируем название файла
        return N + BBBBB + "." + TTT;
    }

    public Optional<Kredit> byls_kred(String lskred) {
        // Очистка кэша сессии перед запросом
        entityManager.clear();


        // Поля, по которым будем искать
        List<String> fields = List.of(
                "lskred",
                "lsproc",
                "lsprosr_proc"
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

        logger.warn("Кредит не найден ни по основному номеру " + lskred + ", ни по альтернативным вариантам");
        return Optional.empty();
    }

    private Map<String, Kredit> loadAllKredits(Set<String> accounts) {
        Map<String, Kredit> result = new HashMap<>();
        logger.info("Начало загрузки кредитов для {} счетов", accounts.size());

        // Массовый поиск
        Map<String, Kredit> standardResults = loadKreditsStandardWay(accounts);
        result.putAll(standardResults);
        logger.info("Массовым поиском найдено: {} кредитов", standardResults.size());

        // Индивидуальный поиск для ненайденных
        Set<String> missingAccounts = new HashSet<>(accounts);
        missingAccounts.removeAll(result.keySet());

        if (!missingAccounts.isEmpty()) {
            logger.info("Запускаем индивидуальный поиск для {} счетов: {}",
                    missingAccounts.size(), missingAccounts);

            Map<String, Kredit> individualResults = loadKreditsIndividualWay(missingAccounts);
            result.putAll(individualResults);
            logger.info("Индивидуальным поиском найдено: {} кредитов", individualResults.size());
        }

        // Финальная проверка
        Set<String> stillMissing = new HashSet<>(accounts);
        stillMissing.removeAll(result.keySet());
        if (!stillMissing.isEmpty()) {
            logger.warn("После всех попыток не найдены кредиты для {} счетов: {}",
                    stillMissing.size(), stillMissing);
        }

        logger.info("Итогово загружено: {}/{} кредитов", result.size(), accounts.size());
        return result;
    }

    private Map<String, Kredit> loadKreditsStandardWay(Set<String> accounts) {
        Map<String, Kredit> result = new HashMap<>();
        List<String> fields = List.of("lskred", "lsproc", "lsprosr_proc");

        for (String field : fields) {
            String sql = "SELECT * FROM kredit WHERE " + field + " IN :accounts";
            List<Kredit> kredits = entityManager.createNativeQuery(sql, Kredit.class)
                    .setParameter("accounts", accounts)
                    .getResultList();

            for (Kredit k : kredits) {
                String acc = getAccountValue(k, field);
                if (acc != null && accounts.contains(acc)) {
                    result.put(acc, k);
                }
            }
        }
        return result;
    }

    private Map<String, Kredit> loadKreditsIndividualWay(Set<String> missingAccounts) {
        Map<String, Kredit> result = new HashMap<>();

        for (String account : missingAccounts) {
            Kredit kredit = findKreditByAnyField(account);
            if (kredit != null) {
                result.put(account, kredit);
                logger.info("Найден кредит для проблемного счета: {}", account);
            } else {
                logger.warn("Счет {} не найден даже при индивидуальном поиске", account);
            }
        }

        return result;
    }

    private Kredit findKreditByAnyField(String account) {
        for (String field : List.of("lskred", "lsproc", "lsprosr_proc")) {
            try {
                String sql = "SELECT * FROM kredit WHERE " + field + " = :account";
                List<Kredit> results = entityManager.createNativeQuery(sql, Kredit.class)
                        .setParameter("account", account)
                        .getResultList();

                if (!results.isEmpty()) {
                    logger.info("Найден кредит по полю {} для счета {}", field, account);
                    return results.get(0);
                }
            } catch (Exception e) {
                logger.error("Ошибка поиска по полю {}", field, e);
            }
        }
        return null;
    }

    private String getAccountValue(Kredit kredit, String field) {
        if (kredit == null) {
            return null;
        }

        try {
            switch (field) {
                case "lskred":
                    return kredit.getLskred();
                case "lsproc":
                    return kredit.getLsproc();
                case "lsprosr_proc":
                    return kredit.getLsprosrProc();
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
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
                        String accountType = account.substring(0, 5);

                        BigDecimal amount6 = new BigDecimal(parts[6]);
                        BigDecimal amount7 = new BigDecimal(parts[7]);
                        BigDecimal amount8 = new BigDecimal(parts[8]);
                        BigDecimal amount9 = new BigDecimal(parts[9]);

                        accountSums.computeIfAbsent(account, a -> new CbOtchDTO(a))
                                .addAmounts(amount6, amount7, amount8, amount9);

                        accountTypeSums.computeIfAbsent(accountType, a -> new CbOtchDTO(a))
                                .addAmounts(amount6, amount7, amount8, amount9);
                    }
                } catch (Exception e) {
                    logger.error("Ошибка при обработке записи: " + record, e);
                }
            }

            // Добавляем агрегированные данные в resultList (оригинальная логика)
            resultList.addAll(accountSums.values());

// 2. Один запрос в БД на все счета
            Set<String> accountsToSearch = accountSums.keySet();
            Map<String, Kredit> kreditMap = loadAllKredits(accountsToSearch);

            logger.info("Размер accountSums: {}", accountSums.size());
            logger.info("Размер kreditMap: {}", kreditMap.size());
            logger.info("Ключи kreditMap: {}", kreditMap.keySet());

// 3. Формируем всё в памяти
            StringBuilder fileContent = new StringBuilder();
            List<CbOtchDTO> writtenRecords = new ArrayList<>();

            for (CbOtchDTO dto : accountSums.values()) {
                logger.debug("Ищем кредит для счета: '{}'", dto.getAccount());
                Kredit kredit = kreditMap.get(dto.getAccount());

                if (dto.getAccount() == null || dto.getAccount().trim().isEmpty()) {
                    logger.warn("Пустой номер счета в DTO: {}", dto.toLogString());
                    continue;
                }

                if (kredit == null) {
                    logger.warn("Не найден кредит по счету: {}", dto.getAccount());
                    continue;
                }

                String cleanedNumdog = kredit.getNumdog()
                        .replaceAll("[-KК/\\\\]", "")
                        .trim();

                fileContent.append(dateStringReverse).append(separator)
                        .append("03").append(separator)
                        .append(inform.getNumks()).append(separator)
                        .append(kredit.getGrkiContractId() != null ? kredit.getGrkiContractId() : "0").append(separator)
                        .append(cleanedNumdog).append(separator)
                        .append(dto.getAccount()).append(separator)
                        .append(dto.getPrevAmount()).append(separator)
                        .append(dto.getDeb()).append(separator)
                        .append(dto.getKred()).append(separator)
                        .append(dto.getCurrentAmount()).append(separator).append("\n");

                writtenRecords.add(dto);

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dateStringReverse);
                row.createCell(1).setCellValue("03");
                row.createCell(2).setCellValue(inform.getNumks());
                row.createCell(3).setCellValue(kredit.getGrkiContractId());
                row.createCell(4).setCellValue(cleanedNumdog);
                row.createCell(5).setCellValue(dto.getAccount());
                row.createCell(6).setCellValue(trimZeros(dto.getPrevAmount()));
                row.createCell(7).setCellValue(trimZeros(dto.getDeb()));
                row.createCell(8).setCellValue(trimZeros(dto.getKred()));
                row.createCell(9).setCellValue(trimZeros(dto.getCurrentAmount()));
            }

            writer008.close();

            // 4. Записываем одним махом
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(fileName008), "windows-1251"))) {
                writer.write(fileContent.toString());
            }

            Map<String, CbOtchDTO> fileTypeSums = new LinkedHashMap<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileName008), "windows-1251"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(Pattern.quote(String.valueOf(separator)));
                    if (parts.length < 10) continue;

                    String account = parts[5].trim();
                    String type = account.length() >= 5 ? account.substring(0, 5) : account;

                    BigDecimal prev = new BigDecimal(parts[6].trim());
                    BigDecimal deb = new BigDecimal(parts[7].trim());
                    BigDecimal kred = new BigDecimal(parts[8].trim());
                    BigDecimal curr = new BigDecimal(parts[9].trim());

                    fileTypeSums.computeIfAbsent(type, t -> new CbOtchDTO(t))
                            .addAmounts(prev, deb, kred, curr);
                }

            } catch (IOException e) {
                logger.error("Ошибка при чтении .008 файла для агрегации по типам", e);
            }

// --- Логируем суммы по типам счетов ---
            logger.info("\n=== СУММЫ ПО ТИПАМ СЧЕТОВ ИЗ ФАЙЛА ===");
            fileTypeSums.forEach((type, dto) -> {
                logger.info(dto.toLogString());
            });

// 5. Агрегация по типам счетов после записи
            Map<String, CbOtchDTO> writtenTypeSums = new LinkedHashMap<>();

            for (CbOtchDTO dto : writtenRecords) {
                String type = dto.getAccount().substring(0, 5); // первые 5 цифр
                writtenTypeSums.computeIfAbsent(type, t -> new CbOtchDTO(t))
                        .addAmounts(dto.getPrevAmountBD(), dto.getDebBD(), dto.getKredBD(), dto.getCurrentAmountBD());
            }

// --- Логируем итоговые суммы по типам счетов ---
            logger.info("\n=== СУММЫ ПО ТИПАМ СЧЕТОВ ИЗ ЗАПИСИ ===");
            writtenTypeSums.forEach((type, dto) -> {
                logger.info(dto.toLogString());
            });

// 6. Сравнение
            logger.info("\n=== СРАВНЕНИЕ ДО/ПОСЛЕ ===");
            for (String type : accountTypeSums.keySet()) {
                CbOtchDTO before = accountTypeSums.get(type);
                CbOtchDTO after = writtenTypeSums.get(type);
                if (after == null) {
                    logger.warn("Отсутствует после записи: {}", type);
                    continue;
                }
                if (!before.equalsAmounts(after)) {
                    logger.warn("Несовпадение для типа {}: до={}, после={}", type, before, after);
                } else {
                    logger.info("✔ Совпадает {}", type);
                }
            }

// 7. Сохраняем Excel
            try (FileOutputStream fileOut = new FileOutputStream(excelFileName)) {
                workbook.write(fileOut);
            }
            workbook.close();

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

                        kreditRepository.findByNumdog(kredit.getNumdog().trim()).ifPresent(found_kredit -> {
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
                            else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("12401")) {
                                lsKod = "01009";
                            } else if (dok.getLs().startsWith("14801") && dok.getLscor().startsWith("12405")) {
                                lsKod = "01009";
                            } else if (dok.getLs().startsWith("12501") && dok.getLscor().startsWith("12405")) {
                                lsKod = "01009";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12405")) {
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
                            else if (dok.getLs().startsWith("56802") && dok.getLscor().startsWith("12499")) {
                                lsKod = "01012";
                            }
                            // dic 060 -> 01013
                            else if (dok.getLs().startsWith("96345") && dok.getLscor().startsWith("95413")) {
                                lsKod = "01013";
                            }
                            // dic 060 -> 01014
                            else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16307")) {
                                lsKod = "01014";
                            } else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16377")) {
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
                            } else if (dok.getLs().startsWith("22812") && dok.getLscor().startsWith("16307")) {
                                lsKod = "01015";
                            } else if (dok.getLs().startsWith("22812") && dok.getLscor().startsWith("16377")) {
                                lsKod = "01015";
                            } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("22812")) {
                                lsKod = "01015";
                            } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("22812")) {
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
                                typeOption = "0601";
                            } else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16307")) {
                                typeOption = "0201";
                            } else if (dok.getLs().startsWith("42001") && dok.getLscor().startsWith("16377")) {
                                typeOption = "0207";
                            } else if (dok.getLs().startsWith("42005") && dok.getLscor().startsWith("16307")) {
                                typeOption = "0201";
                            } else if (dok.getLs().startsWith("22812") && dok.getLscor().startsWith("16307")) {
                                typeOption = "0912";
                            } else if (dok.getLs().startsWith("22812") && dok.getLscor().startsWith("16377")) {
                                typeOption = "0913";
                            }else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0909";
                            } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("22812")) {
                                typeOption = "0910";
                            } else if (dok.getLs().startsWith("12499") && dok.getLscor().startsWith("56802")) {
                                typeOption = "0801";
                            } else if (dok.getLs().startsWith("56802") && dok.getLscor().startsWith("12499")) {
                                typeOption = "0802";
                            } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12405")) {
                                typeOption = "0501";
                            } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("12401")) {
                                typeOption = "1441";
                            }


                            if (fiz == null) {
                                String line009 = dateStringReverse + separator +
                                        "03" + separator +
                                        inform.getNumks() + separator +
                                        ((found_kredit != null && found_kredit.getGrkiContractId() != null) ? found_kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        typeOption + separator +
                                        nalCard + separator +
                                        "03" + separator +
                                        dok.getKod() + separator +
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
                                        "03" + separator +
                                        inform.getNumks() + separator +
                                        ((found_kredit != null && found_kredit.getGrkiContractId() != null) ? found_kredit.getGrkiContractId() : "0") + separator +
                                        cleanedNumdog + separator +
                                        dok.getKod().intValue() + separator +
                                        typeOption + separator +
                                        nalCard + separator +
                                        "03" + separator +
                                        dok.getKod() + separator +
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

}
package com.grkicbreport.service;

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
import java.math.RoundingMode;
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

import com.grkicbreport.component.InformHelper;


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

    public FileGeneratorService(KreditRepository kreditRepository, DokRepository dokRepository, AzolikFizRepository azolikFizRepository, AzolikYurRepository azolikYurRepository, com.grkicbreport.component.InformHelper informHelper) {
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

                // Дополнительная проверка для 95413 счетов
                if (lskred.startsWith("95413") && (kredit.getGrkiContractId() == null || Objects.equals(kredit.getGrkiContractId(), "0"))) {
                    return findAlternative95413Account(lskred);
                }

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

    private Optional<Kredit> findAlternative95413Account(String lskred) {
        if (lskred == null || lskred.length() != 20) {
            logger.warn("Номер счета 95413 должен иметь длину 20 символов");
            return Optional.empty();
        }

        String middlePart = lskred.substring(9, 17);  // символы с 9 по 16 (8 символов)
        String likePattern = "95413%" + middlePart + "%";


        try {
            // Запрос только нужных колонок
            String sql = """
                    SELECT numdog, ls_spiskred, [grki-contract-id], dats_zakr, datadog
                    FROM kredit
                    WHERE ls_spiskred LIKE :likePattern
                    ORDER BY numdog DESC
                    """;
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("likePattern", likePattern);

            List<Object[]> results = query.getResultList();

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                logger.info("",
                        i + 1,
                        row[0],  // numdog
                        row[1],  // ls_spiskred
                        row[2],  // grki_contract_id
                        row[3],  // dats_zakr
                        row[4]   // datadog
                );
            }

            if (!results.isEmpty()) {
                Object[] row = results.get(0);

                Kredit kredit = new Kredit();
                kredit.setNumdog(Objects.toString(row[0], ""));
                kredit.setLsSpiskred(Objects.toString(row[1], ""));
                kredit.setGrkiContractId(Objects.toString(row[2], null));

                return Optional.of(kredit);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            logger.error("Ошибка при поиске по шаблону 95413: {}", likePattern, e);
            return Optional.empty();
        }
    }

    private Map<String, Kredit> loadAllKredits(Set<String> accounts) {
        Map<String, Kredit> result = new HashMap<>();
        List<String> fields = List.of(
                "lskred", "lsprosr_kred", "lssud_kred", "lsproc",
                "lsprocvne", "ls_spiskred", "lsprosr_proc", "lsrezerv"
        );

        for (String field : fields) {
            String sql = "SELECT * FROM kredit WHERE " + field + " IN :accounts";
            List<Kredit> kredits = entityManager.createNativeQuery(sql, Kredit.class)
                    .setParameter("accounts", accounts)
                    .getResultList();
            for (Kredit k : kredits) {
                String acc = getAccountValue(k, field);
                if (acc != null) result.putIfAbsent(acc, k);
            }
        }
        return result;
    }

    private String getAccountValue(Kredit kredit, String field) {
        switch (field) {
            case "lskred":
                return kredit.getLskred();
            case "lsprosr_kred":
                return kredit.getLsprosrKred();
            case "lssud_kred":
                return kredit.getLssudKred();
            case "lsproc":
                return kredit.getLsproc();
            case "lsprocvne":
                return kredit.getLsprocvne();
            case "ls_spiskred":
                return kredit.getLsSpiskred();
            case "lsprosr_proc":
                return kredit.getLsprosrProc();
            case "lsrezerv":
                return kredit.getLsrezerv();
            default:
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
            List<String> cb_otch = kreditRepository.getReport008(currentDate, currentDate);
            List<CbOtchDTO> resultList = new ArrayList<>();


            // Для агрегации сумм по счетам (оригинальная логика)
            Map<String, CbOtchDTO> accountSums = new LinkedHashMap<>();

            for (String record : cb_otch) {
                try {
                    // Разбиваем строку на части по "#"
                    String[] parts = record.split("#");

                    if (parts.length > 9) {
                        String account = parts[3]; // Счёт из строки


                        BigDecimal sumbeg = new BigDecimal(parts[6]); // Входящий остаток
                        BigDecimal sumdeb = new BigDecimal(parts[7]); // Дебет
                        BigDecimal sumkr = new BigDecimal(parts[8]); // Кредит
                        BigDecimal sumend = new BigDecimal(parts[9]); // Исходящий остаток

                        accountSums
                                .computeIfAbsent(account, a -> new CbOtchDTO(a))
                                .addAmounts(sumbeg, sumdeb, sumkr, sumend);

                    }
                } catch (Exception e) {
                    logger.error("Ошибка при обработке строки из creat_report_008", e);
                }
            }


            // Добавляем агрегированные данные в resultList (оригинальная логика)
            resultList.addAll(accountSums.values());

            // 2. Один запрос в БД на все счета
            Set<String> accountsToSearch = accountSums.keySet();
            Map<String, Kredit> kreditMap = loadAllKredits(accountsToSearch);

            // 3. Формируем всё в памяти
            StringBuilder fileContent = new StringBuilder();

            for (CbOtchDTO dto : accountSums.values()) {
                Kredit kredit = kreditMap.get(dto.getAccount());
                if (kredit == null) {
                    logger.warn("Не найден кредит по счету: {}", dto.getAccount());
                    kredit = byls_kred(dto.getAccount())
                            .orElse(null); // или .orElse(new Kredit()) для заглушки

                    if (kredit == null) {
                        logger.error("Кредит не найден даже через byls_kred для счета: {}", dto.getAccount());
                        continue; // пропускаем эту итерацию
                    } else logger.warn("Найден кредит по счету: {}", dto.getAccount());

                }

                String cleanedNumdog = kredit.getNumdog()
                        .replaceAll("[-KК/\\\\]", "")
                        .trim();

                fileContent.append(dateStringReverse).append(separator)
                        .append("02").append(separator)
                        .append(inform.getNumks()).append(separator)
                        .append(kredit.getGrkiContractId() != null ? kredit.getGrkiContractId() : "0").append(separator)
                        .append(cleanedNumdog).append(separator)
                        .append(dto.getAccount()).append(separator)
                        .append(dto.getPrevAmount()).append(separator)
                        .append(dto.getDeb()).append(separator)
                        .append(dto.getKred()).append(separator)
                        .append(dto.getCurrentAmount()).append(separator).append("\n");

            }

            writer008.close();

            // 4. Записываем одним махом
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(fileName008), "windows-1251"))) {
                writer.write(fileContent.toString());
            }

        } catch (Exception e) {
            logger.error("Критическая ошибка при обработке .008 файла", e);
        }


        // Создание и запись в файл с расширением .009
        try {
            BufferedWriter writer009 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName009), "windows-1251"));


            String[] balValues = {"12401", "12405", "15701", "12499",
                    "15799", "91501", "95413", "16307", "16377"};

            Set<String> balSet = new HashSet<>(Arrays.asList(balValues));

// Итоговые суммы по типам счетов
            Map<String, BigDecimal> debitTypeTotalsFinal = new LinkedHashMap<>();
            Map<String, BigDecimal> creditTypeTotalsFinal = new LinkedHashMap<>();

            List<Dok> docs = dokRepository.findAllByDatsAndSost(currentDate.toLocalDate(), 3);

            for (Dok dok : docs) {
                BigDecimal amount = dok.getSums();

                String ls = dok.getLs();
                String lscor = dok.getLscor();
                String accountForSearch = null; // ← будем хранить, по какому счёту реально искать

                String accountTypeKey = null;

                if (ls != null && ls.length() >= 5 && balSet.contains(ls.substring(0, 5))) {
                    accountForSearch = ls;
                    accountTypeKey = ls.substring(0, 5);
                    creditTypeTotalsFinal.merge(accountTypeKey, amount, BigDecimal::add);
                } else if (lscor != null && lscor.length() >= 5 && balSet.contains(lscor.substring(0, 5))) {
                    accountForSearch = lscor;
                    accountTypeKey = lscor.substring(0, 5);
                    debitTypeTotalsFinal.merge(accountTypeKey, amount, BigDecimal::add);
                }

                if (accountForSearch == null) {
                    continue; // вообще не наш счёт
                }


                // теперь только один запрос в БД
                Optional<Kredit> creditOpt = byls_kred(accountForSearch);

                if (creditOpt.isEmpty()) {
                    logger.info("Кредит не найден для " + accountForSearch);
                    continue;
                }

                Kredit kredit = creditOpt.get();
                String cleanedNumdog = kredit.getNumdog()
                        .replaceAll("[-KК/\\\\]", "")
                        .trim();

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
                    } else if (dok.getLs().startsWith("12499") && dok.getLscor().startsWith("56802")) {
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
                    } else if (dok.getLs().startsWith("15799") && dok.getLscor().startsWith("56842")) {
                        lsKod = "01012";
                    } else if (dok.getLs().startsWith("56842") && dok.getLscor().startsWith("15799")) {
                        lsKod = "01012";
                    } else if (dok.getLs().startsWith("96345") && dok.getLscor().startsWith("95413")) {
                        lsKod = "01013";
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
                        typeOption = "0302";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("22812")) {
                        typeOption = "0306";
                    } else if (dok.getLs().startsWith("12409") && dok.getLscor().startsWith("22812")) {
                        typeOption = "0310";
                    } else if (dok.getLs().startsWith("15701") && dok.getLscor().startsWith("22812")) {
                        typeOption = "0314";
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
                        typeOption = "1419";
                    } else if (dok.getLs().startsWith("22812") && dok.getLscor().startsWith("16377")) {
                        typeOption = "1423";
                    } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("22812")) {
                        typeOption = "0402";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("22812")) {
                        typeOption = "0406";
                    } else if (dok.getLs().startsWith("12499") && dok.getLscor().startsWith("56802")) {
                        typeOption = "0801";
                    } else if (dok.getLs().startsWith("56802") && dok.getLscor().startsWith("12499")) {
                        typeOption = "0802";
                    } else if (dok.getLs().startsWith("12401") && dok.getLscor().startsWith("12405")) {
                        typeOption = "0501";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("12401")) {
                        typeOption = "1441";
                    } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("42001")) {
                        typeOption = "1460";
                    } else if (dok.getLs().startsWith("16377") && dok.getLscor().startsWith("42005")) {
                        typeOption = "1440";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("15701")) {
                        typeOption = "0506";
                    } else if (dok.getLs().startsWith("15799") && dok.getLscor().startsWith("56842")) {
                        typeOption = "0801";
                    } else if (dok.getLs().startsWith("56842") && dok.getLscor().startsWith("15799")) {
                        typeOption = "0802";
                    } else if (dok.getLs().startsWith("16307") && dok.getLscor().startsWith("42001")) {
                        typeOption = "1460";
                    } else if (dok.getLs().startsWith("42005") && dok.getLscor().startsWith("16377")) {
                        typeOption = "0202";
                    } else if (dok.getLs().startsWith("12405") && dok.getLscor().startsWith("12499")) {
                        typeOption = "0805";
                    } else if (dok.getLs().startsWith("96345") && dok.getLscor().startsWith("95413")) {
                        typeOption = "0819";
                    } else if (dok.getLs().startsWith("96335") && dok.getLscor().startsWith("91501")) {
                        typeOption = "0613";
                    }

                    BigDecimal sumTiyn = amount.movePointRight(2).setScale(0, RoundingMode.HALF_UP);


                    String clientName = (fiz != null) ? fiz.getName() : yur.getName();

                    String line009 = dateStringReverse + separator +
                            "02" + separator +
                            inform.getNumks() + separator +
                            ((found_kredit != null && found_kredit.getGrkiContractId() != null)
                                    ? found_kredit.getGrkiContractId() : "0") + separator +
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
                            sumTiyn.toPlainString() + separator +
                            inform.getName() + separator +
                            clientName + separator +
                            lsKod + separator +
                            dok.getNazn() + separator + "\n";

                    // Записываем строку в файл с расширением .009
                    try {
                        writer009.write(line009);
                        writer009.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
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

}

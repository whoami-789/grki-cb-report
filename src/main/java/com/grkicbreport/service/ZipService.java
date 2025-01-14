package com.grkicbreport.service;

import com.grkicbreport.dto.RecordDTO;
import com.grkicbreport.dto.ZipFileDTO;
import com.grkicbreport.dto.PFileDTO;
import com.grkicbreport.dto.BFileDTO;
import com.grkicbreport.dto.RecordsResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ZipService {

    private static final Logger logger = LoggerFactory.getLogger(ZipService.class);

    private static final String FILE_008 = "N07074.008";
    private static final String FILE_009 = "N07074.009";
    private static final String zipFolderPath = "/Users/rustamrahmov/work/grki_cb_report/grki cb report/C:/Users/Nilufar Asilbek/Desktop/GRKI/";
    private static final String receiveFolderPath = "/Users/rustamrahmov/work/grki_cb_report/grki cb report/C:/Users/Nilufar Asilbek/Desktop/GRKI/";

    /**
     * Получение всех записей из основных ZIP-файлов и связанных дополнительных файлов.
     *
     * @return Структурированный объект с данными из всех файлов
     */
    public RecordsResponseDTO getAllRecords() {
        List<ZipFileDTO> zipFiles = new ArrayList<>();

        // Обработка основных ZIP-файлов
        zipFiles = processMainZipFiles();

        return new RecordsResponseDTO(zipFiles);
    }

    /**
     * Обработка основных ZIP-файлов из zipFolderPath и связанных с ними p и b файлов из receiveFolderPath.
     *
     * @return Список ZipFileDTO
     */
    private List<ZipFileDTO> processMainZipFiles() {
        List<ZipFileDTO> zipFiles = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(zipFolderPath), "N???????.???")) {
            for (Path mainZipPath : stream) {
                String mainZipFileName = mainZipPath.getFileName().toString();
                String zipDate;
                try {
                    zipDate = extractDateFromZipFileName(mainZipFileName);
                } catch (IllegalArgumentException e) {
                    logger.warn("Пропуск файла с некорректным именем: {}", mainZipFileName);
                    continue;
                }

                logger.info("Обработка основного ZIP-файла: {}", mainZipFileName);
                // Обработка содержимого основного ZIP-файла
                ZipFileDTO zipFileDTO = processZipFile(mainZipPath, mainZipFileName, zipDate);
                if (zipFileDTO == null) {
                    logger.warn("Не удалось обработать ZIP-файл: {}", mainZipFileName);
                    continue;
                }

                // Поиск и обработка связанных p и b файлов
                String identifier = mainZipFileName.substring(1, mainZipFileName.indexOf('.')); // Извлекаем идентификатор, например, "0707402"
                String pFilePattern = "p" + identifier + ".*"; // Шаблон для p-файлов
                String bFilePattern = "b" + identifier + ".*"; // Шаблон для b-файлов

                // Обработка p-файлов
                List<PFileDTO> pFiles = processPFiles(identifier);
                zipFileDTO.setpFiles(pFiles);

                // Обработка b-файлов
                List<BFileDTO> bFiles = processBFiles(identifier);
                zipFileDTO.setbFiles(bFiles);

                zipFiles.add(zipFileDTO);
            }
        } catch (IOException e) {
            logger.error("Ошибка при сканировании директории с основными ZIP-файлами", e);
        }

        return zipFiles;
    }

    /**
     * Обработка одного основного ZIP-файла и извлечение записей из файлов `.008` и `.009`.
     *
     * @param zipPath        Путь к ZIP-файлу
     * @param zipFileName    Имя ZIP-файла
     * @param zipDate        Дата, извлечённая из имени ZIP-файла
     * @return Объект ZipFileDTO с разделёнными записями из `.008` и `.009`
     */
    private ZipFileDTO processZipFile(Path zipPath, String zipFileName, String zipDate) {
        List<RecordDTO> file008Records = new ArrayList<>();
        List<RecordDTO> file009Records = new ArrayList<>();
        Charset charset = StandardCharsets.UTF_8; // Замените на нужную кодировку, если требуется

        try (InputStream fis = Files.newInputStream(zipPath);
             ZipInputStream zis = new ZipInputStream(fis, charset)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();

                // Защита от Zip Slip
                if (!isValidEntry(entry, zipFolderPath)) {
                    logger.warn("Обнаружен потенциальный Zip Slip в файле: {}", fileName);
                    zis.closeEntry();
                    continue;
                }

                if (!entry.isDirectory()) {
                    if (fileName.equalsIgnoreCase(FILE_008)) {
                        logger.info("Извлечение файла: {}", fileName);
                        List<RecordDTO> records008 = parseFileContent(zis, zipDate);
                        file008Records.addAll(records008);
                    } else if (fileName.equalsIgnoreCase(FILE_009)) {
                        logger.info("Извлечение файла: {}", fileName);
                        List<RecordDTO> records009 = parseFileContent(zis, zipDate);
                        file009Records.addAll(records009);
                    }
                }

                zis.closeEntry();
            }

        } catch (IOException e) {
            logger.error("Ошибка при обработке основного ZIP-файла: {}", zipFileName, e);
            return null;
        }

        return new ZipFileDTO(zipFileName, file008Records, file009Records, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Обработка связанных pN*.OC3 файлов для данного идентификатора.
     *
     * @param identifier Идентификатор основного ZIP-файла, например, "0707402"
     * @return Список PFileDTO
     */
    private List<PFileDTO> processPFiles(String identifier) {
        List<PFileDTO> pFiles = new ArrayList<>();
        String pFilePrefix = "pN" + identifier;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(receiveFolderPath), pFilePrefix + ".*")) {
            for (Path pFilePath : stream) {
                String pFileName = pFilePath.getFileName().toString();
                logger.info("Обработка p-файла: {}", pFileName);
                PFileDTO pFileDTO = processPFile(pFilePath, pFileName);
                if (pFileDTO != null) {
                    pFiles.add(pFileDTO);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при сканировании директории с p-файлами для идентификатора: {}", identifier, e);
        }

        return pFiles;
    }

    /**
     * Обработка связанных bN*.OC3 файлов для данного идентификатора.
     *
     * @param identifier Идентификатор основного ZIP-файла, например, "0707402"
     * @return Список BFileDTO
     */
    private List<BFileDTO> processBFiles(String identifier) {
        List<BFileDTO> bFiles = new ArrayList<>();
        String bFilePrefix = "bN" + identifier;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(receiveFolderPath), bFilePrefix + ".*")) {
            for (Path bFilePath : stream) {
                String bFileName = bFilePath.getFileName().toString();
                logger.info("Обработка b-файла: {}", bFileName);
                BFileDTO bFileDTO = processBFile(bFilePath, bFileName);
                if (bFileDTO != null) {
                    bFiles.add(bFileDTO);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при сканировании директории с b-файлами для идентификатора: {}", identifier, e);
        }

        return bFiles;
    }

    /**
     * Обработка одного pN*.OC3 файла и извлечение записей об ошибках.
     *
     * @param pFilePath Путь к pN*.OC3 файлу
     * @param pFileName Имя pN*.OC3 файла
     * @return Объект PFileDTO с записями об ошибках
     */
    private PFileDTO processPFile(Path pFilePath, String pFileName) {
        List<RecordDTO> errorRecords = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(pFilePath, StandardCharsets.UTF_8)) {
            String line;

            // Чтение первой строки: имя файла внутри p-файла
            line = reader.readLine();
            if (line == null) {
                logger.warn("p-файл пустой: {}", pFileName);
                return null;
            }

            // Чтение второй строки: дата и время
            line = reader.readLine();
            if (line == null) {
                logger.warn("p-файл без даты: {}", pFileName);
                return null;
            }

            // Чтение третьей строки: количество ошибок
            line = reader.readLine();
            if (line == null || !line.matches("\\d+")) {
                logger.warn("p-файл без количества ошибок: {}", pFileName);
                return null;
            }

            int numErrors = Integer.parseInt(line.trim());

            for (int i = 0; i < numErrors; i++) {
                // Чтение номера ошибки
                String codeLine = reader.readLine();
                if (codeLine == null || !codeLine.matches("\\d+")) {
                    logger.warn("p-файл: некорректный код ошибки в файле: {}", pFileName);
                    break;
                }
                long code = Long.parseLong(codeLine.trim());

                // Чтение сообщения об ошибке
                String messageLine = reader.readLine();
                if (messageLine == null) {
                    logger.warn("p-файл: отсутствует сообщение об ошибке в файле: {}", pFileName);
                    break;
                }

                RecordDTO record = new RecordDTO(code, null, messageLine.trim());
                errorRecords.add(record);
            }

        } catch (IOException | NumberFormatException e) {
            logger.error("Ошибка при обработке p-файла: {}", pFileName, e);
            return null;
        }

        return new PFileDTO(pFileName, errorRecords);
    }

    /**
     * Обработка одного bN*.OC3 файла и извлечение записей из файлов `.008` и `.009`.
     *
     * @param bFilePath  Путь к bN*.OC3 архиву
     * @param bFileName  Имя bN*.OC3 архива
     * @return Объект BFileDTO с записями об ошибках
     */
    private BFileDTO processBFile(Path bFilePath, String bFileName) {
        List<RecordDTO> errorRecords = new ArrayList<>();
        Charset charset = StandardCharsets.UTF_8; // Замените на нужную кодировку, если требуется

        try (InputStream fis = Files.newInputStream(bFilePath);
             ZipInputStream zis = new ZipInputStream(fis, charset)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();

                // Защита от Zip Slip
                if (!isValidEntry(entry, receiveFolderPath)) {
                    logger.warn("Обнаружен потенциальный Zip Slip в файле: {}", fileName);
                    zis.closeEntry();
                    continue;
                }

                if (!entry.isDirectory() && (fileName.endsWith(".008") || fileName.endsWith(".009"))) {
                    logger.info("Извлечение файла из b-файла: {}", fileName);
                    List<RecordDTO> records = parseBFileContent(zis);
                    errorRecords.addAll(records);
                }

                zis.closeEntry();
            }

        } catch (IOException e) {
            logger.error("Ошибка при обработке b-файла: {}", bFileName, e);
            return null;
        }

        return new BFileDTO(bFileName, errorRecords);
    }

    /**
     * Парсинг содержимого bN*.008 или bN*.009 файла.
     *
     * @param is InputStream текущего ZipEntry
     * @return Список записей из файла
     */
    private List<RecordDTO> parseBFileContent(InputStream is) {
        List<RecordDTO> errorRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("windows-1251")))) { // Изменено на windows-1251
            String line;
            int separatorCount = 0;

            // Поиск второго разделителя
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("-------------------------------------------------")) {
                    separatorCount++;
                    if (separatorCount == 2) {
                        break;
                    }
                }
            }

            // Чтение записей об ошибках
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("-------------------------------------------------")) {
                    break; // Конец записей
                }

                if (line.isEmpty()) {
                    continue;
                }

                // Чтение номера ошибки
                String numberLine = line;
                if (!numberLine.matches("\\d+")) {
                    logger.warn("Некорректный номер ошибки в b-файле: {}", numberLine);
                    continue;
                }
                long number = Long.parseLong(numberLine);

                // Чтение кода ошибки
                String codeLine = reader.readLine();
                if (codeLine == null || !codeLine.matches("\\d+")) {
                    logger.warn("b-файл: некорректный код ошибки");
                    break;
                }
                long code = Long.parseLong(codeLine.trim());

                // Чтение сообщения об ошибке
                String messageLine = reader.readLine();
                if (messageLine == null) {
                    logger.warn("b-файл: отсутствует сообщение об ошибке");
                    break;
                }

                RecordDTO record = new RecordDTO(code, null, messageLine.trim());
                errorRecords.add(record);
            }

        } catch (IOException | NumberFormatException e) {
            logger.error("Ошибка при парсинге b-файла", e);
        }

        return errorRecords;
    }

    /**
     * Парсинг содержимого файла `.008` или `.009`.
     *
     * @param is      InputStream текущего ZipEntry
     * @param zipDate Дата, связанная с ZIP-файлом
     * @return Список записей из файла
     */
    private List<RecordDTO> parseFileContent(InputStream is, String zipDate) {
        List<RecordDTO> records = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("windows-1251"))); // Изменено на windows-1251
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Пропускаем пустые строки
                }
                RecordDTO record = new RecordDTO(null, zipDate, line.trim());
                records.add(record);
            }
        } catch (IOException e) {
            logger.error("Ошибка при чтении содержимого файла", e);
        }

        return records;
    }


    /**
     * Проверка на безопасность ZipEntry (Zip Slip).
     *
     * @param entry      ZipEntry для проверки
     * @param baseFolder Папка, из которой извлекается файл
     * @return true, если безопасно; иначе false
     */
    private boolean isValidEntry(ZipEntry entry, String baseFolder) {
        Path targetDir = Paths.get(baseFolder).toAbsolutePath().normalize();
        Path targetPath = targetDir.resolve(entry.getName()).normalize();
        return targetPath.startsWith(targetDir);
    }

    /**
     * Извлечение даты из имени файла.
     * Формат имени: N + BBBBB + RR + "." + YMD
     * Пример: N0707402.A1B
     *
     * @param fileName Имя файла
     * @return Дата в формате yyyyMMdd
     */
    private String extractDateFromZipFileName(String fileName) {
        // Разделяем по точке
        String[] parts = fileName.split("\\.");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Некорректное имя файла: " + fileName);
        }

        String YMD = parts[parts.length - 1]; // Последняя часть после точки
        if (YMD.length() != 3) {
            throw new IllegalArgumentException("Некорректная часть YMD в имени файла: " + fileName);
        }

        // Разбираем YMD обратно в дату
        char YChar = YMD.charAt(0);
        String MStr = String.valueOf(YMD.charAt(1));
        String DStr = String.valueOf(YMD.charAt(2));

        int year = 2010 + (YChar - 'A');
        int month = MStr.matches("\\d") ? Integer.parseInt(MStr) : 10 + (MStr.charAt(0) - 'A');
        int day = DStr.matches("\\d") ? Integer.parseInt(DStr) : 10 + (DStr.charAt(0) - 'A');

        // Проверка корректности даты
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyy")); // Изменённый формат
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректная дата в имени файла: " + fileName, e);
        }
    }

}

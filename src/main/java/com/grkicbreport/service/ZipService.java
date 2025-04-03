package com.grkicbreport.service;

import com.grkicbreport.component.InformHelper;
import com.grkicbreport.dto.*;
import com.grkicbreport.model.Inform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ZipService {

    private static final Logger logger = LoggerFactory.getLogger(ZipService.class);
    private final InformHelper informHelper;

    String receiveFolderPath = "C:/Recv";

    // Укажите правильные пути к папкам


    public ZipService(InformHelper informHelper) {
        this.informHelper = informHelper;
    }

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
        Inform inform = informHelper.fetchSingleRow();

        String zipFolderPath = inform.getGrki_file_path();

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

                // Извлечение идентификатора и расширения
                String identifier = extractIdentifier(mainZipFileName); // Например, "0600501"
                String extension = extractExtension(mainZipFileName);   // Например, "P15"

                // Логирование идентификатора и расширения
                logger.debug("Идентификатор: {}, Расширение: {}", identifier, extension);

                // Обработка p-файлов
                List<PFileDTO> pFiles = processPFiles(identifier, extension);
                zipFileDTO.setpFiles(pFiles);

                // Обработка b-файлов
                List<BFileDTO> bFiles = processBFiles(identifier, extension);
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
     * @param zipPath     Путь к ZIP-файлу
     * @param zipFileName Имя ZIP-файла
     * @param zipDate     Дата, извлечённая из имени ZIP-файла
     * @return Объект ZipFileDTO с разделёнными записями из `.008` и `.009`
     */
    private ZipFileDTO processZipFile(Path zipPath, String zipFileName, String zipDate) {
        List<RecordDTO> file008Records = new ArrayList<>();
        List<RecordDTO> file009Records = new ArrayList<>();
        Charset charset = Charset.forName("windows-1251"); // Используется для ZIP-архива
        Inform inform = informHelper.fetchSingleRow();

        String zipFolderPath = inform.getGrki_file_path();


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
                    if (fileName.equalsIgnoreCase("N" + inform.getNumks() + ".008")) { // Возможно, сделать динамическим
                        logger.info("Извлечение файла: {}", fileName);
                        List<RecordDTO> records008 = parseFileContent(zis, zipDate);
                        file008Records.addAll(records008);
                    } else if (fileName.equalsIgnoreCase("N" + inform.getNumks() + ".009")) { // Возможно, сделать динамическим
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

        return new ZipFileDTO(zipFileName, zipDate, file008Records, file009Records, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Обработка связанных pN*.OC3 файлов для данного идентификатора и расширения.
     *
     * @param identifier Идентификатор основного ZIP-файла, например, "0600501"
     * @param extension  Расширение основного ZIP-файла, например, "P15"
     * @return Список PFileDTO
     */
    private List<PFileDTO> processPFiles(String identifier, String extension) {
        List<PFileDTO> pFiles = new ArrayList<>();
        String pFilePattern = "pN" + identifier + "." + extension; // Например, "pN0600501.P15"

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(receiveFolderPath), pFilePattern)) {
            for (Path pFilePath : stream) {
                String pFileName = pFilePath.getFileName().toString();
                logger.info("Обработка p-файла: {}", pFileName);
                PFileDTO pFileDTO = processPFile(pFilePath, pFileName);
                if (pFileDTO != null) {
                    pFiles.add(pFileDTO);
                } else {
                    logger.warn("p-файл не был добавлен из-за ошибок: {}", pFileName);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при сканировании директории с p-файлами для идентификатора {}: {}", identifier, e.getMessage(), e);
        }

        return pFiles;
    }

    /**
     * Обработка связанных bN*.OC3 файлов для данного идентификатора и расширения.
     *
     * @param identifier Идентификатор основного ZIP-файла, например, "0600501"
     * @param extension  Расширение основного ZIP-файла, например, "P15"
     * @return Список BFileDTO
     */
    private List<BFileDTO> processBFiles(String identifier, String extension) {
        List<BFileDTO> bFiles = new ArrayList<>();
        String bFilePattern = "bN" + identifier + "." + extension; // Например, "bN0600501.P15"

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(receiveFolderPath), bFilePattern)) {
            for (Path bFilePath : stream) {
                String bFileName = bFilePath.getFileName().toString();
                logger.info("Обработка b-файла: {}", bFileName);
                BFileDTO bFileDTO = processBFile(bFilePath, bFileName);
                if (bFileDTO != null) {
                    bFiles.add(bFileDTO);
                } else {
                    logger.warn("b-файл не был добавлен из-за ошибок: {}", bFileName);
                }
            }
        } catch (IOException e) {
            logger.error("Ошибка при сканировании директории с b-файлами для идентификатора {}: {}", identifier, e.getMessage(), e);
        }

        return bFiles;
    }

    /**
     * Обработка одного pN*.OC3 файла и извлечение строк.
     *
     * @param pFilePath Путь к pN*.OC3 файлу
     * @param pFileName Имя pN*.OC3 файла
     * @return Объект PFileDTO с извлечёнными строками
     */
    private PFileDTO processPFile(Path pFilePath, String pFileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(pFilePath, Charset.forName("windows-1251"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }

            // Проверка количества строк
            if (lines.size() < 3) {
                logger.warn("p-файл содержит меньше 3 строк: {}", pFileName);
                return null;
            }

            // Создаём PFileDTO с извлечёнными строками
            return new PFileDTO(pFileName, lines);
        } catch (MalformedInputException e) {
            logger.error("Некорректная кодировка в p-файле: {}", pFileName, e);
        } catch (IOException e) {
            logger.error("Ошибка при обработке p-файла: {}", pFileName, e);
        }

        return null;
    }

    /**
     * Обработка одного bN*.OC3 файла и извлечение записей из файлов `.008` и `.009`.
     *
     * @param bFilePath Путь к bN*.OC3 архиву
     * @param bFileName Имя bN*.OC3 архива
     * @return Объект BFileDTO с записями об ошибках
     */
    private BFileDTO processBFile(Path bFilePath, String bFileName) {
        List<RecordDTO> errorRecords = new ArrayList<>();
        Charset charset = Charset.forName("windows-1251"); // Используется для ZIP-архива

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("windows-1251"))); // Используем windows-1251

        try {
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

            // Чтение строк после второго разделителя до следующего
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("-------------------------------------------------")) {
                    break; // Конец записей
                }

                if (line.isEmpty()) {
                    continue;
                }

                // Создание RecordDTO с сообщением
                RecordDTO record = new RecordDTO(null, null, line);
                errorRecords.add(record);
            }

        } catch (IOException e) {
            logger.error("Ошибка при парсинге b-файла", e);
        }

        // Не закрываем reader, чтобы не закрывать 'zis'
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("windows-1251"))); // Используем windows-1251
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
     * @return Дата в формате dd-MM-yyyy
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
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Изменённый формат
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректная дата в имени файла: " + fileName, e);
        }
    }

    /**
     * Извлечение идентификатора из имени файла.
     * Пример: из N0600501.P15 извлекаем "0600501"
     *
     * @param fileName Имя файла
     * @return Идентификатор
     */
    private String extractIdentifier(String fileName) {
        // Извлекаем часть между первой буквой и первой точкой
        int firstDotIndex = fileName.indexOf('.');
        if (firstDotIndex == -1) {
            throw new IllegalArgumentException("Некорректное имя файла (отсутствует точка): " + fileName);
        }
        return fileName.substring(1, firstDotIndex); // Например, из "N0600501.P15" получим "0600501"
    }

    /**
     * Извлечение расширения из имени файла.
     * Пример: из N0600501.P15 извлекаем "P15"
     *
     * @param fileName Имя файла
     * @return Расширение
     */
    private String extractExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            throw new IllegalArgumentException("Некорректное расширение в имени файла: " + fileName);
        }
        return fileName.substring(lastDotIndex + 1); // Например, из "N0600501.P15" получим "P15"
    }
}

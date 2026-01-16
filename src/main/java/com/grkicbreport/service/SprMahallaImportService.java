package com.grkicbreport.service;

import com.grkicbreport.component.TextNorm;
import com.grkicbreport.dto.SprMahallaRowDTO;
import com.grkicbreport.model.SprMahalla;
import com.grkicbreport.repository.SprMahallaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SprMahallaImportService {

    private final SprMahallaRepository repository;

    // если у тебя в листе 125 заголовки не в первой строке — поменяй
    private static final int HEADER_ROW = 0;      // строка 1
    private static final int DATA_START_ROW = 1;  // строка 2
    private static final int BATCH_SIZE = 500;

    private static final DateTimeFormatter[] DATE_FORMATS = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    };

    @Transactional
    public int importExcel125(MultipartFile file) throws Exception {
        // ✅ справочник: пересоздаем целиком
        repository.deleteAllInBatch();
        repository.flush();

        try (InputStream is = file.getInputStream();
             Workbook wb = WorkbookFactory.create(is)) {

            DataFormatter formatter = new DataFormatter();

            // 1) Берем лист: если у тебя он называется "125" — отлично, иначе берем по индексу
            Sheet sheet = wb.getSheet("125");
            if (sheet == null) sheet = wb.getSheetAt(0);

            Row header = sheet.getRow(HEADER_ROW);
            if (header == null) {
                throw new IllegalStateException("Header row is empty (row index " + HEADER_ROW + ")");
            }

            Map<String, Integer> col = headerMap(header, formatter);

            // ✅ дедуп в рамках файла
            Set<String> seenRowKeys = new HashSet<>();

            List<SprMahalla> batch = new ArrayList<>(BATCH_SIZE);
            int processed = 0;

            for (int r = DATA_START_ROW; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                SprMahallaRowDTO dto = parse(row, col, formatter);
                if (dto == null) continue;

                String rowKey = buildRowKey(dto);
                if (!seenRowKeys.add(rowKey)) {
                    continue;
                }

                SprMahalla e = SprMahalla.builder()
                        .uzKad(dto.uzKad())
                        .code1c(dto.code1c())
                        .uzKad2(dto.uzKad2())
                        .mahallaInn(dto.mahallaInn())
                        .soatoRegion(dto.soatoRegion())
                        .soatoDistrict(dto.soatoDistrict())
                        .cbDistrict(dto.cbDistrict())
                        .nameUz(dto.nameUz())
                        .nameRu(dto.nameRu())
                        .nameEn(dto.nameEn())
                        .dateActive(dto.dateActive())
                        .dateEnd(dto.dateEnd())
                        .activeFlag(dto.activeFlag())
                        // .nameRuNorm(TextNorm.norm(dto.nameRu())) // если добавил поле
                        .build();

                batch.add(e);

                if (batch.size() >= BATCH_SIZE) {
                    repository.saveAll(batch);
                    processed += batch.size();
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
                processed += batch.size();
            }

            return processed;
        }
    }

    // ========= dedupe key =========
    private String buildRowKey(SprMahallaRowDTO dto) {
        return String.join("|",
                TextNorm.norm(dto.uzKad()),
                TextNorm.norm(dto.code1c()),
                TextNorm.norm(dto.uzKad2()),
                TextNorm.norm(dto.mahallaInn()),
                TextNorm.norm(dto.soatoRegion()),
                TextNorm.norm(dto.soatoDistrict()),
                TextNorm.norm(dto.cbDistrict()),
                TextNorm.norm(dto.nameUz()),
                TextNorm.norm(dto.nameRu()),
                TextNorm.norm(dto.nameEn()),
                String.valueOf(dto.dateActive()),
                String.valueOf(dto.dateEnd()),
                TextNorm.norm(dto.activeFlag())
        );
    }

    // ========= parsing =========

    private Map<String, Integer> headerMap(Row header, DataFormatter formatter) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < header.getLastCellNum(); i++) {
            Cell cell = header.getCell(i);
            if (cell == null) continue;

            String key = formatter.formatCellValue(cell);
            if (key == null) continue;

            key = key.trim().toLowerCase(Locale.ROOT);
            if (!key.isEmpty()) map.put(key, i);
        }
        return map;
    }

    private SprMahallaRowDTO parse(Row r, Map<String, Integer> c, DataFormatter f) {
        // Названия заголовков должны совпасть с Excel.
        // Если они отличаются (например "Код УзКад" vs "Код УзКад (1)") — скажи, и я подстрою маппинг.
        String uzKad = str(r, c, "код узкад", f);
        String code1c = str(r, c, "код 1c", f);
        String uzKad2 = str(r, c, "код узкад 2", f); // если заголовок другой — поменяем
        String inn = str(r, c, "инн махалли", f);
        String nameRu = str(r, c, "наименование махалли (ru)", f); // если заголовок другой — поменяем

        // минимальная проверка "строка пустая"
        if ((uzKad == null || uzKad.isBlank())
                && (inn == null || inn.isBlank())
                && (nameRu == null || nameRu.isBlank())) {
            return null;
        }

        return new SprMahallaRowDTO(
                uzKad,
                code1c,
                uzKad2,
                inn,
                str(r, c, "соато область", f),
                str(r, c, "соато район", f),
                str(r, c, "код района цб", f),
                str(r, c, "наименование (uz)", f),
                str(r, c, "наименование (ru)", f),
                str(r, c, "наименование (en)", f),
                date(r, c, "дата активизации", f),
                date(r, c, "дата деактивизации", f),
                str(r, c, "признак активности", f)
        );
    }

    private String str(Row r, Map<String, Integer> c, String header, DataFormatter f) {
        Integer idx = c.get(header);
        if (idx == null) return null;

        Cell cell = r.getCell(idx);
        if (cell == null) return null;

        String s = f.formatCellValue(cell);
        if (s == null) return null;

        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private LocalDate date(Row r, Map<String, Integer> c, String header, DataFormatter f) {
        Integer idx = c.get(header);
        if (idx == null) return null;

        Cell cell = r.getCell(idx);
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        String s = f.formatCellValue(cell);
        if (s == null || s.isBlank()) return null;
        s = s.trim();

        for (DateTimeFormatter fmt : DATE_FORMATS) {
            try {
                return LocalDate.parse(s, fmt);
            } catch (Exception ignored) {}
        }
        return null;
    }
}
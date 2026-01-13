package com.grkicbreport.service;

import com.grkicbreport.component.TextNorm;
import com.grkicbreport.dto.MahallaRowDTO;
import com.grkicbreport.model.Mahalla;
import com.grkicbreport.repository.MahallaRepository;
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
public class MahallaImportService {

    private final MahallaRepository repository;

    private static final int HEADER_ROW = 1;      // строка 2
    private static final int DATA_START_ROW = 2;  // строка 3
    private static final int BATCH_SIZE = 500;

    private static final DateTimeFormatter[] DATE_FORMATS = new DateTimeFormatter[] {
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
    };

    @Transactional
    public int importExcel(MultipartFile file) throws Exception {
        // ✅ справочник: проще и правильнее пересоздавать целиком
        repository.deleteAllInBatch();

        try (InputStream is = file.getInputStream();
             Workbook wb = WorkbookFactory.create(is)) {

            DataFormatter formatter = new DataFormatter();
            Sheet sheet = wb.getSheetAt(0);

            Row header = sheet.getRow(HEADER_ROW);
            if (header == null) {
                throw new IllegalStateException("Header row is empty (row index " + HEADER_ROW + ")");
            }

            Map<String, Integer> col = headerMap(header, formatter);

            // ✅ чтобы не было дублей ВНУТРИ самого файла (иногда такое бывает)
            Set<String> seenRowKeys = new HashSet<>();

            List<Mahalla> batch = new ArrayList<>(BATCH_SIZE);
            int processed = 0;

            for (int r = DATA_START_ROW; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                MahallaRowDTO dto = parse(row, col, formatter);
                if (dto == null) continue;

                // ключ строки (на основе всех полей, как ты и хочешь)
                String rowKey = buildRowKey(dto);
                if (!seenRowKeys.add(rowKey)) {
                    continue; // дубль в самом Excel
                }

                Mahalla entity = new Mahalla();

                entity.setDictCode(dto.dictCode());
                entity.setInn(dto.inn());
                entity.setName(dto.name());
                entity.setCode(dto.code());
                entity.setRegionName(dto.regionName());
                entity.setRegionCbCode(dto.regionCbCode());
                entity.setDistrictName(dto.districtName());
                entity.setSectorNo(dto.sectorNo());
                entity.setDistrictSoatoCode(dto.districtSoato());
                entity.setDistrictCbCode(dto.districtCb());
                entity.setDateActive(dto.dateActive());
                entity.setDateEnd(dto.dateEnd());
                entity.setActiveFlag(dto.activeFlag());
                entity.setIsActive(dto.isActive());

                // нормализация для поиска
                entity.setNameNorm(TextNorm.norm(dto.name()));

                batch.add(entity);

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

    private String buildRowKey(MahallaRowDTO dto) {
        // нормализуем строки, чтобы "Ташкент" и "  Ташкент " считались одинаковыми
        return String.join("|",
                String.valueOf(dto.dictCode()),
                TextNorm.norm(dto.inn()),
                TextNorm.norm(dto.name()),
                TextNorm.norm(dto.code()),
                TextNorm.norm(dto.regionName()),
                TextNorm.norm(dto.regionCbCode()),
                TextNorm.norm(dto.districtName()),
                String.valueOf(dto.sectorNo()),
                TextNorm.norm(dto.districtSoato()),
                TextNorm.norm(dto.districtCb()),
                String.valueOf(dto.dateActive()),
                String.valueOf(dto.dateEnd()),
                TextNorm.norm(dto.activeFlag())
        );
    }

    // ===== helpers =====

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

    private MahallaRowDTO parse(Row r, Map<String, Integer> c, DataFormatter f) {
        String inn = str(r, c, "инн махалли", f);
        String name = str(r, c, "наименование махалли", f);
        if (inn == null || inn.isBlank() || name == null || name.isBlank()) return null;

        return new MahallaRowDTO(
                integer(r, c, "код самого справочника", f),
                inn,
                name,
                str(r, c, "код махалли", f),
                str(r, c, "наименование области махалли", f),
                str(r, c, "код области махалли (цб)", f),
                str(r, c, "наименование района махалли", f),
                integer(r, c, "номер сектора", f),
                str(r, c, "код района махалли (соато)", f),
                str(r, c, "код района махалли (цб)", f),
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

    private Integer integer(Row r, Map<String, Integer> c, String header, DataFormatter f) {
        String s = str(r, c, header, f);
        if (s == null) return null;
        try {
            if (s.contains(".")) s = s.substring(0, s.indexOf('.'));
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
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
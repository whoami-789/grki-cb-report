package com.grkicbreport.service;

import com.grkicbreport.model.SprMahalla;
import com.grkicbreport.repository.SprMahallaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityManager;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SprMahallaImportService {

    private final SprMahallaRepository repository;
    private final EntityManager em;

    private static final int BATCH_SIZE = 100; // <= 150 (SQL Server 2100 params)
    private static final DateTimeFormatter DDMMYYYY = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Transactional
    public int importExcel125(MultipartFile file, boolean truncateBefore) throws Exception {

        if (truncateBefore) {
            repository.deleteAllInBatch();
            // чтобы реально очистилось перед вставкой
            repository.flush();
        }

        try (InputStream is = file.getInputStream();
             Workbook wb = WorkbookFactory.create(is)) {

            Sheet sheet = wb.getSheet("125");
            if (sheet == null) throw new IllegalStateException("Лист '125' не найден");

            DataFormatter f = new DataFormatter();

            List<SprMahalla> batch = new ArrayList<>(BATCH_SIZE);
            int processed = 0;

            for (int r = 1; r <= sheet.getLastRowNum(); r++) { // 0 — заголовок
                Row row = sheet.getRow(r);
                if (row == null) continue;

                SprMahalla e = SprMahalla.builder()
                        .uzKad1(longVal(row, 0, f))
                        .code1c(longVal(row, 1, f))
                        .uzKad2(longVal(row, 2, f))
                        .mahallaInn(str(row, 3, f))
                        .soatoRegion(intVal(row, 4, f))
                        .soatoDistrict(intVal(row, 5, f))
                        .cbDistrict(str(row, 6, f))
                        .nameUz(str(row, 7, f))
                        .nameRu(str(row, 8, f))
                        .nameEn(str(row, 9, f))
                        .dateActive(dateVal(row, 10, f))
                        .dateEnd(dateVal(row, 11, f))
                        .activeFlag(str(row, 12, f))
                        .build();

                // пропуск полностью пустых
                if (isEmptyRow(e)) continue;

                batch.add(e);

                if (batch.size() >= BATCH_SIZE) {
                    repository.saveAll(batch);
                    repository.flush();
                    em.clear(); // важно, чтобы не раздувать persistence context
                    processed += batch.size();
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                repository.saveAll(batch);
                repository.flush();
                em.clear();
                processed += batch.size();
            }

            return processed;
        }
    }

    private boolean isEmptyRow(SprMahalla e) {
        return e.getUzKad1() == null
                && e.getCode1c() == null
                && e.getUzKad2() == null
                && e.getMahallaInn() == null
                && e.getNameRu() == null;
    }

    private String str(Row row, int idx, DataFormatter f) {
        Cell c = row.getCell(idx);
        if (c == null) return null;
        String s = f.formatCellValue(c);
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private Long longVal(Row row, int idx, DataFormatter f) {
        String s = str(row, idx, f);
        if (s == null) return null;
        try {
            s = s.replaceAll("\\s", "");
            if (s.contains(".")) s = s.substring(0, s.indexOf('.'));
            return Long.parseLong(s);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer intVal(Row row, int idx, DataFormatter f) {
        Long v = longVal(row, idx, f);
        return v == null ? null : v.intValue();
    }

    private LocalDate dateVal(Row row, int idx, DataFormatter f) {
        Cell c = row.getCell(idx);
        if (c == null) return null;

        if (c.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(c)) {
            return c.getLocalDateTimeCellValue().toLocalDate();
        }

        String s = str(row, idx, f);
        if (s == null) return null;

        String digits = s.replaceAll("\\D", "");
        if (digits.length() == 8) {
            try { return LocalDate.parse(digits, DDMMYYYY); } catch (Exception ignored) {}
        }
        return null;
    }
}
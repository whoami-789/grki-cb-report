package com.grkicbreport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Report008Excel {

    private static final char SEP = '\u001D';

    public static class Totals {
        public BigDecimal begin  = BigDecimal.ZERO;
        public BigDecimal debit  = BigDecimal.ZERO;
        public BigDecimal credit = BigDecimal.ZERO;
        public BigDecimal end    = BigDecimal.ZERO;

        void add(BigDecimal b, BigDecimal d, BigDecimal c, BigDecimal e) {
            begin  = begin.add(b);
            debit  = debit.add(d);
            credit = credit.add(c);
            end    = end.add(e);
        }
    }

    private static Map<String, Totals> summarizeByType(BufferedReader br) throws Exception {
        Map<String, Totals> result = new LinkedHashMap<>();
        String line;

        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;

            String[] parts = splitKeepEmpty(line, SEP);
            int last = lastNonEmptyIndex(parts);
            if (last < 4) continue;

            BigDecimal sumbeg = safeParse(parts[last - 3]).negate();
            BigDecimal sumdeb = safeParse(parts[last - 2]);
            BigDecimal sumkr  = safeParse(parts[last - 1]);
            BigDecimal sumend = safeParse(parts[last]).negate();

            String ls = parts[last - 4];
            if (ls == null || ls.length() < 5) continue;
            String type = ls.substring(0, 5);

            result.computeIfAbsent(type, t -> new Totals())
                    .add(sumbeg, sumdeb, sumkr, sumend);
        }

        return result;
    }

    private static int lastNonEmptyIndex(String[] a) {
        int i = a.length - 1;
        while (i >= 0 && (a[i] == null || a[i].isEmpty())) i--;
        return i;
    }

    private static BigDecimal safeParse(String s) {
        if (s == null) return BigDecimal.ZERO;
        s = s.trim();
        if (s.isEmpty() || "-".equals(s)) return BigDecimal.ZERO;
        try { return new BigDecimal(s); } catch (Exception e) { return BigDecimal.ZERO; }
    }

    private static String[] splitKeepEmpty(String line, char sep) {
        int n = 1;
        for (int i = 0; i < line.length(); i++) if (line.charAt(i) == sep) n++;
        String[] out = new String[n];
        int idx = 0, start = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == sep) {
                out[idx++] = line.substring(start, i);
                start = i + 1;
            }
        }
        out[idx] = line.substring(start);
        return out;
    }

    public static void main(String[] args) throws Exception {
        Path folder = Path.of("C:/Users/Admin/Desktop/bux-lombard"); // папка с архивами
        Charset cs = Charset.forName("windows-1251");

        Map<String, Map<String, Totals>> allResults = new LinkedHashMap<>();

        for (Path file : (Iterable<Path>) Files.list(folder)::iterator) {
            if (!Files.isRegularFile(file)) continue;

            try (ZipFile zip = new ZipFile(file.toFile(), cs)) {
                ZipEntry entry = zip.getEntry("N07105.008");
                if (entry == null) {
                    System.out.println("Файл N07105.008 не найден в архиве: " + file.getFileName());
                    continue;
                }

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(zip.getInputStream(entry), cs))) {
                    Map<String, Totals> byType = summarizeByType(br);
                    allResults.put(file.getFileName().toString(), byType);
                }
            }
        }

        // ==== создаем Excel ====
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Summary");

        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        String[] headers = {"ARCHIVE", "TYPE", "BEGIN", "DEBIT", "CREDIT", "END"};
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        for (Map.Entry<String, Map<String, Totals>> archiveEntry : allResults.entrySet()) {
            String archiveName = archiveEntry.getKey();
            for (Map.Entry<String, Totals> e : archiveEntry.getValue().entrySet()) {
                Totals t = e.getValue();
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(archiveName);
                row.createCell(1).setCellValue(e.getKey());
                row.createCell(2).setCellValue(t.begin.doubleValue());
                row.createCell(3).setCellValue(t.debit.doubleValue());
                row.createCell(4).setCellValue(t.credit.doubleValue());
                row.createCell(5).setCellValue(t.end.doubleValue());
            }
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream out = new FileOutputStream("C:/Users/Admin/Desktop/bux-lombard/summary.xlsx")) {
            workbook.write(out);
        }

        workbook.close();
        System.out.println("Готово! summary.xlsx создан.");
    }
}

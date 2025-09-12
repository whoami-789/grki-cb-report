package com.grkicbreport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class Report008Summarizer {

    // Разделитель 008: ASCII-29
    private static final char SEP = '\u001D';

    public static class Totals {
        public BigDecimal begin  = BigDecimal.ZERO; // суммируем КАК В ФАЙЛЕ (но со знаком +)
        public BigDecimal debit  = BigDecimal.ZERO;
        public BigDecimal credit = BigDecimal.ZERO;
        public BigDecimal end    = BigDecimal.ZERO; // суммируем КАК В ФАЙЛЕ (но со знаком +)

        void add(BigDecimal b, BigDecimal d, BigDecimal c, BigDecimal e) {
            begin  = begin.add(b);
            debit  = debit.add(d);
            credit = credit.add(c);
            end    = end.add(e);
        }
    }

    /**
     * Сводка по типам счетов (первые 5 символов ЛС) из .008-файла.
     * Формат строки (с конца): [-4]=-sumbeg, [-3]=sumdeb, [-2]=sumkr, [-1]=-sumend, [-5]=ls.
     * begin/end берём из файла и просто разворачиваем знак (в файле они с минусом).
     */
    public static Map<String, Totals> summarizeByType(Path filePath, Charset charset) throws Exception {
        Map<String, Totals> result = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath.toFile()), charset))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;

                String[] parts = splitKeepEmpty(line, SEP);
                int last = lastNonEmptyIndex(parts);   // игнорируем возможный хвостовой пустой токен
                if (last < 4) continue;                // мало полей — пропускаем

                // Индексы с хвоста:
                // last     -> -1 : -sumend
                // last-1   -> -2 : sumkr
                // last-2   -> -3 : sumdeb
                // last-3   -> -4 : -sumbeg
                // last-4   -> -5 : ls
                BigDecimal sumbeg = safeParse(parts[last - 3]).negate(); // -sumbeg -> в плюс
                BigDecimal sumdeb = safeParse(parts[last - 2]);
                BigDecimal sumkr  = safeParse(parts[last - 1]);
                BigDecimal sumend = safeParse(parts[last]).negate();      // -sumend -> в плюс

                String ls = parts[last - 4];
                if (ls == null || ls.length() < 5) continue;
                String type = ls.substring(0, 5);

                result.computeIfAbsent(type, t -> new Totals())
                        .add(sumbeg, sumdeb, sumkr, sumend);
            }
        }

        return result;
    }

    // ===== утилиты =====

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

    // Сплит по символу (не regex), с сохранением пустых полей
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

    // Демонстрация
    public static void main(String[] args) throws Exception {
        Path path = Path.of("C:/Users/intel/Desktop/GRKI/N07105.008");
        Charset cs = Charset.forName("windows-1251");

        Map<String, Totals> byType = summarizeByType(path, cs);

        // Печать сводки + контроль дельты по ТИ (актив/пассив при желании можно подтянуть из БД)
        System.out.printf("%-8s %18s %18s %18s %18s%n", "TYPE", "BEGIN", "DEBIT", "CREDIT", "END");
        for (Map.Entry<String, Totals> e : byType.entrySet()) {
            Totals t = e.getValue();
            System.out.printf("%-8s %18s %18s %18s %18s%n",
                    e.getKey(), t.begin.toPlainString(), t.debit.toPlainString(),
                    t.credit.toPlainString(), t.end.toPlainString());
        }
    }
}


package com.grkicbreport;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;

public class Report009Summarizer {

    private static final char SEP = '\u001D'; // разделитель в файле

    public static class Totals {
        BigDecimal debit = BigDecimal.ZERO;
        BigDecimal credit = BigDecimal.ZERO;

        void addDebit(BigDecimal d) { debit = debit.add(d); }
        void addCredit(BigDecimal c) { credit = credit.add(c); }
    }

    public static Map<String, Totals> summarizeByType(Path filePath, Charset charset) throws Exception {
        Map<String, Totals> result = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath.toFile()), charset))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;

                String[] parts = splitKeepEmpty(line, SEP);
                // Проверяем, что полей хватает (у тебя ~19)
                if (parts.length < 15) continue;

                String debitAcc = parts[11];   // lscor
                String creditAcc = parts[13];  // ls
                BigDecimal amount = safeParse(parts[15]); // сумма

                // дебет
                if (debitAcc != null && debitAcc.length() >= 5) {
                    String type = debitAcc.substring(0, 5);
                    result.computeIfAbsent(type, k -> new Totals()).addDebit(amount);
                }

                // кредит
                if (creditAcc != null && creditAcc.length() >= 5) {
                    String type = creditAcc.substring(0, 5);
                    result.computeIfAbsent(type, k -> new Totals()).addCredit(amount);
                }
            }
        }
        return result;
    }

    // ===== утилиты =====

    private static BigDecimal safeParse(String s) {
        if (s == null) return BigDecimal.ZERO;
        s = s.trim();
        if (s.isEmpty() || "-".equals(s)) return BigDecimal.ZERO;
        try {
            return new BigDecimal(s);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
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

    // Демонстрация
    public static void main(String[] args) throws Exception {
        Path path = Path.of("C:/Users/user/Desktop/GRKI/N06005.009");
        Charset cs = Charset.forName("windows-1251");

        Map<String, Totals> byType = summarizeByType(path, cs);

        System.out.printf("%-8s %18s %18s%n", "TYPE", "DEBIT", "CREDIT");
        for (Map.Entry<String, Totals> e : byType.entrySet()) {
            Totals t = e.getValue();
            System.out.printf("%-8s %18s %18s%n",
                    e.getKey(),
                    t.debit.toPlainString(),
                    t.credit.toPlainString());
        }
    }
}

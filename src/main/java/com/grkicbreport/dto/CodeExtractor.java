package com.grkicbreport.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public static String extractCode(String input) {
        if (input == null) {
            return null;
        }

        // Регекс для формата: № <числа>[К или K]? (без года)
        // Пример: "№ 123К"
        String regexShort = "№\\s*(\\d+[КK]?)";

        // Регекс для формата: № <числа>[К или K]?/<4 цифры>
        // Пример: "№ 165К/2025"
        String regexWithYear = "№\\s*(\\d+[КK]?/\\d{4})";

        // Критерий, по которому определяем,
        // что нужно искать формат с годом (напр. с /2025 и выше).
        // Здесь, в качестве примера, проверяем если в строке встречается "/2025" или выше
        // (можно расширить логику проверки, если нужно).
        boolean has2025OrLater = input.matches(".*(\\/20(2[5-9]|[3-9]\\d)).*");

        // Если нашли /2025 или год больше, то ищем с учетом /год
        if (has2025OrLater) {
            Pattern pattern = Pattern.compile(regexWithYear);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        }

        // Иначе (либо /2023, /2024, либо год не указан) — используем короткий формат
        Pattern pattern = Pattern.compile(regexShort);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        // Если ничего не найдено
        return null;
    }
}


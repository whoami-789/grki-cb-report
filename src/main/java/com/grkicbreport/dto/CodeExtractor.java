package com.grkicbreport.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public static String extractCode(String input) {
        // Регулярное выражение для поиска чисел с необязательной буквой К или K после №
        String regex = "№\\s*(\\d+[КK]?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Возвращаем только найденный код без "№"
        if (matcher.find()) {
            return matcher.group(1).trim(); // Группа 1 содержит только код
        }
        return null; // Код не найден
    }
}

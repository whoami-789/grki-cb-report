package com.grkicbreport.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public static String extractCode(String input) {
        // Регулярное выражение для поиска кода в строке
        String regex = "\\d{1,3}[А-Яа-яA-Za-z]?[\\/]?\\d{0,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Возвращаем первый найденный код
        if (matcher.find()) {
            return matcher.group();
        }
        return null; // Код не найден
    }
}


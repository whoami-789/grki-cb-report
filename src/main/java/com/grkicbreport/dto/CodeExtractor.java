package com.grkicbreport.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public static String extractCode(String input) {
        // Регулярное выражение для поиска кода с дефисом или слэшем, с или без "№"
        String regex = "(?:№\\s*)?(\\d+[КK]?[-/]?\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Возвращаем первый найденный код
        if (matcher.find()) {
            return matcher.group(1);  // Извлекаем именно код, без №
        }
        return null; // Код не найден
    }

    // Проверочный метод
    public static void main(String[] args) {
        String[] testStrings = {
                "Документ № 990004312-1",       // С № и дефисом
                "Номер № 1234/56",             // С № и слэшем
                "Запись № 5678K",              // С № и буквой K
                "Случай без кода",             // Без кода
                "Текст 000123",                // Просто цифры без №
                "№ 9999K/88",                  // С №, K и слэшем
                "Пример 1234-56",              // Просто код без №
                "Не 123456",                   // Без №
                "990004312-1" ,                // Только код без №
                "Погашение  процентов сог.дог №99000307"
        };

        for (String test : testStrings) {
            String code = extractCode(test);
            System.out.println("Input: " + test + " -> Extracted: " + code);
        }
    }
}

package com.grkicbreport.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    private static final Pattern CODE_PATTERN = Pattern.compile("№\\s*(\\d+[КK]?(?:/\\d+)?)");

    public static String extractCode(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        Matcher matcher = CODE_PATTERN.matcher(input);
        return matcher.find() ? matcher.group(1).trim() : null;
    }
}
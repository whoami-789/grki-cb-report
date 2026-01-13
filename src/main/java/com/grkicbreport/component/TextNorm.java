package com.grkicbreport.component;

import java.util.Locale;

public final class TextNorm {
    private TextNorm() {
    }

    public static String norm(String s) {
        if (s == null) return null;
        String x = s.trim().toLowerCase(Locale.ROOT);
        x = x.replace('ё', 'е');
        x = x.replaceAll("\\s+", " ");
        return x;
    }
}

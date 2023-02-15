package com.ufpr.tads.web2.utils;

public class StringUtils {
    public static String removeNonNumeric(String text) {
        if (text == null) return text;

        return text.replaceAll("[^0-9]", "");
    }
}

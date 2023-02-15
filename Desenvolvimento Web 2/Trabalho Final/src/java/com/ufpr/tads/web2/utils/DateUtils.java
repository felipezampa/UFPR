package com.ufpr.tads.web2.utils;

import java.util.Date;

public class DateUtils {
    public static Date parseDate(String date) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
        } catch (Exception e) {
            return null;
        }
    }
}

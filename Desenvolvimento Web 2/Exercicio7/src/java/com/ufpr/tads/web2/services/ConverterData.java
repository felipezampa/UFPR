package com.ufpr.tads.web2.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {

    public static Date converteData(String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedData = null;

        try {
            parsedData = format.parse(data);
        } catch (ParseException e) {
            System.out.println("Data no formato errado");
            e.printStackTrace();
        }

        return parsedData;
    }
}

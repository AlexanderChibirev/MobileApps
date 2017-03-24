package com.example.alexander.todolist.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.text.format.DateFormat.getDateFormat;

public class DateUtils {
    private static final String DATE_AND_TIME_PATTERN = "dd-MM-yyyy";

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                DATE_AND_TIME_PATTERN, Locale.ENGLISH);
        Date resultDate = new Date();
        try {
            resultDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public static String dateToString(Date date, Context context) {
        DateFormat dateFormat = getDateFormat(context);
        return dateFormat.format(date);
    }
}

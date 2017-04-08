package com.forrestdale.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by jesse on 4/2/2017.
 */
public class DateUtil {
    //This is some horrid piece of code to enable the month/year to roll over when incrementing days.
    //The java.util.Date class is old and I did not know before implementing quite a bit of date-oriented stuff.
    //Now I am too lazy to change.
    public static Date deltaDayDate(Date javaDate, int delta) {
        LocalDate date = LocalDate.of(javaDate.getYear(), javaDate.getMonth(), javaDate.getDate());
        date = date.plusDays(delta);

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return new Date(year, month, day);
    }

    public static String getTime(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm a");
        return fmt.format(date);
    }

    public static String getDay(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd");
        return fmt.format(date);
    }
}

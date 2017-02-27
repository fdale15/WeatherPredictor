package com.forrestdale.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Date;

/**
 * Created by forrest on 2/26/17.
 */
public class WeatherDateConverter {
    public static String ConvertFromWeatherDate(Date date) {
        //No need for minutes/hours at the moment.
        String month = String.valueOf(date.getMonth() + 1);
        month = month.length() == 2 ? month : "0" + month;
        String year = String.valueOf(date.getYear() + 1900);
        String day = String.valueOf(date.getDay());
        day = day.length() == 2 ? day : "0" + day;

        return new String(year + month + day);
    }

    public static Date ConvertToWeatherDate(String dateString) {
        String day = dateString.substring(0, 8);
        String time = dateString.substring(8);

        int year = Integer.valueOf(day.substring(0, 4)) - 1900;
        int month = Integer.valueOf(day.substring(4, 6)) - 1;
        int date = Integer.valueOf(day.substring(6));
        int hours = Integer.valueOf(time.substring(0, 2));
        int minutes = Integer.valueOf(time.substring(2));

        return new Date(year, month, date, hours, minutes);
    }
}

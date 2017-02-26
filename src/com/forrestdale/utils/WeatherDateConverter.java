package com.forrestdale.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Date;

/**
 * Created by forrest on 2/26/17.
 */
public class WeatherDateConverter {
    public static String ConvertFromWeatherDate(Date date) {
        //Not sure if I need this.
        //TODO: Implement later if necessary.
        throw new NotImplementedException();
    }

    public static Date ConvertToWeatherDate(String dateString) {
        String day = dateString.substring(0, 8);
        String time = dateString.substring(8);

        int year = Integer.valueOf(day.substring(0, 4));
        int month = Integer.valueOf(day.substring(4, 6));
        int date = Integer.valueOf(day.substring(6));
        int hours = Integer.valueOf(time.substring(0, 2));
        int minutes = Integer.valueOf(time.substring(2));

        return new Date(year, month, date, hours, minutes);
    }
}

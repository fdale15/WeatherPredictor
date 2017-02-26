package com.forrestdale;

import com.forrestdale.models.WeatherDay;
import com.forrestdale.utils.WeatherLoader;

public class Main {

    public static void main(String[] args) {
        WeatherLoader wl = new WeatherLoader("./src/com/forrestdale/weatherData.csv", true);

        for (WeatherDay wh : wl.getWeatherDays()) {
            System.out.println(wh);
        }
    }
}

package com.forrestdale;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.predictor.WeatherPredictor;
import com.forrestdale.utils.WeatherLoader;

import java.util.Date;
import java.util.List;

public class Main {
    private static final String FILEPATH = "./src/com/forrestdale/weatherData.csv";

    public static void main(String[] args) {
        WeatherLoader wl = new WeatherLoader(FILEPATH, true);
        WeatherPredictor predictor = new WeatherPredictor(wl.getWeatherDays());

        List<IForecastDay> forecastDay = predictor.PredictForecastRange(new Date(0, 1, 26), new Date(0, 1, 27));
        for (IForecastDay day : forecastDay) {
            System.out.println(day);
        }
    }
}

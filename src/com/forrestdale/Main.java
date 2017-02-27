package com.forrestdale;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.models.WeatherDay;
import com.forrestdale.predictor.WeatherPredictor;
import com.forrestdale.utils.WeatherLoader;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WeatherLoader wl = new WeatherLoader("./src/com/forrestdale/weatherData.csv", true);
        WeatherPredictor predictor = new WeatherPredictor(wl.getWeatherDays());

        List<IForecastDay> forecastDay = predictor.GetForecastRange(new Date(0, 2, 26), new Date(0, 2, 27));
        for (IForecastDay day : forecastDay) {
            System.out.println(day);
        }
    }
}

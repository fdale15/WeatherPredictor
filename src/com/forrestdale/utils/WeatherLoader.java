package com.forrestdale.utils;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.models.WeatherDay;
import com.forrestdale.models.WeatherHour;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by forrestdale on 2/16/17.
 */
public class WeatherLoader extends ModelLoader {
    private List<WeatherHour> mWeatherHours = new ArrayList<>();

    public WeatherLoader(String filename) {
        super(filename);
        mWeatherHours = generateWeatherHours(getRows());
    }

    public List<WeatherDay> getWeatherDays() {
        List<WeatherDay> result = new ArrayList<>();

        return result;
    }

    private List<WeatherHour> generateWeatherHours(List<String[]> rows) {
        List<WeatherHour> result = new ArrayList<>();

        for (String[] row : rows) {
            WeatherHour hour = new WeatherHour();

//            hour.setDate();
//            hour.setDewPoint();
//            hour.setDryBulbTemp();
//            hour.setRelativeHumidity();
//            hour.setStationPressure();
//            hour.setVisibility();
//            hour.setWeatherSkyConditions();
//            hour.setWetBulbTemp();
//            hour.setWindDirection();
//            hour.setWindSpeed();

            result.add(hour);
        }

        return result;
    }
}

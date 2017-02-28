package com.forrestdale.utils;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.models.WeatherDay;
import com.forrestdale.models.WeatherHour;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by forrestdale on 2/16/17.
 */
public class WeatherLoader extends ModelLoader {
    private List<WeatherHour> mWeatherHours = new ArrayList<>();

    public WeatherLoader(String filename, boolean header) {
        super(filename, header);
        mWeatherHours = generateWeatherHours(getRows());
    }

    public Map<String, IForecastDay> getWeatherDays() {
        Map<String, IForecastDay> result = new HashMap<>();
        Map<String, List<WeatherHour>> days = new HashMap<>();

        //Group by the day.
        days = mWeatherHours.stream().collect(Collectors.groupingBy(WeatherHour::getKey));

        for (Map.Entry<String, List<WeatherHour>> day : days.entrySet()) {
            result.put(day.getKey(), new WeatherDay(day.getValue()));
        }
        return result;
    }

    public List<WeatherHour> getWeatherHours() {
        return new ArrayList<WeatherHour>(mWeatherHours);
    }

    private List<WeatherHour> generateWeatherHours(List<String[]> rows) {
        List<WeatherHour> result = new ArrayList<>();

        for (String[] row : rows) {
            String[] sanitizedRow = Sanitize(row);
            WeatherHour hour = new WeatherHour();

            hour.setDate(WeatherDateConverter.ConvertToWeatherDate(sanitizedRow[1] + sanitizedRow[2])); //idx 1, time is idx 2
            hour.setDewPoint(Integer.valueOf(sanitizedRow[18])); //idx 18
            hour.setDryBulbTemp(Integer.valueOf(sanitizedRow[10])); //idx 10
            hour.setRelativeHumidity(Integer.valueOf(sanitizedRow[22])); //idx 22
            hour.setStationPressure(Double.valueOf(sanitizedRow[30])); //idx 30
            hour.setVisibility(Double.valueOf(sanitizedRow[6])); //idx 6
            hour.setWeatherSkyConditions(WeatherSkyConditionConverter.ConvertToWeatherSkyCondition(sanitizedRow[4])); //idx 4
            hour.setWetBulbTemp(Integer.valueOf(sanitizedRow[14])); //idx 14
            hour.setWindDirection(sanitizedRow[26].contains("VR") ? 0 : Integer.valueOf(sanitizedRow[26])); //idx 26
            hour.setWindSpeed(Integer.valueOf(sanitizedRow[24])); //idx 24

            result.add(hour);
        }

        return result;
    }

    private String[] Sanitize(String[] row) {
        String[] result = new String[row.length];

        for (int i = 0; i < row.length; i++) {
            String value = row[i].trim();
            if (value.equals("M") || value.isEmpty()) {
                value = "-1";
            }
            result[i] = value;
        }

        return result;
    }
}

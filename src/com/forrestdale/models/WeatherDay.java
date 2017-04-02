package com.forrestdale.models;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.utils.DoubleAverager;
import com.forrestdale.utils.IntAverager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by forrest on 2/12/17.
 */
public class WeatherDay implements IForecastDay {
    private List<WeatherHour> mWeatherHours;

    public WeatherDay(List<WeatherHour> weatherHours) {
        this.mWeatherHours = weatherHours;
    }

    public WeatherDay() {
        this.mWeatherHours = new ArrayList<>();
    }

    public void addWeatherHour(WeatherHour weatherHour) {
        mWeatherHours.add(weatherHour);
    }
    public void addWeatherHours(List<WeatherHour> weatherHours) {
        mWeatherHours.addAll(weatherHours);
    }

    @Override
    public int getHighTemp() {
        return mWeatherHours.stream().max(Comparator.comparingInt(WeatherHour::getDryBulbTemp)).get().getDryBulbTemp();
    }

    @Override
    public int getLowTemp() {
        return mWeatherHours.stream().min(Comparator.comparingInt(WeatherHour::getDryBulbTemp)).get().getDryBulbTemp();
    }

    @Override
    public double getAvgTemp() {
        IntAverager avg = mWeatherHours.stream().map(WeatherHour::getDryBulbTemp).collect(IntAverager::new, IntAverager::accept, IntAverager::combine);
        return avg.average();
    }

    @Override
    public double getAvgVisibility() {
        DoubleAverager avg = mWeatherHours.stream().map(WeatherHour::getVisibility).collect(DoubleAverager::new, DoubleAverager::accept, DoubleAverager::combine);
        return avg.average();
    }

    @Override
    public double getAvgRelativeHumidity() {
        IntAverager avg = mWeatherHours.stream().map(WeatherHour::getRelativeHumidity).collect(IntAverager::new, IntAverager::accept, IntAverager::combine);
        return avg.average();
    }

    @Override
    public double getAvgWindSpeed() {
        IntAverager avg = mWeatherHours.stream().map(WeatherHour::getWindSpeed).collect(IntAverager::new, IntAverager::accept, IntAverager::combine);
        return avg.average();
    }

    @Override
    public double getAvgWindDir() {
        IntAverager avg = mWeatherHours.stream().map(WeatherHour::getWindDirection).collect(IntAverager::new, IntAverager::accept, IntAverager::combine);
        return avg.average();
    }

    @Override
    public double getAvgStationPressure() {
        DoubleAverager avg = mWeatherHours.stream().map(WeatherHour::getStationPressure).collect(DoubleAverager::new, DoubleAverager::accept, DoubleAverager::combine);
        return avg.average();
    }

    @Override
    public WeatherSkyCondition getAvgSkyCondition() {
        Map<WeatherSkyCondition, Integer> conditionMap = new HashMap<>();

        for (WeatherHour wh : mWeatherHours) {
            WeatherSkyCondition condition = wh.getAvgWeatherSkyCondition();

            if (conditionMap.containsKey(condition)) {
                conditionMap.put(condition, conditionMap.get(condition) + 1);
            }
            else {
                conditionMap.put(condition, 1);
            }
        }

        WeatherSkyCondition avgCondition = null;
        int count = 0;
        for (Map.Entry<WeatherSkyCondition, Integer> pair : conditionMap.entrySet()) {
            if (pair.getValue() > count) {
                count = pair.getValue();
                avgCondition = pair.getKey();
            }
        }
        return avgCondition;
    }

    @Override
    public List<WeatherHour> getWeatherHours() {
        return mWeatherHours;
    }

    @Override
    public WeatherHour getHighTempHour() {
        return mWeatherHours.stream().max(Comparator.comparingInt(WeatherHour::getDryBulbTemp)).get();
    }

    @Override
    public WeatherHour getLowTempHour() {
        return mWeatherHours.stream().min(Comparator.comparingInt(WeatherHour::getDryBulbTemp)).get();
    }

    @Override
    public Date getDate() {
        return new Date(mWeatherHours.get(0).getDay());
    }

    @Override
    public Date getDay() {
        Date d = new Date(mWeatherHours.get(0).getDay());
        return new Date(0, d.getMonth(), d.getDay());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("-----------------Day Forecast for " + getDate().toString() + "-----------------\n");
        sb.append("High Temp: " + getHighTemp());
        sb.append("\n");
        sb.append("Low Temp: " + getLowTemp());
        sb.append("\n");
        sb.append("Avg Temp: " + getAvgTemp());
        sb.append("\n");
        sb.append("Avg Visibility: " + getAvgVisibility());
        sb.append("\n");
        sb.append("Avg RH: " + getAvgRelativeHumidity());
        sb.append("\n");
        sb.append("Avg Windspeed: " + getAvgWindSpeed());
        sb.append("\n");
        sb.append("Avg Wind Direction: " + getAvgWindDir());
        sb.append("\n");
        sb.append("Avg Station Pressure: " + getHighTemp());
        sb.append("\n");

        for (WeatherHour wh : mWeatherHours) {
            sb.append(wh.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getKey() {
        return mWeatherHours.get(0).getKey();
    }
}

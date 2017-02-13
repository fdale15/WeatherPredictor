package com.forrestdale.models;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.utils.DoubleAverager;
import com.forrestdale.utils.IntAverager;

import java.util.ArrayList;

/**
 * Created by forrest on 2/12/17.
 */
public class WeatherDay implements IForecastDay {
    private ArrayList<WeatherHour> mWeatherHours;

    public WeatherDay(ArrayList<WeatherHour> weatherHours) {
        this.mWeatherHours = weatherHours;
    }

    @Override
    public int getHighTemp() {
        return mWeatherHours.stream().max((x1, x2) -> Integer.compare(x1.getDryBulbTemp(), x2.getDryBulbTemp())).get().getDryBulbTemp();
    }

    @Override
    public int getLowTemp() {
        return mWeatherHours.stream().min((x1, x2) -> Integer.compare(x1.getDryBulbTemp(), x2.getDryBulbTemp())).get().getDryBulbTemp();
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
    public ArrayList<WeatherHour> getWeatherHours() {
        return mWeatherHours;
    }

    @Override
    public WeatherHour getHighTempHour() {
        return mWeatherHours.stream().max((x1, x2) -> Integer.compare(x1.getDryBulbTemp(), x2.getDryBulbTemp())).get();
    }

    @Override
    public WeatherHour getLowTempHour() {
        return mWeatherHours.stream().min((x1, x2) -> Integer.compare(x1.getDryBulbTemp(), x2.getDryBulbTemp())).get();
    }
}

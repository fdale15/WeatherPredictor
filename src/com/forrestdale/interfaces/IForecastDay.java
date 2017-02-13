package com.forrestdale.interfaces;

import com.forrestdale.models.WeatherHour;

import java.util.ArrayList;

/**
 * Created by forrest on 2/12/17.
 */
public interface IForecastDay {
    public int getHighTemp();
    public int getLowTemp();
    public double getAvgTemp();
    public double getAvgVisibility();
    public double getAvgRelativeHumidity();
    public double getAvgWindSpeed();
    public double getAvgWindDir();
    public double getAvgStationPressure();
    
    public ArrayList<WeatherHour> getWeatherHours();
    public WeatherHour getHighTempHour();
    public WeatherHour getLowTempHour();
}

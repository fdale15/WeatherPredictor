package com.forrestdale.interfaces;

import com.forrestdale.models.WeatherHour;
import com.forrestdale.models.WeatherSkyCondition;

import java.util.Date;
import java.util.List;

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
    public WeatherSkyCondition getAvgSkyCondition();
    
    public List<WeatherHour> getWeatherHours();
    public WeatherHour getHighTempHour();
    public WeatherHour getLowTempHour();

    public Date getDate();
    public Date getDay();
}

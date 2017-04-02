package com.forrestdale.models;

import com.forrestdale.utils.WeatherSkyConditionConverter;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by forrest on 2/12/17.
 */
public class WeatherHour {
    private Date mDate;
    private ArrayList<WeatherSkyCondition> mWeatherSkyConditions;
    private double mVisibility;
    private int mDryBulbTemp;
    private int mWetBulbTemp;
    private int mDewPoint;
    private int mRelativeHumidity;
    private int mWindSpeed;
    private int mWindDirection;
    private double mStationPressure;

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(mDate.getHours() + ":00");
        sb.append("\t");
        sb.append("Sky Conditions: " + WeatherSkyConditionConverter.ConvertToHumanReadable(mWeatherSkyConditions));
        sb.append("\t");
        sb.append("Visibility: " + mVisibility);
        sb.append("\t");
        sb.append("Temperature: " + mDryBulbTemp);
        sb.append("\t");

        return sb.toString();
    }

    public WeatherHour() {

    }

    public Date getDate() {
        return mDate;
    }

    public Long getDay() {
        return mDate.getTime();
    }

    public String getKey() {
        Date keyDate = new Date(mDate.getTime());
        keyDate.setYear(0);
        return new SimpleDateFormat("MM.dd.yyyy").format(keyDate);
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public ArrayList<WeatherSkyCondition> getWeatherSkyConditions() {
        return mWeatherSkyConditions;
    }

    public void setWeatherSkyConditions(ArrayList<WeatherSkyCondition> mWeatherSkyConditions) {
        this.mWeatherSkyConditions = mWeatherSkyConditions;
    }

    public double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(double mVisibility) {
        this.mVisibility = mVisibility;
    }

    public int getDryBulbTemp() {
        return mDryBulbTemp;
    }

    public void setDryBulbTemp(int mDryBulbTemp) {
        this.mDryBulbTemp = mDryBulbTemp;
    }

    public int getWetBulbTemp() {
        return mWetBulbTemp;
    }

    public void setWetBulbTemp(int mWetBulbTemp) {
        this.mWetBulbTemp = mWetBulbTemp;
    }

    public int getDewPoint() {
        return mDewPoint;
    }

    public void setDewPoint(int mDewPoint) {
        this.mDewPoint = mDewPoint;
    }

    public int getRelativeHumidity() {
        return mRelativeHumidity;
    }

    public void setRelativeHumidity(int mRelativeHumidity) {
        this.mRelativeHumidity = mRelativeHumidity;
    }

    public int getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(int mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public int getWindDirection() {
        return mWindDirection;
    }

    public void setWindDirection(int mWindDirection) {
        this.mWindDirection = mWindDirection;
    }

    public double getStationPressure() {
        return mStationPressure;
    }

    public void setStationPressure(double mStationPressure) {
        this.mStationPressure = mStationPressure;
    }

    public WeatherSkyCondition getAvgWeatherSkyCondition() {
        Map<WeatherSkyCondition, Integer> conditionMap = new HashMap<>();

        for (WeatherSkyCondition condition : mWeatherSkyConditions) {
            if (conditionMap.containsKey(condition)) {
                Integer count = conditionMap.get(condition);
                conditionMap.put(condition, count + 1);
            }
            else {
                conditionMap.put(condition, 1);
            }
        }

        WeatherSkyCondition most = null;
        int count = 0;
        for (Map.Entry<WeatherSkyCondition, Integer> pair : conditionMap.entrySet()) {
            if (pair.getValue() > count) {
                count = pair.getValue();
                most = pair.getKey();
            }
        }
        return most;
    }
}

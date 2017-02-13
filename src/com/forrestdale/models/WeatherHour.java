package com.forrestdale.models;

import jdk.nashorn.internal.parser.DateParser;

import java.util.ArrayList;
import java.util.Date;

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

    public WeatherHour() {

    }

    public Date getDate() {
        return mDate;
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
}

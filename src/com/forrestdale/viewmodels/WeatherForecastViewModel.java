package com.forrestdale.viewmodels;

import com.forrestdale.ObserverBase;
import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.utils.DateUtil;
import com.forrestdale.utils.MessageUtil;
import com.sun.media.sound.InvalidFormatException;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jesse on 4/8/2017.
 */
public class WeatherForecastViewModel extends WeatherViewModelBase {
    public static final String DETAILED_FORECAST_PROPERTY = "DETAILED_FORECAST";
    public static final String DURATION_PROPERTY = "DURATION";
    public static final String FORECAST_DAYS_PROPERTY = "FORECAST_DAYS";


    private IForecastPredictor mPredictor;

    private boolean mDetailedForecast = false;
    private int mDuration = 4;
    private java.util.List<IForecastDay> mForecastDays = new ArrayList<>();
    private IForecastDay mSelectedDay;

    public WeatherForecastViewModel(IForecastPredictor predictor) {
        mPredictor = predictor;
    }

    //Commands
    public void updateForecastCommand() {
        System.out.println("Update forecast command.");

        try {
            System.out.println("SelectedDate: " + mSelectedDate);
            SimpleDateFormat df = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
            Date date = new Date(df.parse(mSelectedDate).getTime());
            mSelectedDateObject = new Date(date.getTime());
            Date endDate = DateUtil.deltaDayDate(mSelectedDateObject, mDuration);
            setForecastDays(mPredictor.PredictForecastRange(mSelectedDateObject, endDate));
        } catch (ParseException ex) {
            MessageUtil.displayInvalidDateFormat();
        }
    }

    //Getters
    public boolean isDetailedForecast() {
        return mDetailedForecast;
    }

    public int getDuration() {
        return mDuration;
    }

    public java.util.List<IForecastDay> getForecastDays() {
        return mForecastDays;
    }

    public IForecastDay getSelectedDay() {
        return mSelectedDay;
    }

    //Setters
    public void setDetailedForecast(boolean x) {
        mDetailedForecast = x;
        notifySubscribers(DETAILED_FORECAST_PROPERTY);
    }

    public void setDuration(int x) {
        mDuration = x;
        notifySubscribers(DURATION_PROPERTY);
    }

    public void setForecastDays(java.util.List<IForecastDay> forecastDays) {
        System.out.println("Setting forecast days.");
        mForecastDays = forecastDays;
        notifySubscribers(FORECAST_DAYS_PROPERTY);
    }

    public void setSelectedDay(IForecastDay selectedDay) {
        this.mSelectedDay = selectedDay;
    }
}

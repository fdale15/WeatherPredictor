package com.forrestdale.viewmodels;

import com.forrestdale.ObserverBase;
import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.models.WeatherDay;
import com.forrestdale.utils.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jesse on 3/27/2017.
 */
public class WeatherSingleViewModel extends ObserverBase {
    public static final String FORECAST_DAY_PROPERTY = "FORECAST_DAY";
    public static final String SELECTED_DATE_PROPERTY = "SELECTED_DATE";

    private IForecastPredictor mPredictor;
    private IForecastDay mForecastDay;
    private String mSelectedDate = "";
    private Date mSelectedDateObject = new Date();

    public WeatherSingleViewModel(IForecastPredictor predictor, IForecastDay weatherDay) {
        mPredictor = predictor;
        setForecastDay(weatherDay);
    }

    public void setForecastDay(IForecastDay forecastDay) {
        mForecastDay = forecastDay;
        notifySubscribers(FORECAST_DAY_PROPERTY);
    }

    public void setSelectedDate(String date) {
        mSelectedDate = date;
        notifySubscribers(SELECTED_DATE_PROPERTY);
    }

    public void nextDateCommand() {
        System.out.println("Next date command.");
        mSelectedDateObject = DateUtil.deltaDayDate(mSelectedDateObject, 1);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        setSelectedDate(df.format(mSelectedDateObject));
        System.out.println(df.format(mSelectedDateObject));
        setForecastDay(mPredictor.PredictForecastDay(mSelectedDateObject));
    }

    public void previousDateCommand() {
        System.out.println("Previous date command.");
        mSelectedDateObject = DateUtil.deltaDayDate(mSelectedDateObject, -1);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        setSelectedDate(df.format(mSelectedDateObject));
        System.out.println(df.format(mSelectedDateObject));
        setForecastDay(mPredictor.PredictForecastDay(mSelectedDateObject));
    }

    public void selectDateCommand() {
        System.out.println("Select date command.");

        try {
            System.out.println("SelectedDate: " + mSelectedDate);
            SimpleDateFormat df = new SimpleDateFormat("MM/dd", Locale.ENGLISH);
            Date date = new Date(df.parse(mSelectedDate).getTime());
            mSelectedDateObject = new Date(date.getTime());
            IForecastDay day = mPredictor.PredictForecastDay(date);
            setForecastDay(day);
        } catch (Exception ex) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Invalid Date");
            dialog.add(new Label("Date must be in format MM/dd"));
            dialog.setSize(250, 100);
            dialog.setVisible(true);
        }
    }

    public void forecastCommand() {
        System.out.println("Forecast command.");
    }

    public IForecastDay getForecastDay() {
        return mForecastDay;
    }

    public String getSelectedDate() {
        return mSelectedDate;
    }
}

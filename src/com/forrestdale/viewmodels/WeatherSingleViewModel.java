package com.forrestdale.viewmodels;

import com.forrestdale.ObserverBase;
import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.models.WeatherDay;
import com.forrestdale.utils.DateUtil;
import com.forrestdale.utils.MessageUtil;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.forrestdale.viewmodels.WeatherSingleViewModel.DescriptionType.HOURLY;
import static com.forrestdale.viewmodels.WeatherSingleViewModel.DescriptionType.TRI_DAILY;

/**
 * Created by jesse on 3/27/2017.
 */
public class WeatherSingleViewModel extends WeatherViewModelBase {
    public static final String FORECAST_DAY_PROPERTY = "FORECAST_DAY";
    public static final String DESCRIPTION_PROPERTY = "DESCRIPTION";

    private IForecastPredictor mPredictor;
    private IForecastDay mForecastDay;

    private DescriptionType mDescriptionType = HOURLY;

    public enum DescriptionType {
        TRI_DAILY,
        HOURLY,
    }

    public WeatherSingleViewModel(IForecastPredictor predictor, IForecastDay weatherDay) {
        mPredictor = predictor;
        mSelectedDate = DateUtil.getDay(weatherDay.getDay());
        mSelectedDateObject = weatherDay.getDay();
        setForecastDay(weatherDay);
    }

    //Setters
    public void setForecastDay(IForecastDay forecastDay) {
        mForecastDay = forecastDay;
        notifySubscribers(FORECAST_DAY_PROPERTY);
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.mDescriptionType = descriptionType;
        notifySubscribers(DESCRIPTION_PROPERTY);
    }

    //Commands
    public void updateDescriptionCommand() {
        setDescriptionType(mDescriptionType == DescriptionType.TRI_DAILY ? HOURLY : DescriptionType.TRI_DAILY);
    };

    //Commands
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
        } catch (ParseException ex) {
            MessageUtil.displayInvalidDateFormat();
        }
    }

    //Getters
    public IForecastDay getForecastDay() {
        return mForecastDay;
    }

    public DescriptionType getDescriptionType() {
        return mDescriptionType;
    }
}

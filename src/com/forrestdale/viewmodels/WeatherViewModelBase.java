package com.forrestdale.viewmodels;

import com.forrestdale.ObserverBase;
import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.utils.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jesse on 4/8/2017.
 */
public class WeatherViewModelBase extends ObserverBase {
    public static final String SELECTED_DATE_PROPERTY = "SELECTED_DATE";

    protected String mSelectedDate = "";
    protected Date mSelectedDateObject = new Date();

    public WeatherViewModelBase() {

    }

    //Setters
    public void setSelectedDate(String date) {
        mSelectedDate = date;
        notifySubscribers(SELECTED_DATE_PROPERTY);
    }

    //Getters
    public String getSelectedDate() {
        return mSelectedDate;
    }
}

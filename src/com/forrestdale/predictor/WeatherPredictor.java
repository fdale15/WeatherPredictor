package com.forrestdale.predictor;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.models.WeatherDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by forrest on 2/26/17.
 */
public class WeatherPredictor implements IForecastPredictor {
    private static final int DAY_MILLIS = 86400000;

    private Map<String, IForecastDay> mForecastDays;

    public WeatherPredictor(Map<String, IForecastDay> forecastDays) {
        mForecastDays = forecastDays;
    }

    //TODO: Actually implement the prediction.
    @Override
    public List<IForecastDay> PredictForecastRange(Date startDate, Date endDate) {
        List<IForecastDay> result = new ArrayList<>();

        SimpleDateFormat fmt = new SimpleDateFormat("MM.dd.yyyy");

        do {
            result.add(PredictForecastDay(startDate));
            startDate.setTime(startDate.getTime() + DAY_MILLIS);
        } while (startDate.compareTo(endDate) <= 0);

        return result;
    }

    @Override
    public IForecastDay PredictForecastDay(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("MM.dd.yyyy");
        IForecastDay pastYesterday = mForecastDays.get(fmt.format(date.getTime() - DAY_MILLIS));
        IForecastDay pastDay = mForecastDays.get(fmt.format(date));
        IForecastDay pastTomorrow = mForecastDays.get(fmt.format(date.getTime() + DAY_MILLIS));

        WeatherDay result = new WeatherDay();
        if (pastDay != null) {
            result.addWeatherHours(pastDay.getWeatherHours());
        }
        if (pastYesterday != null) {
            result.addWeatherHours(pastYesterday.getWeatherHours());
        }
        if (pastTomorrow != null) {
            result.addWeatherHours(pastTomorrow.getWeatherHours());
        }

        return result;
    }
}

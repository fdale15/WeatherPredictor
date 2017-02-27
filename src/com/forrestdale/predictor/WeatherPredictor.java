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
    private Map<String, IForecastDay> mForecastDays;

    public WeatherPredictor(Map<String, IForecastDay> forecastDays) {
        mForecastDays = forecastDays;
    }

    //TODO: Actually implement the prediction.
    @Override
    public List<IForecastDay> GetForecastRange(Date startDate, Date endDate) {
        List<IForecastDay> result = new ArrayList<>();

        Date currentDate = startDate;
        SimpleDateFormat fmt = new SimpleDateFormat("MM.dd.yyyy");

        do {
            IForecastDay day = mForecastDays.get(fmt.format(startDate));

            if (day != null) {
                result.add(day);
            }
            startDate.setTime(startDate.getTime() + 86400000);
        } while (startDate.compareTo(endDate) <= 0);

        return result;
    }

    //TODO: Actually implement the prediction.
    @Override
    public IForecastDay GetForecastDay(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("MM.dd.yyyy");

        IForecastDay result = mForecastDays.get(fmt.format(date));

        return result;
    }
}

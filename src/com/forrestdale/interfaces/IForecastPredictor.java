package com.forrestdale.interfaces;

import java.util.Date;
import java.util.List;

/**
 * Created by forrest on 2/26/17.
 */
public interface IForecastPredictor {
    List<IForecastDay> PredictForecastRange(Date startDate, Date endDate);
    IForecastDay PredictForecastDay(Date date);
}

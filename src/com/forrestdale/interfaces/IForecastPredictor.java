package com.forrestdale.interfaces;

import java.util.Date;
import java.util.List;

/**
 * Created by forrest on 2/26/17.
 */
public interface IForecastPredictor {
    List<IForecastDay> GetForecastRange(Date startDate, Date endDate);
    IForecastDay GetForecastDay(Date date);
}

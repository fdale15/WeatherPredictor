package com.forrestdale.utils;

import com.forrestdale.models.WeatherSkyCondition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by forrest on 2/26/17.
 */
public class WeatherSkyConditionConverter {
    public static ArrayList<WeatherSkyCondition> ConvertToWeatherSkyCondition(String skyConditions) {
        ArrayList<WeatherSkyCondition> result = new ArrayList<>();

        if (skyConditions.contains("OVC")) {
            result.add(WeatherSkyCondition.OVERCAST);
        }
        if (skyConditions.contains("CLR")) {
            result.add(WeatherSkyCondition.CLEAR);
        }
        if (skyConditions.contains("SCT")) {
            result.add(WeatherSkyCondition.SCATTERED);
        }
        if (skyConditions.contains("BKN")) {
            result.add(WeatherSkyCondition.BROKEN);
        }
        if (skyConditions.contains("FEW")) {
            result.add(WeatherSkyCondition.FEW);
        }

        return result;
    }

    public static String[] ConvertFromWeatherSkyConditions(ArrayList<WeatherSkyCondition> skyConditions) {
        //TODO: Implement later if necessary.
        throw new NotImplementedException();
    }

    public static String ConvertToHumanReadable(ArrayList<WeatherSkyCondition> skyConditions) {
        StringBuilder sb = new StringBuilder();

        for (WeatherSkyCondition sc : skyConditions) {
            sb.append(sc.toString());
            sb.append(", ");
        }

        return sb.toString();
    }
}

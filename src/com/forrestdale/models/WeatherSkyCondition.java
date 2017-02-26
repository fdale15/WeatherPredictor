package com.forrestdale.models;

/**
 * Created by forrest on 2/12/17.
 */
public enum WeatherSkyCondition {
    OVERCAST("Overcast"),
    BROKEN("Broken"),
    SCATTERED("Scattered"),
    FEW("Few"),
    CLEAR("Clear");

    private String mCondition = "";

    WeatherSkyCondition(String condition) {
        mCondition = condition;
    }

    public String toString() {
        return mCondition;
    }
}

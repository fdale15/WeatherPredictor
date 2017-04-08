package com.forrestdale.utils;

/**
 * Created by jesse on 4/8/2017.
 */
public class WindConverter {
    public static String getWindString(int speed, int direction) {
        String dirString = "";

        if (direction > 305 && direction <= 45) {
            dirString += "N";
        }
        if (direction > 135 && direction <= 225) {
            dirString += "S";
        }
        if (direction > 45 && direction <= 135) {
             dirString += "E";
        }
        if (direction > 225 && direction <= 305) {
            dirString += "W";
        }

        return speed + " mph " + dirString;
    }
}

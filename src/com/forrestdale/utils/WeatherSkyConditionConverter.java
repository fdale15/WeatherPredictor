package com.forrestdale.utils;

import com.forrestdale.models.WeatherSkyCondition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private static Map<String, ImageIcon> imgCache = new HashMap<>();

    public static ImageIcon GetImageForSkyCondition(WeatherSkyCondition skyCondition) {
        String imgString = "src/com/forrestdale/images/";
        switch (skyCondition) {
            case BROKEN:
                imgString += "broken.jpg";
                break;
            case CLEAR:
                imgString += "clear.jpg";
                break;
            case FEW:
            case SCATTERED:
                imgString += "scattered.jpg";
                break;
            case OVERCAST:
                imgString += "overcast.jpg";
                break;
        }

        //If we have the image in cache, load it.
        //Simple but not effective enough. The bottle neck is resizing the images.
        if (imgCache.containsKey(imgString)) {
            System.out.println("Loaded image from cache.");
            return imgCache.get(imgString);
        }

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgCache.put(imgString, new ImageIcon(img));
        return imgCache.get(imgString);
    }
}

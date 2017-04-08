package com.forrestdale.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jesse on 4/8/2017.
 */
public class MessageUtil {
    public static void displayInvalidDateFormat() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Invalid Date");
        dialog.add(new Label("Date must be in format MM/dd"));
        dialog.setSize(250, 100);
        dialog.setVisible(true);
    }
}

package com.forrestdale.views;

import com.forrestdale.ObserverBase;
import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.ISubscriber;
import com.forrestdale.models.WeatherHour;
import com.forrestdale.models.WeatherSkyCondition;
import com.forrestdale.utils.WeatherSkyConditionConverter;
import com.forrestdale.viewmodels.WeatherSingleViewModel;
import javafx.scene.control.DatePicker;
import sun.awt.im.InputMethodAdapter;
import sun.plugin.javascript.JSClassLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WeatherSingleView extends JFrame implements ISubscriber {
    private JTextField dateField;
    private JPanel controlsPanel;
    private JButton btnPrevious;
    private JButton btnSelectDate;
    private JButton btnNext;
    private JButton btnForecast;
    private JPanel weatherInfoPanel;
    private JLabel lblDatevalue;
    private JLabel lblHighvalue;
    private JLabel lblLowvalue;
    private JLabel lblAveragevalue;
    private JLabel lblVisibilityvalue;
    private JLabel lblRhvalue;
    private JLabel lblBarometervalue;
    private JLabel lblWindspeedvalue;
    private JLabel lblWinddirvalue;

    private WeatherSingleViewModel mViewModel;
    private JList hourlyList;
    private JScrollPane hourlyScrollPane;
    private JLabel weatherImage;

    /**
     * Create the panel.
     */
    public WeatherSingleView(WeatherSingleViewModel viewModel) {
        initializeView();
        mViewModel = viewModel;
        viewModel.subscribe(this);
        initializeCommands();

        dateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                mViewModel.setSelectedDate(dateField.getText());
            }
        });
    }

    private void initializeView() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setSize(800, 500);

        controlsPanel = new JPanel();
        controlsPanel.setBounds(10, 5, 800, 33);
        add(controlsPanel);
        controlsPanel.setLayout(new GridLayout(0, 4, 0, 0));

        btnPrevious = new JButton("Previous");
        controlsPanel.add(btnPrevious);

        dateField = new JTextField();
        controlsPanel.add(dateField);
        dateField.setColumns(10);

        btnSelectDate = new JButton("Select Date");
        controlsPanel.add(btnSelectDate);

        btnNext = new JButton("Next");
        controlsPanel.add(btnNext);

        weatherInfoPanel = new JPanel();
        weatherInfoPanel.setSize(new Dimension(200, 300));
        weatherInfoPanel.setBounds(new Rectangle(10, 49, 351, 397));
        add(weatherInfoPanel);
        GridBagLayout gbl_weatherInfoPanel = new GridBagLayout();
        gbl_weatherInfoPanel.columnWidths = new int[]{39, 35, 64, 65, 130, 90, 110, 79, 38, 0};
        gbl_weatherInfoPanel.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_weatherInfoPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_weatherInfoPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        weatherInfoPanel.setLayout(gbl_weatherInfoPanel);

        JLabel label_8 = new JLabel("Date:");
        label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_8 = new GridBagConstraints();
        gbc_label_8.insets = new Insets(0, 0, 5, 5);
        gbc_label_8.gridx = 4;
        gbc_label_8.gridy = 1;
        weatherInfoPanel.add(label_8, gbc_label_8);

        lblDatevalue = new JLabel("dateValue");
        GridBagConstraints gbc_lblDatevalue = new GridBagConstraints();
        gbc_lblDatevalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblDatevalue.gridx = 5;
        gbc_lblDatevalue.gridy = 1;
        weatherInfoPanel.add(lblDatevalue, gbc_lblDatevalue);

        JLabel label = new JLabel("High:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 4;
        gbc_label.gridy = 3;
        weatherInfoPanel.add(label, gbc_label);

        lblHighvalue = new JLabel("highValue");
        GridBagConstraints gbc_lblHighvalue = new GridBagConstraints();
        gbc_lblHighvalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblHighvalue.gridx = 5;
        gbc_lblHighvalue.gridy = 3;
        weatherInfoPanel.add(lblHighvalue, gbc_lblHighvalue);

        JLabel label_1 = new JLabel("Low:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.insets = new Insets(0, 0, 5, 5);
        gbc_label_1.gridx = 4;
        gbc_label_1.gridy = 4;
        weatherInfoPanel.add(label_1, gbc_label_1);

        lblLowvalue = new JLabel("lowValue");
        GridBagConstraints gbc_lblLowvalue = new GridBagConstraints();
        gbc_lblLowvalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblLowvalue.gridx = 5;
        gbc_lblLowvalue.gridy = 4;
        weatherInfoPanel.add(lblLowvalue, gbc_lblLowvalue);

        JLabel label_2 = new JLabel("Average:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_2 = new GridBagConstraints();
        gbc_label_2.insets = new Insets(0, 0, 5, 5);
        gbc_label_2.gridx = 4;
        gbc_label_2.gridy = 5;
        weatherInfoPanel.add(label_2, gbc_label_2);

        lblAveragevalue = new JLabel("averageValue");
        GridBagConstraints gbc_lblAveragevalue = new GridBagConstraints();
        gbc_lblAveragevalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblAveragevalue.gridx = 5;
        gbc_lblAveragevalue.gridy = 5;
        weatherInfoPanel.add(lblAveragevalue, gbc_lblAveragevalue);

        JLabel label_3 = new JLabel("Visibility:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_3 = new GridBagConstraints();
        gbc_label_3.insets = new Insets(0, 0, 5, 5);
        gbc_label_3.gridx = 4;
        gbc_label_3.gridy = 7;
        weatherInfoPanel.add(label_3, gbc_label_3);

        lblVisibilityvalue = new JLabel("visibilityValue");
        GridBagConstraints gbc_lblVisibilityvalue = new GridBagConstraints();
        gbc_lblVisibilityvalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblVisibilityvalue.gridx = 5;
        gbc_lblVisibilityvalue.gridy = 7;
        weatherInfoPanel.add(lblVisibilityvalue, gbc_lblVisibilityvalue);

        JLabel label_4 = new JLabel("Relative Humidity:");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_4 = new GridBagConstraints();
        gbc_label_4.insets = new Insets(0, 0, 5, 5);
        gbc_label_4.gridx = 4;
        gbc_label_4.gridy = 9;
        weatherInfoPanel.add(label_4, gbc_label_4);

        lblRhvalue = new JLabel("rhValue");
        GridBagConstraints gbc_lblRhvalue = new GridBagConstraints();
        gbc_lblRhvalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblRhvalue.gridx = 5;
        gbc_lblRhvalue.gridy = 9;
        weatherInfoPanel.add(lblRhvalue, gbc_lblRhvalue);

        JLabel label_7 = new JLabel("Barometer:");
        label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_7 = new GridBagConstraints();
        gbc_label_7.insets = new Insets(0, 0, 5, 5);
        gbc_label_7.gridx = 4;
        gbc_label_7.gridy = 10;
        weatherInfoPanel.add(label_7, gbc_label_7);

        lblBarometervalue = new JLabel("barometerValue");
        GridBagConstraints gbc_lblBarometervalue = new GridBagConstraints();
        gbc_lblBarometervalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblBarometervalue.gridx = 5;
        gbc_lblBarometervalue.gridy = 10;
        weatherInfoPanel.add(lblBarometervalue, gbc_lblBarometervalue);

        JLabel label_5 = new JLabel("Wind Speed:");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_5 = new GridBagConstraints();
        gbc_label_5.insets = new Insets(0, 0, 5, 5);
        gbc_label_5.gridx = 4;
        gbc_label_5.gridy = 11;
        weatherInfoPanel.add(label_5, gbc_label_5);

        lblWindspeedvalue = new JLabel("windSpeedValue");
        GridBagConstraints gbc_lblWindspeedvalue = new GridBagConstraints();
        gbc_lblWindspeedvalue.insets = new Insets(0, 0, 5, 5);
        gbc_lblWindspeedvalue.gridx = 5;
        gbc_lblWindspeedvalue.gridy = 11;
        weatherInfoPanel.add(lblWindspeedvalue, gbc_lblWindspeedvalue);

        JLabel label_6 = new JLabel("Wind Direction:");
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_label_6 = new GridBagConstraints();
        gbc_label_6.insets = new Insets(0, 0, 0, 5);
        gbc_label_6.gridx = 4;
        gbc_label_6.gridy = 12;
        weatherInfoPanel.add(label_6, gbc_label_6);

        lblWinddirvalue = new JLabel("windDirValue");
        GridBagConstraints gbc_lblWinddirvalue = new GridBagConstraints();
        gbc_lblWinddirvalue.insets = new Insets(0, 0, 0, 5);
        gbc_lblWinddirvalue.gridx = 5;
        gbc_lblWinddirvalue.gridy = 12;
        weatherInfoPanel.add(lblWinddirvalue, gbc_lblWinddirvalue);

        weatherImage = new JLabel(WeatherSkyConditionConverter.GetImageForSkyCondition(WeatherSkyCondition.CLEAR));
        weatherImage.setSize(300, 200);
        weatherImage.setLocation(400, 75);
        add(weatherImage);

        hourlyList = new JList();

        hourlyScrollPane = new JScrollPane(hourlyList);
        hourlyScrollPane.setLocation(0, 300);
        hourlyScrollPane.setSize(750, 200);
        add(hourlyScrollPane);


    }

    private void initializeCommands() {
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mViewModel.nextDateCommand();
            }
        });

        btnPrevious.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mViewModel.previousDateCommand();
            }
        });

        btnSelectDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mViewModel.selectDateCommand();
            }
        });
    }

    //Initialize primitive databinding here.
    @Override
    public void onPropertyChange(String property) {
        if (property.equals(WeatherSingleViewModel.FORECAST_DAY_PROPERTY)) {
            IForecastDay day = mViewModel.getForecastDay();
            //high
            lblHighvalue.setText(Integer.toString(day.getHighTemp()));
            //low
            lblLowvalue.setText(Integer.toString(day.getLowTemp()));
            //average
            lblAveragevalue.setText(Double.toString(day.getAvgTemp()));
            //visibility
            lblVisibilityvalue.setText(Double.toString(day.getAvgVisibility()));
            //rh
            lblRhvalue.setText(Double.toString(day.getAvgRelativeHumidity()));
            //barometer
            lblBarometervalue.setText(Double.toString(day.getAvgStationPressure()));
            //windspeed
            lblWindspeedvalue.setText(Double.toString(day.getAvgWindSpeed()));
            //winddir
            lblWinddirvalue.setText(Double.toString(day.getAvgWindDir()));

            hourlyList.setListData(mViewModel.getForecastDay().getWeatherHours().toArray());

            lblDatevalue.setText(dateField.getText());

            weatherImage.setIcon(WeatherSkyConditionConverter.GetImageForSkyCondition(mViewModel.getForecastDay().getAvgSkyCondition()));
        }
        else if (property.equals(WeatherSingleViewModel.SELECTED_DATE_PROPERTY)) {
            dateField.setText(mViewModel.getSelectedDate());
            lblDatevalue.setText(mViewModel.getSelectedDate());
        }
    }
}

package com.forrestdale.views;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.ISubscriber;
import com.forrestdale.models.WeatherHour;
import com.forrestdale.models.WeatherSkyCondition;
import com.forrestdale.utils.DateUtil;
import com.forrestdale.utils.WeatherSkyConditionConverter;
import com.forrestdale.utils.WindConverter;
import com.forrestdale.viewmodels.WeatherSingleViewModel;
import com.forrestdale.views.controls.WeatherHourPane;
import com.forrestdale.views.controls.WeatherTriDailyPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

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
    private JLabel weatherImage;

    private JRadioButton btnTriDailyDescription;
    private JRadioButton btnHourlyDescription;

    private JPanel triDailyList;
    private JScrollPane triDailyScrollPane;

    private JPanel hourlyList;
    private JScrollPane hourlyScrollPane;

    private WeatherSingleViewModel mViewModel;

    /**
     * Create the panel.
     */
    public WeatherSingleView(WeatherSingleViewModel viewModel) {
        mViewModel = viewModel;
        viewModel.subscribe(this);
        initializeView();
        initializeCommands();
        initializeDatabinding();
    }

    private void initializeDatabinding() {
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
        setSize(800, 850);

        controlsPanel = new JPanel();
        controlsPanel.setBounds(10, 5, 800, 33);
        add(controlsPanel);
        controlsPanel.setLayout(new GridLayout(0, 4, 0, 0));

        btnPrevious = new JButton("Previous");
        controlsPanel.add(btnPrevious);

        dateField = new JTextField();
        dateField.setText(DateUtil.getDay(mViewModel.getForecastDay().getDay()));
        controlsPanel.add(dateField);
        dateField.setColumns(10);

        btnSelectDate = new JButton("Select Date");
        controlsPanel.add(btnSelectDate);

        btnNext = new JButton("Next");
        controlsPanel.add(btnNext);

        weatherInfoPanel = new JPanel();
        weatherInfoPanel.setSize(new Dimension(200, 300));
        weatherInfoPanel.setBounds(new Rectangle(10, 49, 351, 250));
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

        lblDatevalue = new JLabel(DateUtil.getDay(mViewModel.getForecastDay().getDay()));
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

        lblHighvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getHighTemp()) + "°F");
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

        lblLowvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getLowTemp()) + "°F");
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

        lblAveragevalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgTemp()) + "°F");
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

        lblVisibilityvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgVisibility()) + "mi");
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

        lblRhvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgRelativeHumidity()) + "%");
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

        lblBarometervalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgStationPressure()) + "mmHg");
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

        lblWindspeedvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgWindSpeed()));
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

        lblWinddirvalue = new JLabel(String.valueOf(mViewModel.getForecastDay().getAvgWindDir()));
        GridBagConstraints gbc_lblWinddirvalue = new GridBagConstraints();
        gbc_lblWinddirvalue.insets = new Insets(0, 0, 0, 5);
        gbc_lblWinddirvalue.gridx = 5;
        gbc_lblWinddirvalue.gridy = 12;
        weatherInfoPanel.add(lblWinddirvalue, gbc_lblWinddirvalue);

        weatherImage = new JLabel(WeatherSkyConditionConverter.GetImageForSkyCondition(mViewModel.getForecastDay().getAvgSkyCondition()));
        weatherImage.setSize(300, 200);
        weatherImage.setLocation(400, 75);
        add(weatherImage);

        hourlyList = new JPanel();
        hourlyList.setLayout(new FlowLayout());
        addWeatherHourPanes(mViewModel.getForecastDay().getWeatherHours());

        hourlyScrollPane = new JScrollPane(hourlyList);
        hourlyScrollPane.setLocation(0, 300);
        hourlyScrollPane.setSize(780, 450);
        add(hourlyScrollPane);

        triDailyList = new JPanel();
        triDailyList.setLayout(new FlowLayout());
        addWeatherTriDailyPanes(mViewModel.getForecastDay().getWeatherHours());

        triDailyScrollPane = new JScrollPane(triDailyList);
        triDailyScrollPane.setLocation(0, 300);
        triDailyScrollPane.setSize(780, 450);
        add(triDailyScrollPane);

        btnTriDailyDescription = new JRadioButton("Tridaily");
        btnTriDailyDescription.setSize(100,25);
        btnTriDailyDescription.setLocation(300, 755);
        add(btnTriDailyDescription);

        btnHourlyDescription = new JRadioButton("Hourly");
        btnHourlyDescription.setLocation(400, 755);
        btnHourlyDescription.setSize(100, 25);
        btnHourlyDescription.setSelected(true);
        add(btnHourlyDescription);
    }

    private void addWeatherTriDailyPanes(List<WeatherHour> weatherHours) {
        //Could use some protection here, but I think the data is clean enough.
        WeatherHour whMorning = weatherHours.get(8);
        WeatherTriDailyPane wpMorning = getPaneForWeatherTriDaily(whMorning);

        WeatherHour whNoon = weatherHours.get(13);
        WeatherTriDailyPane wpNoon = getPaneForWeatherTriDaily(whNoon);

        WeatherHour whEvening = weatherHours.get(16);
        WeatherTriDailyPane wpEvening = getPaneForWeatherTriDaily(whEvening);

        triDailyList.add(wpMorning);
        triDailyList.add(wpNoon);
        triDailyList.add(wpEvening);
    }

    private WeatherTriDailyPane getPaneForWeatherTriDaily(WeatherHour wh) {
        return new WeatherTriDailyPane(DateUtil.getTime(wh.getDate()),
                Double.toString(wh.getStationPressure()) + " mmHg",
                Integer.toString(wh.getRelativeHumidity()) + "% RH",
                "Vis:" + Double.toString(wh.getVisibility()) + " Mi",
                "Wind: " + WindConverter.getWindString(wh.getWindSpeed(), wh.getWindDirection()),
                Integer.toString(wh.getDryBulbTemp()) + "°F",
                WeatherSkyConditionConverter.GetImageForSkyCondition(wh.getAvgWeatherSkyCondition()));
    }

    private void addWeatherHourPanes(java.util.List<WeatherHour> hours) {
        int i = 0;
        for (WeatherHour wh : hours) {
            i++;
            WeatherHourPane whp =  new WeatherHourPane(DateUtil.getTime(wh.getDate()),
                    "Wind:"+    WindConverter.getWindString(wh.getWindSpeed(), wh.getWindDirection()),
                    "Vis:" + Double.toString(wh.getVisibility()) + " Mi",
                    Integer.toString(wh.getDryBulbTemp()) + "°F",
                    WeatherSkyConditionConverter.GetImageForSkyCondition(wh.getAvgWeatherSkyCondition()));
            hourlyList.add(whp);

            if (i >= 24) {
                break;
            }
        }
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

        MouseAdapter descriptionMouseListener = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                WeatherSingleViewModel.DescriptionType type = mViewModel.getDescriptionType();

                if (type == WeatherSingleViewModel.DescriptionType.HOURLY && e.getSource() == btnHourlyDescription) {
                    return;
                }
                if (type == WeatherSingleViewModel.DescriptionType.TRI_DAILY && e.getSource() == btnTriDailyDescription) {
                    return;
                }

                mViewModel.updateDescriptionCommand();
            }
        };

        btnHourlyDescription.addMouseListener(descriptionMouseListener);
        btnTriDailyDescription.addMouseListener(descriptionMouseListener);
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

            lblDatevalue.setText(dateField.getText());

            weatherImage.setIcon(WeatherSkyConditionConverter.GetImageForSkyCondition(mViewModel.getForecastDay().getAvgSkyCondition()));

            hourlyList.removeAll();
            addWeatherHourPanes(mViewModel.getForecastDay().getWeatherHours());

            triDailyList.removeAll();
            addWeatherTriDailyPanes(mViewModel.getForecastDay().getWeatherHours());
        }
        else if (property.equals(WeatherSingleViewModel.SELECTED_DATE_PROPERTY)) {
            dateField.setText(mViewModel.getSelectedDate());
            lblDatevalue.setText(mViewModel.getSelectedDate());
        }
        else if (property.equals(WeatherSingleViewModel.DESCRIPTION_PROPERTY)) {
            WeatherSingleViewModel.DescriptionType type = mViewModel.getDescriptionType();

            btnTriDailyDescription.setSelected(false);
            btnHourlyDescription.setSelected(false);
            hourlyScrollPane.setVisible(false);
            triDailyScrollPane.setVisible(false);


            if (type.equals(WeatherSingleViewModel.DescriptionType.TRI_DAILY)) {
                //Set tri daily radio button.
                btnTriDailyDescription.setSelected(true);
                //Setup the tri daily description.
                triDailyList.removeAll();
                addWeatherTriDailyPanes(mViewModel.getForecastDay().getWeatherHours());
                triDailyList.setVisible(true);
                triDailyScrollPane.setVisible(true);
            }
            else {
                //Set hourly radio button.
                btnHourlyDescription.setSelected(true);
                //Setup the weather hour list.
                hourlyList.removeAll();
                addWeatherHourPanes(mViewModel.getForecastDay().getWeatherHours());
                hourlyScrollPane.setVisible(true);
            }
        }
    }
}

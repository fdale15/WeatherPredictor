package com.forrestdale.views;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IObservable;
import com.forrestdale.interfaces.ISubscriber;
import com.forrestdale.models.WeatherSkyCondition;
import com.forrestdale.predictor.WeatherPredictor;
import com.forrestdale.utils.DateUtil;
import com.forrestdale.utils.WeatherSkyConditionConverter;
import com.forrestdale.utils.WindConverter;
import com.forrestdale.viewmodels.MainViewModel;
import com.forrestdale.viewmodels.WeatherForecastViewModel;
import com.forrestdale.views.controls.WeatherDayDetailPane;
import com.forrestdale.views.controls.WeatherDayPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecastView extends JFrame implements ISubscriber, IObservable {
    public static final String VIEW_CLICKED = "VIEW_CLICKED";
    private ArrayList<ISubscriber> subscribers = new ArrayList<>();

    private JTextField dateField;
	private JPanel controlsPanel;
	private JButton getForecastBtn;
	private JSlider durationSlider;
	private JCheckBox detailedForecastBox;
    private JPanel forecastList;
	private WeatherForecastViewModel mViewModel;

	/**
	 * Create the panel.
	 */
	public WeatherForecastView(WeatherForecastViewModel viewModel) {
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

        durationSlider.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                mViewModel.setDuration(durationSlider.getValue());
            }
        });

        detailedForecastBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mViewModel.setDetailedForecast(detailedForecastBox.isSelected());
            }
        });
    }

	private void initializeCommands() {
		getForecastBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mViewModel.updateForecastCommand();
			}
		});
	}

	private void initializeView() {
		setLayout(null);
		setSize(800, 700);

		controlsPanel = new JPanel();
		controlsPanel.setBounds(10, 11, 782, 77);
		add(controlsPanel);
		controlsPanel.setLayout(null);

		JLabel lblEnterDate = new JLabel("Enter Date:");
		lblEnterDate.setBounds(10, 11, 70, 14);
		controlsPanel.add(lblEnterDate);

		dateField = new JTextField();
		dateField.setBounds(76, 8, 153, 20);
		controlsPanel.add(dateField);
		dateField.setColumns(10);



		getForecastBtn = new JButton("Get Forecast");
		getForecastBtn.setBounds(599, 11, 173, 45);
		controlsPanel.add(getForecastBtn);

		durationSlider = new JSlider();
		durationSlider.setMajorTickSpacing(2);
		durationSlider.setSnapToTicks(true);
		durationSlider.setPaintLabels(true);
		durationSlider.setPaintTicks(true);
		durationSlider.setMinorTickSpacing(1);
		durationSlider.setValue(4);
		durationSlider.setMinimum(4);
		durationSlider.setMaximum(14);
		durationSlider.setBounds(305, 11, 284, 45);
		controlsPanel.add(durationSlider);

		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(249, 26, 70, 14);
		controlsPanel.add(lblDuration);

		detailedForecastBox = new JCheckBox("Detailed Forecast");
		detailedForecastBox.setHorizontalAlignment(SwingConstants.LEFT);
		detailedForecastBox.setBounds(76, 36, 150, 23);
		controlsPanel.add(detailedForecastBox);

		forecastList = new JPanel();
		forecastList.setLayout(new FlowLayout());
		forecastList.setSize(800, 580);

		JScrollPane forecastPane = new JScrollPane(forecastList);
		forecastPane.setLocation(0, 100);
		forecastPane.setSize(800, 560);
		forecastPane.setVisible(true);
		add(forecastPane);
	}

	@Override
	public void onPropertyChange(String property) {
		if (property.equals(WeatherForecastViewModel.FORECAST_DAYS_PROPERTY)) {
			//Redraw the forecast days.
            forecastList.removeAll();

            if (mViewModel.isDetailedForecast()) {
                //Do detailed forecast.
                addWeatherDayDetailPanels(mViewModel.getForecastDays());
            }
            else {
                addWeatherDayPanels(mViewModel.getForecastDays());
            }

            revalidate();
            repaint();
		}
	}

    private void addWeatherDayDetailPanels(List<IForecastDay> forecastDays) {
        DecimalFormat format = new DecimalFormat("###.##");
        for (IForecastDay fd : forecastDays) {
            WeatherDayDetailPane detailPane = new WeatherDayDetailPane(DateUtil.getDay(fd.getDate()),
                                                                        "High:" + String.valueOf(fd.getHighTemp()) + "°F",
                                                                        "Low:" + String.valueOf(fd.getLowTemp()) + "°F",
                                                                        WindConverter.getWindString((int)Math.round(fd.getAvgWindSpeed()), (int)Math.round(fd.getAvgWindDir())),
                                                                        "RH:" + format.format(fd.getAvgRelativeHumidity()) + "%",
                                                                        format.format(fd.getAvgStationPressure()) + "mmHg",
                                                                        "Vis:" + format.format(fd.getAvgVisibility()) + "mi",
                                                                        WeatherSkyConditionConverter.GetImageForSkyCondition(fd.getAvgSkyCondition()));
            detailPane.setViewEvent(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    mViewModel.setSelectedDay(fd);
                    notifySubscribers(VIEW_CLICKED);
                }
            });
            forecastList.add(detailPane);
        }
    }

    private void addWeatherDayPanels(List<IForecastDay> forecastDays) {
        for (IForecastDay fd : forecastDays) {
            WeatherDayPane wdp = new WeatherDayPane(DateUtil.getDay(fd.getDate()),
                                                    String.valueOf(fd.getHighTemp()) + "°F",
                                                    WindConverter.getWindString((int)Math.round(fd.getAvgWindSpeed()), (int)Math.round(fd.getAvgWindDir())),
                                                    WeatherSkyConditionConverter.GetImageForSkyCondition(fd.getAvgSkyCondition()));
            wdp.setViewAction(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    mViewModel.setSelectedDay(fd);
                    notifySubscribers(VIEW_CLICKED);
                }
            });
            forecastList.add(wdp);
        }
    }


    @Override
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    protected void notifySubscribers(String property) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.onPropertyChange(property);
        }
    }
}

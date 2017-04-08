package com.forrestdale.viewmodels;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.interfaces.IForecastPredictor;
import com.forrestdale.interfaces.ISubscriber;
import com.forrestdale.views.WeatherForecastView;
import com.forrestdale.views.WeatherSingleView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

/**
 * Created by jesse on 4/8/2017.
 */
public class MainViewModel implements ISubscriber {
    private Stack<JFrame> mNavigationSteps = new Stack<>();
    private Stack<WeatherViewModelBase> mViewModelNavigationSteps = new Stack<>();
    private JFrame mCurrentView;
    private WeatherViewModelBase mCurrentViewModel;
    private IForecastPredictor mPredictor;

    public MainViewModel(IForecastPredictor predictor) {
        mPredictor = predictor;
        mCurrentView = new JFrame();
        showWeatherForecastView();
    }

    private void setCurrentView(JFrame view) {
        mNavigationSteps.push(mCurrentView);
        mCurrentView.setVisible(false);
        mCurrentView = view;
        setupPreviousNavigation(view);
        mCurrentView.setVisible(true);
    }

    private void setCurrentViewModel(WeatherViewModelBase currentViewModel) {
        mViewModelNavigationSteps.push(mCurrentViewModel);
        mCurrentViewModel = currentViewModel;
    }

    private void showWeatherForecastView() {
        setCurrentViewModel(new WeatherForecastViewModel(mPredictor));
        WeatherForecastView view = new WeatherForecastView((WeatherForecastViewModel) mCurrentViewModel);
        view.subscribe(this);
        setCurrentView(view);
    }

    private void showWeatherSingleView(IForecastDay forecastDay) {
        setCurrentViewModel(new WeatherSingleViewModel(mPredictor, forecastDay));
        WeatherSingleView view = new WeatherSingleView((WeatherSingleViewModel) mCurrentViewModel);
        setCurrentView(view);
    }

    private void setupPreviousNavigation(JFrame view) {
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setCurrentViewModel(mViewModelNavigationSteps.pop());
                if (mCurrentViewModel instanceof WeatherSingleViewModel) {
                    mCurrentView.dispose();
                }
                setCurrentView(mNavigationSteps.pop());
            }
        });
    }

    @Override
    public void onPropertyChange(String property) {
        if (property == WeatherForecastView.VIEW_CLICKED && mCurrentViewModel instanceof WeatherForecastViewModel) {
            showWeatherSingleView(((WeatherForecastViewModel) mCurrentViewModel).getSelectedDay());
        }
    }
}

package com.forrestdale;

import com.forrestdale.interfaces.IForecastDay;
import com.forrestdale.predictor.WeatherPredictor;
import com.forrestdale.utils.WeatherLoader;
import com.forrestdale.viewmodels.MainViewModel;
import com.forrestdale.viewmodels.WeatherForecastViewModel;
import com.forrestdale.viewmodels.WeatherSingleViewModel;
import com.forrestdale.views.WeatherForecastView;
import com.forrestdale.views.WeatherSingleView;

import java.util.Date;
import java.util.List;

public class Main {
    private static final String FILEPATH = "./src/com/forrestdale/weatherData.csv";

    public static void main(String[] args) {
        WeatherLoader wl = new WeatherLoader(FILEPATH, true);
        WeatherPredictor predictor = new WeatherPredictor(wl.getWeatherDays());

//        WeatherSingleViewModel viewModel = new WeatherSingleViewModel(predictor, predictor.PredictForecastDay(new Date(0, 2, 26)));
//        WeatherSingleView view = new WeatherSingleView(viewModel);
//        viewModel.setForecastDay(predictor.PredictForecastDay(new Date(0, 2, 26)));
//        view.setVisible(true);

//        WeatherForecastViewModel viewModel = new WeatherForecastViewModel(predictor);
//        WeatherForecastView view = new WeatherForecastView(viewModel);
//        view.setVisible(true);

        MainViewModel viewModel = new MainViewModel(predictor);
    }
}

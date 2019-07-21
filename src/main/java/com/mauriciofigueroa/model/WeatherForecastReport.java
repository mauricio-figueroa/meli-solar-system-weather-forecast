package com.mauriciofigueroa.model;


import lombok.Data;

import java.util.EnumMap;
import java.util.Map;

@Data
public class WeatherForecastReport {

    private int maxRainyDay;

    private Map<WeatherForecast, Integer> forecastsPeriods;


    public WeatherForecastReport() {
        this.forecastsPeriods = new EnumMap<>(WeatherForecast.class);
        this.maxRainyDay = -1;
    }

    public void addForecast(WeatherForecast forecast) {
        Integer count = this.forecastsPeriods.getOrDefault(forecast, 0);

        this.forecastsPeriods.put(forecast, count + 1);
    }

}

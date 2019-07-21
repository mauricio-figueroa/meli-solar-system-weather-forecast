package com.mauriciofigueroa.config;

import com.mauriciofigueroa.service.SolarSystemWeatherForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class StartUpDataInit {

    private final SolarSystemWeatherForecastService solarSystemWeatherForecastService;

    @Value("${forecast.days-to-calculate.value}")
    private Integer daysToCalculateForecast;

    @PostConstruct
    public void init() {
        solarSystemWeatherForecastService.calulateAndPersistForecastForDays(daysToCalculateForecast);
    }

}
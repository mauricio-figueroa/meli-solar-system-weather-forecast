package com.mauriciofigueroa.config;

import com.mauriciofigueroa.service.SolarSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class StartUpDataInit {

    private final SolarSystemService solarSystemService;

    @Value("${forecast.days-to-calculate.value}")
    private Integer daysToCalculateForecast;

    @PostConstruct
    public void init() {
        solarSystemService.calulateAndPersistForecastForDays(daysToCalculateForecast);
    }

}
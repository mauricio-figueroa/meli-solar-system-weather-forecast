package com.mauriciofigueroa.controller;


import com.google.common.collect.ImmutableMap;
import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.model.WeatherForecastReport;
import com.mauriciofigueroa.persistence.ForecastRepository;
import com.mauriciofigueroa.service.SolarSystemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ForecastController {

    private static final Logger logger = LoggerFactory.getLogger(ForecastController.class);

    private final ForecastRepository forecastRepository;
    private final SolarSystemService solarSystemService;


    @GetMapping(value = "/weather-forecast")
    public ResponseEntity weatherForecast(@RequestParam(value = "day") int day) {

        logger.info("ForecastController.weatherForecast Trying to get forecast for day [{}]", day);
        Optional<Forecast> forecast = forecastRepository.findByDay(day);

        if (forecast.isPresent()) {
            return ResponseEntity.ok().body(forecast);
        } else {
            return ResponseEntity.badRequest().body(ImmutableMap.of("Message", "You can only check the weather forecast, from day 0 to 3649"));
        }
    }

    @GetMapping(value = "/forecast-report")
    public ResponseEntity forecastReport() {

        logger.info("ForecastController.forecastReport");
        return ResponseEntity.ok().body(solarSystemService.getReport(10));
    }



}

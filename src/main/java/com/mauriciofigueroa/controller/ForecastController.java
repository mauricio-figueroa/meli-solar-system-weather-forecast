package com.mauriciofigueroa.controller;


import com.google.common.collect.ImmutableMap;
import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.persistence.ForecastRepository;
import com.mauriciofigueroa.service.SolarSystemWeatherForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ForecastController {

    private final ForecastRepository forecastRepository;
    private final SolarSystemWeatherForecastService solarSystemWeatherForecastService;

    @Value("${forecast.report.days}")
    private Integer forecastReportTotalDays;


    @GetMapping(value = "/weather-forecast")
    public ResponseEntity weatherForecast(@RequestParam(value = "day") String day) {
        log.info("Trying to get forecast for day [{}]", day);

        if(!StringUtils.isNumeric(day)){
            return ResponseEntity.badRequest().body(ImmutableMap.of("Message", "The 'day' parameter must be a number"));
        }

        Integer dayParsed = Integer.valueOf(day);

        Optional<Forecast> forecast = forecastRepository.findByDay(dayParsed);

        if (forecast.isPresent()) {
            return ResponseEntity.ok().body(forecast);
        } else {
            return ResponseEntity.badRequest().body(ImmutableMap.of("Message", "You can only check the weather forecast, from day 0 to 3649"));
        }
    }

    @GetMapping(value = "/forecast-report")
    public ResponseEntity forecastReport() {
        log.info("Trying to get forecast report");

        return ResponseEntity.ok().body(solarSystemWeatherForecastService.getReport(forecastReportTotalDays));
    }



}

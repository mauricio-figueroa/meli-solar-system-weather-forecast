package com.mauriciofigueroa.service;

import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.model.SolarSystem;
import com.mauriciofigueroa.model.WeatherForecast;
import com.mauriciofigueroa.model.WeatherForecastReport;
import com.mauriciofigueroa.persistence.ForecastRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;

import static com.mauriciofigueroa.model.SolarSystem.SUN_POSITION;
import static com.mauriciofigueroa.utils.GeometryUtils.doubleEquals;
import static com.mauriciofigueroa.utils.GeometryUtils.triangleArea;
import static com.mauriciofigueroa.utils.GeometryUtils.trianglePerimeter;

@Slf4j
@RequiredArgsConstructor
@Service
public class SolarSystemWeatherForecastService {

    private final SolarSystem solarSystem;
    private final ForecastRepository forecastRepository;


    public Forecast getForecastByDay(int day) {

        if (planetsAligned(day)) {
            WeatherForecast weatherForecast = lineWeatherForecastByDay(day);
            return new Forecast(day, weatherForecast);
        } else {
            //It's a triangle
            Double trianglePerimeter = trianglePerimeter(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));

            WeatherForecast weatherForecast = triangleWeatherForecastByDay(day);
            return new Forecast(day, weatherForecast, trianglePerimeter);
        }
    }

    public WeatherForecastReport getReport(Integer totalDays) {
        WeatherForecastReport weatherForecastReport = new WeatherForecastReport();

        double maxPerimeter = 0;
        Integer maxPerimeterDay = null;
        Forecast lastForecast = null;

        int currentDay;
        for (currentDay = 0; currentDay < totalDays; currentDay++) {
            Forecast currentForecast = getForecastByDay(currentDay);

            if (currentDay == 0) {
                lastForecast = currentForecast;
                weatherForecastReport.addForecast(currentForecast.getWeatherForecast());
            }

            if (!currentForecast.getWeatherForecast().equals(lastForecast.getWeatherForecast())) {
                weatherForecastReport.addForecast(currentForecast.getWeatherForecast());
                lastForecast = currentForecast;
            }

            if (WeatherForecast.RAIN.equals(currentForecast.getWeatherForecast())) {

                if (currentForecast.getPerimeter() > maxPerimeter) {
                    maxPerimeter = currentForecast.getPerimeter();
                    maxPerimeterDay = currentDay;
                }
            }
        }

        weatherForecastReport.setMaxRainyDay(maxPerimeterDay);
        return weatherForecastReport;
    }

    private WeatherForecast lineWeatherForecastByDay(int day) {
        return sunIsAligned(day) ? WeatherForecast.DROUGHT : WeatherForecast.OPTIMAL;
    }

    private boolean sunIsAligned(int day) {
        Double triangleArea = triangleArea(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), SUN_POSITION);
        return doubleEquals(triangleArea, 0);
    }

    private boolean planetsAligned(int day) {
        Double triangleArea = triangleArea(solarSystem.getPlanet1Position(day), solarSystem.getPlanet2Position(day), solarSystem.getPlanet3Position(day));
        return doubleEquals(triangleArea, 0);
    }

    private WeatherForecast triangleWeatherForecastByDay(int day) {
        return sunIsContained(day) ? WeatherForecast.RAIN : WeatherForecast.NONE;
    }

    private boolean sunIsContained(int day) {
        Point2D positionPlanet1 = solarSystem.getPlanet1Position(day);
        Point2D positionPlanet2 = solarSystem.getPlanet2Position(day);
        Point2D positionPlanet3 = solarSystem.getPlanet3Position(day);

        double triangleArea = triangleArea(positionPlanet1, positionPlanet2, positionPlanet3);

        double areaPart1 = triangleArea(positionPlanet1, positionPlanet2, SUN_POSITION);
        double areaPart2 = triangleArea(positionPlanet1, SUN_POSITION, positionPlanet3);
        double areaPart3 = triangleArea(SUN_POSITION, positionPlanet2, positionPlanet3);
        double sumParts = areaPart1 + areaPart2 + areaPart3;

        return doubleEquals(sumParts, triangleArea);
    }

    public void calulateAndPersistForecastForDays(int daysToCalculateForecast) {
        log.info("SolarSystemService.initDatabase");

        for (int currentDay = 0; currentDay < daysToCalculateForecast; currentDay++) {
            Forecast currentForecast = getForecastByDay(currentDay);

            log.info("Trying to save forecast [{}]", currentForecast);
            forecastRepository.save(currentForecast);

            log.info("Forecast [{}] save successful", currentForecast);
        }
    }

}



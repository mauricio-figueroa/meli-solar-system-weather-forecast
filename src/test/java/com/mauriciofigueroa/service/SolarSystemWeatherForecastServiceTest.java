package com.mauriciofigueroa.service;

import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.model.Planet;
import com.mauriciofigueroa.model.SolarSystem;
import com.mauriciofigueroa.model.WeatherForecast;
import com.mauriciofigueroa.model.WeatherForecastReport;
import com.mauriciofigueroa.persistence.ForecastRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static com.mauriciofigueroa.utils.GeometryUtils.doubleEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SolarSystemWeatherForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    private SolarSystemWeatherForecastService target;

    @Before
    public void setUp() {
        Planet ferrengi = new Planet(1, true, 500);
        Planet betasoide = new Planet(3, true, 2000);
        Planet vulkano = new Planet(5, false, 1000);

        SolarSystem solarSystem = new SolarSystem(ferrengi, betasoide, vulkano);

        target = new SolarSystemWeatherForecastService(solarSystem, forecastRepository);
    }

    @Test
    public void getDroughtForecast() {

        Forecast forecast = target.getForecastByDay(0);

        Assert.assertEquals(WeatherForecast.DROUGHT, forecast.getWeatherForecast());
        Assert.assertTrue(doubleEquals(0, forecast.getPerimeter()));
    }

    @Test
    public void getNoneForecast() {

        Forecast forecast = target.getForecastByDay(1);

        Assert.assertEquals(WeatherForecast.NONE, forecast.getWeatherForecast());
    }

    @Test
    public void getRainForecast() {
        Forecast forecast = target.getForecastByDay(284);

        Assert.assertEquals(WeatherForecast.RAIN, forecast.getWeatherForecast());
    }

    @Test
    public void getReport3650Days() {
        WeatherForecastReport forecastReport = target.getReport(3650);

        Assert.assertEquals(Integer.valueOf(2808), forecastReport.getMaxRainyDay());
        Map<WeatherForecast, Integer> forecastsPeriods = forecastReport.getForecastsPeriods();
        Assert.assertEquals(Integer.valueOf(81), forecastsPeriods.get(WeatherForecast.RAIN));
        Assert.assertEquals(Integer.valueOf(41), forecastsPeriods.get(WeatherForecast.DROUGHT));
        Assert.assertEquals(Integer.valueOf(82), forecastsPeriods.get(WeatherForecast.NONE));
    }

    @Test
    public void getReport0Days() {
        WeatherForecastReport forecastReport = target.getReport(0);

        Assert.assertNull(forecastReport.getMaxRainyDay());
        Map<WeatherForecast, Integer> forecastsPeriods = forecastReport.getForecastsPeriods();
        Assert.assertNull(forecastsPeriods.get(WeatherForecast.RAIN));
        Assert.assertNull(forecastsPeriods.get(WeatherForecast.DROUGHT));
        Assert.assertNull(forecastsPeriods.get(WeatherForecast.NONE));
    }


    @Test
    public void calculateAndPersisteForecast() {
        target.calulateAndPersistForecastForDays(3650);

        Mockito.verify(forecastRepository, Mockito.times(3650)).save(any());
    }

}
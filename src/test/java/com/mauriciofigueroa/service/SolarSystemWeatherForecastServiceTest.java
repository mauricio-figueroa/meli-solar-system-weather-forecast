package com.mauriciofigueroa.service;

import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.model.Planet;
import com.mauriciofigueroa.model.SolarSystem;
import com.mauriciofigueroa.model.WeatherForecast;
import com.mauriciofigueroa.persistence.ForecastRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mauriciofigueroa.utils.GeometryUtils.doubleEquals;

@RunWith(MockitoJUnitRunner.class)
public class SolarSystemWeatherForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    private SolarSystemWeatherForecastService target;

    private Planet ferrengi;
    private Planet betasoide;
    private Planet vulkano;

    @Before
    public void setUp() {
        ferrengi = new Planet(1, true, 500);
        betasoide = new Planet(3, true, 2000);
        vulkano = new Planet(5, false, 1000);

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

}
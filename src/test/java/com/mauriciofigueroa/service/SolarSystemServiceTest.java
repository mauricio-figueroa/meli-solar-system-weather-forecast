package com.mauriciofigueroa.service;

import com.mauriciofigueroa.model.Forecast;
import com.mauriciofigueroa.model.Planet;
import com.mauriciofigueroa.model.SolarSystem;
import com.mauriciofigueroa.model.WeatherForecast;
import com.mauriciofigueroa.persistence.ForecastRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Range;

import static com.mauriciofigueroa.utils.GeometryUtils.doubleEquals;

public class SolarSystemServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    @InjectMocks
    private SolarSystemService target;

    private Planet ferrengi;
    private Planet betasoide;
    private Planet vulkano;

    @Before
    public void setUp() {
        ferrengi = new Planet(1, true, 500);
        betasoide = new Planet(3, true, 2000);
        vulkano = new Planet(5, false, 1000);
    }

    @Test
    public void getDroughtForecast() {

        SolarSystem solarSystem = new SolarSystem(ferrengi, betasoide, vulkano);

        target = new SolarSystemService(solarSystem, forecastRepository);
        Forecast forecast = target.getForecastByDay(0);

        Assert.assertEquals(WeatherForecast.DROUGHT, forecast.getWeatherForecast());
        Assert.assertTrue(doubleEquals(0, forecast.getPerimeter()));
    }

    @Test
    public void getNoneForecast() {

        SolarSystem solarSystem = new SolarSystem(ferrengi, betasoide, vulkano);

        target = new SolarSystemService(solarSystem, forecastRepository);
        Forecast forecast = target.getForecastByDay(1);

        Assert.assertEquals(WeatherForecast.NONE, forecast.getWeatherForecast());
    }

    @Test
    public void getRainForecast() {

        SolarSystem solarSystem = new SolarSystem(ferrengi, betasoide, vulkano);

        target = new SolarSystemService(solarSystem, forecastRepository);
        Forecast forecast = target.getForecastByDay(284);

        Assert.assertEquals(WeatherForecast.RAIN, forecast.getWeatherForecast());
    }


    @Test
    public void getRainForeca22st() {

        int i = 0;
        while (true) {
            SolarSystem solarSystem = new SolarSystem(ferrengi, betasoide, vulkano);

            target = new SolarSystemService(solarSystem, forecastRepository);
            Forecast forecast = target.getForecastByDay(i);
            System.out.println(i);

            if(WeatherForecast.OPTIMAL.equals(forecast.getWeatherForecast())){
                break;
            }
            //    Assert.assertEquals(WeatherForecast.RAIN,forecast.getWeatherForecast());
            i++;
        }


    }


}
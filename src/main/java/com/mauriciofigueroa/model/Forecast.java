package com.mauriciofigueroa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "forecast")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column
    private Integer day;


    @Column(name = "wheather_forecast")
    @Enumerated(EnumType.STRING)
    private WeatherForecast weatherForecast;

    @Column
    private Double perimeter;

    public Forecast(Integer day, WeatherForecast weatherForecast, Double perimeter) {
        this.day = day;
        this.weatherForecast = weatherForecast;
        this.perimeter = perimeter;
    }

    public Forecast(int day, WeatherForecast weatherForecast) {
        this.day = day;
        this.weatherForecast = weatherForecast;
        this.perimeter = Double.valueOf(0);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "day=" + day +
                ", weatherForecast=" + weatherForecast +
                ", perimeter=" + perimeter +
                '}';
    }
}

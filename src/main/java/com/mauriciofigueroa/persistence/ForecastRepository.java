package com.mauriciofigueroa.persistence;

import com.mauriciofigueroa.model.Forecast;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

    Optional<Forecast> findByDay(Integer day);

}

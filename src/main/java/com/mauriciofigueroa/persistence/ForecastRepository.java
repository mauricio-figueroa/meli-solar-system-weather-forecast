package com.mauriciofigueroa.persistence;

import com.mauriciofigueroa.model.Forecast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

    public Optional<Forecast> findByDay(Integer day);

}

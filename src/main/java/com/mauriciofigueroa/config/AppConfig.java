package com.mauriciofigueroa.config;

import com.mauriciofigueroa.model.SolarSystem;
import com.mauriciofigueroa.model.Planet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Qualifier(value = "vulkanoBetasoideFerrengiSolarSystem")
    @Bean
    public SolarSystem vulkanoBetasoideFerrengiSolarSystem() {
        Planet ferrengi = new Planet(1, true, 500);
        Planet betasoide = new Planet(3, true, 2000);
        Planet vulkano = new Planet(5, false, 1000);
        return new SolarSystem(ferrengi, betasoide, vulkano);
    }

}

# SOLAR SYSTEM WEATHER FORECAST
## Tecnologías utilizadas:
- Java 8
- Maven
- Spring Boot 2.0
- PostgreSQL
- Lombok



Desarrollé el ejercicio propuesto y lo subi a **Heroku**, en la siguiente url podrán ver desarrollados los puntos 1, 2 y 3:
```sh 
https://meli-test-weather-forecast.herokuapp.com/forecast-report

Retorna un JSON con el siguiente formato:
 {
    "maxRainyDay": 2808,
    "forecastsPeriods": {
        "DROUGHT": 41,
        "RAIN": 81,
        "NONE": 82
    }
}
 ```



 Luego podrán consultar el pronóstico del dia extraido desde la base de datos aqui:

 ```sh
 https://meli-test-weather-forecast.herokuapp.com/weather-forecast?day=720

Retorna un JSON con el siguiente formato:
{
    "day": 720,
    "weatherForecast": "DROUGHT",
    "perimeter": 0
}
 ```
 #### Aclaración 
 **Para correr el proyecto localmente hay que instalar el pluggin de Lombok y levantarlo con el perfil 'dev' (spring_profiles_active=dev como variable de entorno)**

 ### CONSIDERACIONES A LA HORA DE DESARROLLAR EL EJERCICIO
 - La carga de datos a la base se realiza cuando la aplicación levanta.
- En el día 0 del sistema solar, todos los planetas están alineados en el punto mas alto de su órbita (angulo de 90 grados).
- Un año tiene 365 días.
- El sistema solar posee a su sol en el origen (P(0,0)).
- Las órbitas son circulares.
- Para el punto que pide sacar el reporte de los periódos en los ultimos 10 años, se considera periodo a los días consecutivos, con el mismo pronóstico.
- Para calcular el área entre los puntos utilicé la fórmula de Herón.
![alt text](https://wikimedia.org/api/rest_v1/media/math/render/svg/8c6e2cf6555dd752b998056819dfec3c1a068422)
- Cualquier disposición de 3 puntos forma un triángulo, excepto que estén los 3 sobre una misma recta.
- Los triángulos tienen área y perímetro distinto de 0.
- Las rectas tienen área y perímetro igual 0.
- Un punto esta contenido dentro de un triángulo, si el área del mismo es igual a la suma de las áreas de los 3 triángulos que se pueden formar entre el punto del sol y los vértices del triángulo.
- Consideré persistir el perimetro, con el fin de consultar en la base de datos el máximo, para pronosticar la intensidad de lluvia.
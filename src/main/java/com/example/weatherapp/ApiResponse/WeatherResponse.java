package com.example.weatherapp.ApiResponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WeatherResponse {
    private CurrentWeather current;

    @Data
    @Getter
    @Setter
    public static class CurrentWeather {
        private Double temperature;
    }
}

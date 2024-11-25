package com.example.weatherapp.Controller;

import com.example.weatherapp.ApiResponse.WeatherResponse;
import com.example.weatherapp.ExceptionHandler.CityNotFoundException;
import com.example.weatherapp.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @GetMapping("/{city}")
    public ResponseEntity<?> weather(@PathVariable String city){
        try{
            WeatherResponse weatherResponse = weatherService.getWeather(city);
            String response= "Current Temperature in "+city+" is "+ weatherResponse.getCurrent().getTemperature()+" Celcius";
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (CityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>("Unkown Error occured"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

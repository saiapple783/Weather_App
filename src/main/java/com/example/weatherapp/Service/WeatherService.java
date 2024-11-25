package com.example.weatherapp.Service;


import com.example.weatherapp.ApiResponse.WeatherResponse;
import com.example.weatherapp.ExceptionHandler.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    private static final String apikey = "74359ecd83732e66d67461f93ef9d534";
    private static final String API = "https://api.weatherstack.com/current?access_key={API_KEY}&query={CITY}";
    private final RestTemplate restTemplate;


    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String city){
        String finalAPI = UriComponentsBuilder.fromHttpUrl(API)
                .buildAndExpand(apikey,city)
                .toUriString();
        System.out.println("FinalAPI :" +finalAPI);
        try{
            WeatherResponse response = restTemplate.getForObject(finalAPI, WeatherResponse.class);
            if(response == null || response.getCurrent() == null){
                throw new CityNotFoundException("city not found "+city);
            }
            return response;

        }catch (RestClientException e){
            throw new CityNotFoundException("Error finding wether data for city:"+city);
        }

    }
}

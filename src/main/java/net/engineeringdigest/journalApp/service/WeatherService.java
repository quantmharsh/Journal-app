package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;

@Component
public class WeatherService {
    //Value annotation is used to get value from properties file similarly like  .env file and its 
    //not pushed on github
    @Value("${weather.api.key}")
    private  String apiKey ;  
    private static final  String  API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate ;
    public  WeatherResponse getWeather(String city)
    {
        String finalAPI =API.replace("CITY" , city).replace("API_KEY" , apiKey);
           
        //restTemplate is used to send http request to api for us and get response
        //Converting jSON reponse to POJO by usin weatherService Class
        ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalAPI , HttpMethod.GET , null , WeatherResponse.class);
          WeatherResponse  body = response.getBody();
          return body;
    }
}


package com.example;

import com.example.WeatherApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    private final String weatherApiKey = "³¯¾¾API_Key";

    public Map<String, Object> getWeatherData(Double latitude, Double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String url = "https://api.weather.example.com/data?lat={latitude}&lon={longitude}&appid={apiKey}";

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("latitude", String.valueOf(latitude));
        urlVariables.put("longitude", String.valueOf(longitude));
        urlVariables.put("apiKey", weatherApiKey);

        String jsonResponse = restTemplate.getForObject(url, String.class, urlVariables);

        WeatherApiResponse weatherApiResponse;
        try {
            weatherApiResponse = objectMapper.readValue(jsonResponse, WeatherApiResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing weather API response.", e);
        }

        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("temperature", weatherApiResponse.getTemperature());
        weatherData.put("weatherDescription", weatherApiResponse.getWeatherDescription());
        // Add any additional data as needed

        return weatherData;
    }
}

package com.example;

public class WeatherApiResponse {

    private Double temperature;
    private String weatherDescription;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    // Add any additional properties and getter/setter methods as needed
}

package com.example;

import com.example.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MapController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String weather(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, Model model) {
        Map<String, Object> weatherData = weatherService.getWeatherData(latitude, longitude);
        model.addAttribute("weatherData", weatherData);
        return "weather";
    }

    @RequestMapping(value = "/weather-data", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getWeatherData(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
        Map<String, Object> weatherData = weatherService.getWeatherData(latitude, longitude);
        return new ResponseEntity<Map<String, Object>>(weatherData, HttpStatus.OK);
    }
}

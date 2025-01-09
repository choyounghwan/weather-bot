package com.myproject.weatherbot.service;

import com.myproject.weatherbot.entity.Weather;
import com.myproject.weatherbot.model.WeatherData;
import com.myproject.weatherbot.model.WeatherResponse;
import com.myproject.weatherbot.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Value("${weather.api.key}")
    private String apiKey; // OpenWeather API 키

    private final String url = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid=" + apiKey + "&units=metric";

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public WeatherData getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class, city, apiKey);
            if (response != null) {
                WeatherData weatherData = new WeatherData();
                weatherData.setMain(response.getWeather().get(0).getMain());
                weatherData.setDescription(response.getWeather().get(0).getDescription());
                weatherData.setTemp(response.getMain().getTemp());

                // 추천 메시지 추가
                weatherData.setRecommendation(getOutfitRecommendation(weatherData.getTemp()));
                return weatherData;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 온도를 기준으로 옷차림 추천 메시지 제공
    private String getOutfitRecommendation(double temperature) {
        if (temperature > 25) {
            return "It's hot! Wear light clothing.";
        } else if (temperature > 15) {
            return "The weather is moderate. Wear a jacket.";
        } else {
            return "It's cold! Wear warm clothing.";
        }
    }
}

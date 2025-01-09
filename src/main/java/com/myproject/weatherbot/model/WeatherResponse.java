package com.myproject.weatherbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myproject.weatherbot.entity.Weather;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Main main;

    private List<Weather> weather;

    @Getter
    @Setter
    public static  class Main {
        @JsonProperty("temp")
        private double temp;
    }

    @Getter
    @Setter
    public static class Weather {
        private String main;
        private String description;
    }
}

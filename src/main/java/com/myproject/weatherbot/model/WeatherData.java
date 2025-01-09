package com.myproject.weatherbot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
    private String main;
    private String description;
    private double temp;
    private String recommendation;
}

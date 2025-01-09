package com.myproject.weatherbot.repository;


import com.myproject.weatherbot.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    // 커스텀 쿼리 메서드 추가 가능
}
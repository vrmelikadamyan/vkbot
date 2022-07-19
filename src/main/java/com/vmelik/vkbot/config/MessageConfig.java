package com.vmelik.vkbot.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MessageConfig {

    public static Map<String, String> weatherMap() {
        Map<String, String> weatherMap = new HashMap<>();
        weatherMap.put("clear", "ясно");
        weatherMap.put("partly-cloudy", "малооблачно");
        weatherMap.put("cloudy", "облачно");
        weatherMap.put("overcast", "пасмурно");
        weatherMap.put("drizzle", "морось");
        weatherMap.put("light-rain", "небольшой дождь");
        weatherMap.put("rain", "дождь");
        weatherMap.put("moderate-rain", "умеренно сильный дождь");
        weatherMap.put("heavy-rain", "сильный дождь");
        weatherMap.put("continuous-heavy-rain", "длительный сильный дождь");
        weatherMap.put("showers", "ливень");
        weatherMap.put("wet-snow", "дождь со снегом");
        weatherMap.put("light-snow", "небольшой снег");
        weatherMap.put("snow", "снег");
        weatherMap.put("snow-showers", "снегопад");
        weatherMap.put("hail", "град");
        weatherMap.put("thunderstorm", "гроза");
        weatherMap.put("thunderstorm-with-hail", "гроза с градом");

        return weatherMap;
    }

}

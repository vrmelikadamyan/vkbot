package com.vmelik.vkbot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("endpoint")
public class EndpointConfig {
    private OpenWeatherMap openWeatherMap;
    private Yandex yandex;

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OpenWeatherMap {
        private String searchObject;
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Yandex {
        private String getWeather;
    }
}

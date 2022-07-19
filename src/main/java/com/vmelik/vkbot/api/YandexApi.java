package com.vmelik.vkbot.api;

import com.vmelik.vkbot.config.EndpointConfig;
import com.vmelik.vkbot.model.weather.WeatherDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class YandexApi {
    private final EndpointConfig endpointConfig;

    @Qualifier(value = "default")
    private final RestTemplate restTemplate;

    @Value("${yandex.weather-api.key}")
    private String apiKey;

    public WeatherDto getWeather(String lat, String lon) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("X-Yandex-API-Key", List.of(apiKey));

        return restTemplate.exchange(
                endpointConfig.getYandex().getGetWeather(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                WeatherDto.class,
                Map.of("lat", lat, "lon", lon)
        ).getBody();
    }
}

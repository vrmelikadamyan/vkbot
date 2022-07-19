package com.vmelik.vkbot.api;

import com.vmelik.vkbot.config.EndpointConfig;
import com.vmelik.vkbot.model.weather.CoordinatesDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenWeatherMapApi {
    private final EndpointConfig endpointConfig;

    @Qualifier(value = "default")
    private final RestTemplate restTemplate;

    public CoordinatesDto searchObject(String query) {
        List<CoordinatesDto> result = restTemplate.exchange(
                endpointConfig.getOpenWeatherMap().getSearchObject(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CoordinatesDto>>() {
                },
                Map.of("query", query)
        ).getBody();

        return result != null && !result.isEmpty() ? result.get(0) : new CoordinatesDto();
    }
}

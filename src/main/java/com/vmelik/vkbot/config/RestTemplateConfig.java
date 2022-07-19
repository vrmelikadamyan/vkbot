package com.vmelik.vkbot.config;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "default")
    public RestTemplate restTemplate(
            RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.of(120, ChronoUnit.SECONDS))
                .setConnectTimeout(Duration.of(120, ChronoUnit.SECONDS))
                .build();
    }
}

package com.vmelik.vkbot.handler.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vmelik.vkbot.api.OpenWeatherMapApi;
import com.vmelik.vkbot.api.YandexApi;
import com.vmelik.vkbot.config.MessageConfig;
import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.model.NewMessageDto;
import com.vmelik.vkbot.model.VkApi;
import com.vmelik.vkbot.model.weather.CoordinatesDto;
import com.vmelik.vkbot.model.weather.WeatherDto;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class WeatherCommand implements CommandHandler {
    private final YandexApi yandexApi;
    private final OpenWeatherMapApi openWeatherMapApi;
    private final ObjectMapper objectMapper;
    private final VkApi vkApi;

    private static final String TEXT_AFTER_COMMAND_REGEX = "^\\/[a-zA-Z]+ (.*)";
    private static final String WEATHER_TEXT =
            """
                    Погода в %s, %s, %s:
                    %d °C, %s
                    """;

    @Override
    public void handleCommand(EventDto event) throws ClientException, ApiException {
        NewMessageDto eventObject = objectMapper.convertValue(event.getObject(), NewMessageDto.class);

        String query = extractTextAfterCommand(eventObject.getMessage().getText());

        CoordinatesDto object = openWeatherMapApi.searchObject(query);

        if (object.isEmpty()) {
            vkApi.sendMessage(eventObject.getMessage().getPeerId(), "это где?");
        }

        WeatherDto weather = yandexApi.getWeather(object.getLat(), object.getLon());

        String conditionOnRussian = MessageConfig.weatherMap().getOrDefault(weather.getFactWeather().getCondition(), "норм");
        String message = String.format(WEATHER_TEXT,
                weather.getGeoObject().getLocality().getName(),
                weather.getGeoObject().getProvince().getName(),
                weather.getGeoObject().getCountry().getName(),
                weather.getFactWeather().getTemp(),
                conditionOnRussian);

        vkApi.sendMessage(eventObject.getMessage().getPeerId(), message);
    }

    @Override
    public String getCommand() {
        return "/weather";
    }

    @Override
    public String getDescription() {
        return "/weather - погода";
    }

    private String extractTextAfterCommand(String message) {
        Matcher m = Pattern.compile(TEXT_AFTER_COMMAND_REGEX).matcher(message);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }
}

package com.vmelik.vkbot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordinatesDto {

    private String lat;

    private String lon;

    public boolean isEmpty() {
        return lat == null || lon == null;
    }
}

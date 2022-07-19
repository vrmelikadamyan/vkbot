package com.vmelik.vkbot.model.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactWeatherDto {

    private Integer temp;

    private String condition;
}

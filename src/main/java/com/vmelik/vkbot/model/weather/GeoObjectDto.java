package com.vmelik.vkbot.model.weather;

import com.vmelik.vkbot.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoObjectDto {

    private BaseDto locality;

    private BaseDto province;

    private BaseDto country;
}

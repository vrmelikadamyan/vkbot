package com.vmelik.vkbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    /**
     * тип события
     */
    private String type;

    /**
     * идентификатор события
     */
    @JsonProperty(value = "event_id")
    private String eventId;

    /**
     * ID сообщества, в котором произошло событие
     */
    @JsonProperty(value = "group_id")
    private Integer groupId;

    /**
     * объект, инициировавший событие
     */
    private Object object;

    /**
     * версия API, для которой сформировано событие
     */
    @JsonProperty(value = "v")
    private String apiVersion;
}

package com.vmelik.vkbot.handler.event;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vmelik.vkbot.model.EventDto;

public interface EventHandler {

    void handleEvent(EventDto body) throws ClientException, ApiException;

    String getEventType();
}

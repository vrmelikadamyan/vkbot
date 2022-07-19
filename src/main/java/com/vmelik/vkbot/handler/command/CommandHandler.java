package com.vmelik.vkbot.handler.command;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vmelik.vkbot.model.EventDto;

public interface CommandHandler {

    void handleCommand(EventDto event) throws ClientException, ApiException;

    String getCommand();

    String getDescription();
}

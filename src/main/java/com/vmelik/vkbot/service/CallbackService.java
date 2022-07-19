package com.vmelik.vkbot.service;

import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.model.NewMessageDto;

public interface CallbackService {

    void handleCallback(EventDto newMessageEvent);
}

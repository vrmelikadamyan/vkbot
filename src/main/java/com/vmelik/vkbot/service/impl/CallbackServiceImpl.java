package com.vmelik.vkbot.service.impl;

import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.service.CallbackService;
import com.vmelik.vkbot.handler.event.EventHandler;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CallbackServiceImpl implements CallbackService {
    private Map<String, EventHandler> handlers;

    @Autowired
    public void setHandlers(Set<EventHandler> handlerSet) {
        handlers = handlerSet.stream().collect(Collectors.toMap(EventHandler::getEventType, Function.identity()));
    }

    @Override
    public void handleCallback(EventDto event) {
        if (handlers.containsKey(event.getType())) {
            try {
                handlers.get(event.getType()).handleEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

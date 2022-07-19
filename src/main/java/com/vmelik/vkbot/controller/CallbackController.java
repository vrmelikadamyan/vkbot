package com.vmelik.vkbot.controller;

import com.vmelik.vkbot.config.BotConfig;
import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.service.CallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CallbackController {
    private static final String CONFIRMATION = "confirmation";
    private final CallbackService callbackService;
    private final BotConfig botConfig;

    @PostMapping("/callback")
    public Object newMessageCallback(@RequestBody EventDto body) {
        if (body.getType().equals(CONFIRMATION)) {
            return botConfig.getConfirmationCode();
        }

        callbackService.handleCallback(body);

        return "ok";
    }
}

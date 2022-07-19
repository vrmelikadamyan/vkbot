package com.vmelik.vkbot.handler.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.model.NewMessageDto;
import com.vmelik.vkbot.model.VkApi;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelpCommand implements CommandHandler {
    private final VkApi vkApi;
    private final ObjectMapper objectMapper;
    private String commandsDescription;

    @Autowired
    public void setCommandsDescription(Set<CommandHandler> commandHandlers) {
        StringBuilder description = new StringBuilder();
        description.append("Доступные команды:\n");
        description.append("/help - помощь\n");
        commandHandlers.forEach(
                commandHandler -> description.append(commandHandler.getDescription()).append("\n")
        );

        commandsDescription = description.toString();
    }

    @Override
    public void handleCommand(EventDto event) throws ClientException, ApiException {
        NewMessageDto eventObject = objectMapper.convertValue(event.getObject(), NewMessageDto.class);

        vkApi.sendMessage(eventObject.getMessage().getPeerId(), getDescription());
    }

    @Override
    public String getCommand() {
        return "/help";
    }

    @Override
    public String getDescription() {
        return commandsDescription;
    }
}

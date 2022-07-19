package com.vmelik.vkbot.handler.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vmelik.vkbot.handler.command.CommandHandler;
import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.model.NewMessageDto;
import com.vmelik.vkbot.model.VkApi;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewMessageEventHandler implements EventHandler {
    private final VkApi vkApi;
    private final ObjectMapper objectMapper;
    private Map<String, CommandHandler> commandHandlerMap;
    private static final String COMMAND_REGEX = "^\\/[a-zA-Z]+";

    @Autowired
    public void setHandlers(Set<CommandHandler> commandHandlers) {
        commandHandlerMap = commandHandlers.stream().collect(Collectors.toMap(CommandHandler::getCommand, Function.identity()));
    }

    @Override
    public void handleEvent(EventDto event) throws ClientException, ApiException {
        NewMessageDto eventObject = objectMapper.convertValue(event.getObject(), NewMessageDto.class);

        String message = eventObject.getMessage().getText();
        if (isCommand(message)) {
            Optional.ofNullable(commandHandlerMap.get(extractCommand(message)))
                    .ifPresentOrElse(
                            commandHandler -> {
                                try {
                                    commandHandler.handleCommand(event);
                                } catch (ClientException | ApiException e) {
                                    e.printStackTrace();
                                }
                            },
                            () -> {
                                try {
                                    vkApi.sendMessage(eventObject.getMessage().getPeerId(), "Неизвестная команда! Используйте команду /help для получения списка доступных команд");
                                } catch (ClientException | ApiException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        }
    }

    @Override
    public String getEventType() {
        return "message_new";
    }

    private boolean isCommand(String message) {
        return message.startsWith("/");
    }

    private String extractCommand(String message) {
        Matcher m = Pattern.compile(COMMAND_REGEX).matcher(message);

        if (m.find()) {
            return m.group();
        }

        return "";
    }
}

package com.vmelik.vkbot.handler.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.ConversationMember;
import com.vk.api.sdk.objects.messages.responses.GetConversationMembersResponse;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import com.vmelik.vkbot.model.EventDto;
import com.vmelik.vkbot.model.NewMessageDto;
import com.vmelik.vkbot.model.VkApi;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WhoisCommand implements CommandHandler {
    private final VkApi vkApi;
    private final ObjectMapper objectMapper;
    private static final String TEXT_AFTER_COMMAND_REGEX = "^\\/[a-zA-Z]+ (.*)";

    @Override
    public void handleCommand(EventDto event) throws ClientException, ApiException {
        NewMessageDto eventObject = objectMapper.convertValue(event.getObject(), NewMessageDto.class);

        GetConversationMembersResponse response = vkApi.getVk().messages().getConversationMembers(vkApi.getGroup(), eventObject.getMessage().getPeerId()).execute();
        List<ConversationMember> items = response.getItems();
        String textAfterCommand = extractTextAfterCommand(eventObject.getMessage().getText());

        ConversationMember randomMember = items.get(new Random().nextInt(items.size() - 1));

        List<GetResponse> user = vkApi.getVk().users().get(vkApi.getGroup()).userIds(randomMember.getMemberId().toString()).execute();

//        vkApi.sendMessage(eventObject.getMessage().getPeerId(), textAfterCommand + " здесь очевидно " + user.get(0).getFirstName());
        vkApi.sendMessage(eventObject.getMessage().getPeerId(), user.get(0).getFirstName() + " " + user.get(0).getLastName() + ", очевидно, " + textAfterCommand);
    }

    @Override
    public String getCommand() {
        return "/who";
    }

    @Override
    public String getDescription() {
        return "/who <текст> - определить рандомно, кто из участников <текст>";
    }

    private String extractTextAfterCommand(String message) {
        Matcher m = Pattern.compile(TEXT_AFTER_COMMAND_REGEX).matcher(message);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }
}

package com.vmelik.vkbot.model;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VkApi {
    private VkApiClient vk;
    private GroupActor group;

    public void sendMessage(int peerId, String message) throws ClientException, ApiException {
                vk.messages()
                .send(group)
                .peerId(peerId)
                .groupId(group.getGroupId())
                .message(message)
                .randomId(new Random().nextInt())
                .execute();
    }
}

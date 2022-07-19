package com.vmelik.vkbot.config;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vmelik.vkbot.model.VkApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BotConfig {

    @Value("${vk.groupId}")
    private Integer groupId;

    @Value("${vk.token}")
    private String token;

    @Getter
    @Value("${vk.confirmationCode}")
    private String confirmationCode;

    @Bean
    public VkApi vkApi() {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        GroupActor groupActor = new GroupActor(groupId, token);

        return new VkApi(vk, groupActor);
    }
}

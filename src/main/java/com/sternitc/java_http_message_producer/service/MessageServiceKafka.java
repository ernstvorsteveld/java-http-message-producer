package com.sternitc.java_http_message_producer.service;

import com.sternitc.java_http_message_producer.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MessageServiceKafka implements MessageService {

    private final StreamBridge streamBridge;

    @Autowired
    public MessageServiceKafka(
            StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void send(String key, SimpleMessage message) {
        Message<SimpleMessage> messageToSend = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.KEY, key.getBytes(StandardCharsets.UTF_8))
                .build();
        streamBridge.send("addressHandler-out-0", messageToSend);
    }
}

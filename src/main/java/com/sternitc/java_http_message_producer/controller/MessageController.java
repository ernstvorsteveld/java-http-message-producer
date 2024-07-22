package com.sternitc.java_http_message_producer.controller;

import com.sternitc.java_http_message_producer.model.SimpleMessage;
import com.sternitc.java_http_message_producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("message")
    public Mono<String> accept(@RequestBody Mono<SimpleMessage> message) {
        return message.map(m -> {
                    messageService.send(m.key(), m);
                    return String.format("Success - Message with key \"%s\" to topic", m.key());
                }
        );
    }
}


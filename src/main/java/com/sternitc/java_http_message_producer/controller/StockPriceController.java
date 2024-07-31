package com.sternitc.java_http_message_producer.controller;

import com.sternitc.java_http_message_producer.model.StockPrice;
import com.sternitc.java_http_message_producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StockPriceController {

    @Autowired
    private MessageService messageService;

    @PostMapping("stock-price")
    public Mono<String> accept(@RequestBody Mono<StockPrice> message) {
        return message.map(m -> {
                    messageService.send(m.stock(), m.price());
                    return String.format("Success - StockPrice with key \"%s\" to topic", m.stock());
                }
        );
    }
}


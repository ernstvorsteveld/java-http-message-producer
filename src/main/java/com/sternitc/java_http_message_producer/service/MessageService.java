package com.sternitc.java_http_message_producer.service;

import com.sternitc.java_http_message_producer.model.SimpleMessage;

public interface MessageService {

    void send(String key, SimpleMessage message);

    void send(String key, int value);
}

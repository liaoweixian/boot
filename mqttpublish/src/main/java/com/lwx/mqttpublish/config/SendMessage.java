package com.lwx.mqttpublish.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "outChannel")
public interface SendMessage {

    public void send(String message);

    public void send(String message, @Header("mqtt_topic")String topc);

    public void send(Byte[] message, @Header("mqtt_topic")String topc);

}

package com.lwx.mqttpublish.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mqttConfig {

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.address}")
    private String address;
    @Value("${mqtt.qos}")
    private int qos;
    @Value("${mqtt.keepAlive}")
    private int keepAlive;

    public MqttConnectOptions getMqttConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        // options.getConnectionTimeout()
        options.setKeepAliveInterval(keepAlive);
        return options;
    }
}

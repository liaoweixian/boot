package com.lwx.mqttsubscribe.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.List;
import java.util.concurrent.*;

@Configuration
public class mqttConfig {

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.port}")
    private String port;
    @Value("${mqtt.qos}")
    private int qos;
    @Value("${mqtt.keepAlive}")
    private int keepAlive;

    @Bean
    public MqttConnectOptions getMqttConnectionOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setServerURIs(new String[] {port});
        options.setKeepAliveInterval(keepAlive);
        /**
         * 设置服务器和客户端在重新启动或重新连接时记住状态
         * false 客户端、服务器和连接重新启动时保持状态, 根据设置qos的类型，进行数据的处理，
         *  例如：设置至少接收一次，客户端网络问题断开，再次连上，服务端会把断开后没有接收到的数据再次发送给客户端
         * true 不会在客户端、服务端连接重新启动时保持状态, 断开重连，无法保持之前的QOS（消息级别）的消息传递
         *
         * 也就是说： 设置false 客户端断开时，服务端会把你没有接收到的数据进行缓存下来，待你连上，再把数据发送给你
         *           设置true 客户端断开时，服务端的消息不是被别的客户端消费了，就是数据丢失了， 待你再次连上，无法获取到之前的消息
         */
        options.setCleanSession(true);
        return options;
    }

    /**
     * 第一步：配置MQTT客户端工厂类DefaultMqttPahoClientFactory
     * 第二步：配置MQTT入站消息适配器MqttPahoMessageDrivenChannelAdapter
     * 第三步：定义MQTT入站消息通道MessageChannel
     * 第四步：声明MQTT入站消息处理器MessageHandler
     * @return
     */


    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory mqttFactory = new DefaultMqttPahoClientFactory();
        mqttFactory.setConnectionOptions(getMqttConnectionOptions());
        return mqttFactory;
    }

    @Bean
    public MessageChannel outChannel() {
        return new DirectChannel();
    }

    @Bean
    // @ServiceActivator(inputChannel = "outChannel")
    public MqttPahoMessageHandler outbound() {
        // // 初始化出站通道适配器，使用的是Eclipse Paho MQTT客户端库
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("subscribe"+System.currentTimeMillis(),
                        mqttClientFactory());
        // // 设置异步发送，默认是false(发送时阻塞)
        messageHandler.setAsync(true);
        // 设置消息质量
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }

    /**
     * 使用dsl  Integration 进行整合 , 把通道和适配器进行绑定， 不需要使用你ServiceActivator
     * @return
     */
    @Bean
    public IntegrationFlow outFlow() {
        return IntegrationFlows.from(outChannel())
                .channel(MessageChannels.executor(new ThreadPoolExecutor(20,200,
                        60000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()
                        )))
                .handle(outbound())
                .get();
    }

}

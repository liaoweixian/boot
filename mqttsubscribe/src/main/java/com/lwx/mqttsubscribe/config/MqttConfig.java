package com.lwx.mqttsubscribe.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

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

    /**
     * 配置client,监听的topic  订阅主题
     * 配置Inbound入站，消费者基本连接配置
     * 1. 通过DefaultMqttPahoClientFactory 初始化入站通道适配器
     * 2. 配置超时时长，默认30000毫秒
     * 3. 配置Paho消息转换器
     * 4. 配置发送数据的服务质量 0~2
     * 5. 配置订阅通道
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                "noticeSub_"+System.currentTimeMillis(),
                mqttClientFactory(),
                "/com/lwx/boot/qq/#"
                );
        // 设置连接超长时间
        adapter.setCompletionTimeout(3000);
        // 设置转换器 默认的
        DefaultPahoMessageConverter messageConverter = new DefaultPahoMessageConverter();
        adapter.setConverter(messageConverter);
        // 设置数据处理通道
        adapter.setOutputChannel(mqttInChannel());
        /**
         * 设置服务质量
         * 0 最多一次，数据可能丢失
         * 1 至少一次，数据可能重复
         * 2 只有一次，有且只有一次，最耗性能
         */
        adapter.setQos(1);
        return adapter;
    }

    /**
     * 配置入站的消息通道
     */
    @Bean
    public MessageChannel mqttInChannel() {
        return new DirectChannel();
    }

    /**
     * 配置Inbound入站，消费者的消息处理器
     * 1. 使用@ServiceActivator注解，表明所修饰的方法用于消息处理
     * 2. 使用inputChannel值，表明从指定通道中取值
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInChannel")
    public MessageHandler mqttInDataHandler() {
        return message -> {
            String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
            String jsonString = message.getPayload().toString();
            System.out.println(topic);
            System.out.println(jsonString);
        };
    }




}

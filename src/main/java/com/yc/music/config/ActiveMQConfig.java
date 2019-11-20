
package com.yc.music.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


@Configuration
public class ActiveMQConfig {

    public static final String TOPIC_USER = "user.topic";

    public static final String QUEUE_USER = "useR.queue";

    public static final String QUEUE_LISTENER_CONTAINER_FACTORY = "queueListenerContainerFactory";

    public static final String TOPIC_LISTENER_CONTAINER_FACTORY = "topicListenerContainerFactory";

    //消息队列的1对多模式
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(TOPIC_USER);
    }

    //消息队列的点对点模式
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(QUEUE_USER);
    }

    @Value("${spring.activemq.broker-url}")
    private String host;

    @Bean
    public ConnectionFactory getActiveMqConnection() {
        return new ActiveMQConnectionFactory(host);
    }

    @Bean(name = QUEUE_LISTENER_CONTAINER_FACTORY)
    public JmsListenerContainerFactory queueListenerContailerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean(name = TOPIC_LISTENER_CONTAINER_FACTORY)
    public JmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}


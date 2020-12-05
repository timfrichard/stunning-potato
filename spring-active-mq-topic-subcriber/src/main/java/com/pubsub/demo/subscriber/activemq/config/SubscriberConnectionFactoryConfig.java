package com.pubsub.demo.subscriber.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
public class SubscriberConnectionFactoryConfig {

    public static final String DEMO_CLIENT_ID = "demoClientId";

    @Bean
    public ActiveMQProperties activeMQProperties() {
        return new ActiveMQProperties();
    }


    /*
     * Initial ConnectionFactory
     */
    @Bean(name = "demoConnectionFactory")
    public ConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        final ActiveMQProperties activeMQProperties = activeMQProperties();
        connectionFactory.setBrokerURL(activeMQProperties.getBrokerUrl());
        connectionFactory.setUserName(activeMQProperties.getUser());
        connectionFactory.setPassword(activeMQProperties.getPassword());
        // We need this to make it durable
        connectionFactory.setClientID(DEMO_CLIENT_ID);
        connectionFactory.setNonBlockingRedelivery(activeMQProperties.isNonBlockingRedelivery());

        return connectionFactory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> demoListenerContainerFactory(@Qualifier("demoConnectionFactory") final ConnectionFactory connectionFactory,
                                                                       final DefaultJmsListenerContainerFactoryConfigurer configurer) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(Boolean.TRUE);
        // We need this to make it durable
        factory.setClientId(DEMO_CLIENT_ID);
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}

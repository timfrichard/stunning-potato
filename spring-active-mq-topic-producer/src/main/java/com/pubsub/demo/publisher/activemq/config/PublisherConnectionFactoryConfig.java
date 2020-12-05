package com.pubsub.demo.publisher.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class PublisherConnectionFactoryConfig {

    private final String brokerUrl;

    public PublisherConnectionFactoryConfig(@Value("${jsa.activemq.broker.url}") final String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    /*
     * Initial ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setClientIDPrefix("Prefix");
        final RedeliveryPolicy reDeliverPolicy = new RedeliveryPolicy();
        reDeliverPolicy.setMaximumRedeliveries(100);
        activeMQConnectionFactory.setRedeliveryPolicy(reDeliverPolicy);
        activeMQConnectionFactory.setConsumerFailoverRedeliveryWaitPeriod(200L);
        activeMQConnectionFactory.setStatsEnabled(true);
        return new CachingConnectionFactory(activeMQConnectionFactory);
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /*
     * Used for sending Messages.
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        final JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jacksonJmsMessageConverter());
        template.setPubSubDomain(true);
        return template;
    }
}

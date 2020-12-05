package com.pubsub.demo.publisher.activemq.jms;

import com.pubsub.demo.publisher.activemq.models.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsPublisher {

    private final JmsTemplate jmsTemplate;

    private final String topic;

    public JmsPublisher(final JmsTemplate jmsTemplate, @Value("${jsa.activemq.topic}") final String topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic = topic;
    }

    public void send(final Company apple) {
        jmsTemplate.convertAndSend(topic, apple);
    }
}

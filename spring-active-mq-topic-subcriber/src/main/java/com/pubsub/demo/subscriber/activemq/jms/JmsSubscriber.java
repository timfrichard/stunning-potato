package com.pubsub.demo.subscriber.activemq.jms;

import com.pubsub.demo.subscriber.activemq.models.Company;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class JmsSubscriber {

    /* The containerFactory argument set as the name of the JmsListenerContainerFactory bean will
    make the subscription durable */
    @JmsListener(destination = "${demo.spring.activemq.topic}", containerFactory = "demoListenerContainerFactory")
    public void receive(final Company msg) {
        System.out.println("Recieved Message: " + msg);
    }
}

package com.pubsub.demo.publisher.activemq.controller;

import com.pubsub.demo.publisher.activemq.jms.JmsPublisher;
import com.pubsub.demo.publisher.activemq.models.Company;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company-request")
public class CompanyController {

    private final JmsPublisher publisher;

    public CompanyController(final JmsPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/save-company")
    public String savePurchaseOrder(@RequestBody final Company company) {

        publisher.send(company);
        return "Successful";
    }

}

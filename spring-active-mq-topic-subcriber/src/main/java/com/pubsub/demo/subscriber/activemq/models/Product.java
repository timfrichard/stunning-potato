package com.pubsub.demo.subscriber.activemq.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Product.class)
public class Product {
    private String name;

    private Company company;

    public Product() {
    }

    public Product(final String name) {
        this.name = name;
    }

    public Product(final String name, final Company company) {
        this.name = name;
        this.company = company;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // products
    public void setCompany(final Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }
}
package com.pubsub.demo.publisher.activemq.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
  This looks like a good Account.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Company.class)
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Company {

    private List<Department> departments;
    private String name;
    private List<Product> products;

}
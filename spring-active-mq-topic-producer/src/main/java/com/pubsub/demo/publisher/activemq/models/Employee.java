package com.pubsub.demo.publisher.activemq.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    private String emailAddress;
    private Long employeeNumber;
    private String firstName;
    private String lastName;
}

package com.kolotree.domain;

import com.kolotree.common.AggregateRoot;

public class Customer extends AggregateRoot {

    private final String firstName;
    private final String lastName;
    private final String email;

    Customer(String id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

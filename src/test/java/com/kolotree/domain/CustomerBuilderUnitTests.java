package com.kolotree.domain;

import com.kolotree.common.Result;
import org.junit.Test;

import static com.kolotree.domain.CustomerBuilder.aCustomerBuilder;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CustomerBuilderUnitTests {

    private final CustomerBuilder customerBuilder;

    public CustomerBuilderUnitTests() {
        this.customerBuilder = aCustomerBuilder()
                .id("1234567")
                .firstName("Milenko")
                .lastName("Jovanovic")
                .emailAddress("milenko.jovanovic@kolotree.com");
    }

    @Test
    public void createValidCustomerFromValidData() {
        Result<Customer> customerResult = customerBuilder.Build();
        assertTrue(customerResult.isSuccess());
    }

    @Test
    public void errorInCaseOfNullId() {
        CustomerBuilder builderWithoutId = customerBuilder.id(null);
        Result<Customer> customerResult = builderWithoutId.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyId() {
        CustomerBuilder builderWithEmptyId = customerBuilder.id("");
        Result<Customer> customerResult = builderWithEmptyId.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfNullFirstName() {
        CustomerBuilder builderWithoutFirstName = customerBuilder.firstName(null);
        Result<Customer> customerResult = builderWithoutFirstName.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyFirstName() {
        CustomerBuilder builderWithEmptyFirstName = customerBuilder.firstName("");
        Result<Customer> customerResult = builderWithEmptyFirstName.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfNullLastName() {
        CustomerBuilder builderWithoutLastName = customerBuilder.lastName(null);
        Result<Customer> customerResult = builderWithoutLastName.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyLastName() {
        CustomerBuilder builderWithEmptyLastName = customerBuilder.lastName("");
        Result<Customer> customerResult = builderWithEmptyLastName.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfNullEmailAddress() {
        CustomerBuilder builderWithoutEmailAddress = customerBuilder.lastName(null);
        Result<Customer> customerResult = builderWithoutEmailAddress.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyEmailAddress() {
        CustomerBuilder builderWithEmptyEmailAddress = customerBuilder.lastName("");
        Result<Customer> customerResult = builderWithEmptyEmailAddress.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfInvalidEmailAddress() {
        CustomerBuilder builderWithInvalidEmail = customerBuilder.emailAddress("milenko#kolotree.com");
        Result<Customer> customerResult = builderWithInvalidEmail.Build();

        assertTrue(customerResult.isFailure());
        assertEquals(customerResult.getError().getClass(), InvalidEmailAddressError.class);

    }
}

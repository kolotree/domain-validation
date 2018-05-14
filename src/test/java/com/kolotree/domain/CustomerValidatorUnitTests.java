package com.kolotree.domain;

import com.kolotree.common.AggregateError;
import io.vavr.control.Validation;
import org.junit.Test;

import static com.kolotree.domain.CustomerValidator.aCustomerValidator;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CustomerValidatorUnitTests {
    private final CustomerValidator customerValidator;

    public CustomerValidatorUnitTests() {
        this.customerValidator = aCustomerValidator()
                .id("1234567")
                .firstName("Milenko")
                .lastName("Jovanovic")
                .emailAddress("milenko.jovanovic@kolotree.com");
    }

    @Test
    public void createValidCustomerFromValidData() {
        Validation<AggregateError, Customer> customerValidation = customerValidator.validate();
        assertTrue(customerValidation.isValid());
    }

    @Test
    public void errorInCaseOfNullId() {
        CustomerValidator validatorWithoutId = customerValidator.id(null);
        Validation<AggregateError, Customer> customerValidation = validatorWithoutId.validate();

        assertTrue(customerValidation.isInvalid());
        assertEquals(customerValidation.getError().getNestedErrors().get(0).getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyId() {
        CustomerValidator validatorWithEmptyId = customerValidator.id(null);
        Validation<AggregateError, Customer> customerValidation = validatorWithEmptyId.validate();

        assertTrue(customerValidation.isInvalid());
        assertEquals(customerValidation.getError().getNestedErrors().get(0).getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfNullFirstName() {
        CustomerValidator validatorWithoutFirstName = customerValidator.firstName(null);
        Validation<AggregateError, Customer> customerValidation = validatorWithoutFirstName.validate();

        assertTrue(customerValidation.isInvalid());
        assertEquals(customerValidation.getError().getNestedErrors().get(0).getClass(), ArgumentEmptyError.class);
    }

    @Test
    public void errorInCaseOfEmptyFirstName() {
        CustomerValidator validatorWithEmptyFirstName = customerValidator.firstName("");
        Validation<AggregateError, Customer> customerValidation = validatorWithEmptyFirstName.validate();

        assertTrue(customerValidation.isInvalid());
        assertEquals(customerValidation.getError().getNestedErrors().get(0).getClass(), ArgumentEmptyError.class);

    }

    @Test
    public void errorsInCaseOfNullFirstNameAndInvalidEmail() {
        CustomerValidator validator = customerValidator.firstName(null).emailAddress("milenko.jovanovic#kolotree.com");
        Validation<AggregateError, Customer> customerValidation = validator.validate();

        assertTrue(customerValidation.isInvalid());
        assertEquals(customerValidation.getError().getNestedErrors().size(), 2);
        assertEquals(customerValidation.getError().getNestedErrors().get(0).getClass(), ArgumentEmptyError.class);
        assertEquals(customerValidation.getError().getNestedErrors().get(1).getClass(), InvalidEmailAddressError.class);
    }
}

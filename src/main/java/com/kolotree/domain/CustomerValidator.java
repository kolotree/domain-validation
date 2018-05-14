package com.kolotree.domain;


import com.kolotree.common.AggregateError;
import com.kolotree.common.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CustomerValidator {

    public static CustomerValidator aCustomerValidator() {
        return new CustomerValidator();
    }

    private Pattern emailPattern = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private Option<String> idOpt = Option.none();
    private Option<String> firstNameOpt = Option.none();
    private Option<String> lastNameOpt = Option.none();
    private Option<String> emailAddressOpt = Option.none();

    private CustomerValidator() {
    }

    public Validation<AggregateError, Customer> validate() {
        return Validation
                .combine(
                        validateId(),
                        validateFirstName(),
                        validateLastName(),
                        validateEmailAddress()
                )
                .ap(Customer::new)
                .mapError(Seq::asJava)
                .mapError(AggregateError::new);
    }

    public CustomerValidator id(String id) {
        this.idOpt = Option.of(id);
        return this;
    }

    public CustomerValidator firstName(String firstName) {
        this.firstNameOpt = Option.of(firstName);
        return this;
    }

    public CustomerValidator lastName(String lastName) {
        this.lastNameOpt = Option.of(lastName);
        return this;
    }

    public CustomerValidator emailAddress(String emailAddress) {
        this.emailAddressOpt = Option.of(emailAddress);
        return this;
    }

    private Validation<Error, String> validateId() {
        if (idOpt.map(String::isEmpty).getOrElse(true)) {
            return Validation.invalid(new ArgumentEmptyError("customer.id"));
        }
        return Validation.valid(idOpt.get());
    }

    private Validation<Error, String> validateFirstName() {
        if (firstNameOpt.map(String::isEmpty).getOrElse(true)) {
            return Validation.invalid(new ArgumentEmptyError("customer.firstName"));
        }
        return Validation.valid(firstNameOpt.get());
    }

    private Validation<Error, String> validateLastName() {
        if (lastNameOpt.map(String::isEmpty).getOrElse(true)) {
            return Validation.invalid(new ArgumentEmptyError("customer.lastName"));
        }
        return Validation.valid(lastNameOpt.get());
    }

    private Validation<Error, String> validateEmailAddress() {
        Option<Boolean> isEmailValidOpt = emailAddressOpt.map(emailAddress -> {
            Matcher matcher = emailPattern.matcher(emailAddress);
            return matcher.find();
        });

        if (!isEmailValidOpt.isDefined()) {
            return Validation.invalid(new ArgumentEmptyError("customer.emailAddress"));
        }

        if (!isEmailValidOpt.get()) {
            return Validation.invalid(new InvalidEmailAddressError(emailAddressOpt.get()));
        }

        return Validation.valid(emailAddressOpt.get());
    }
}

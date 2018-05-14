package com.kolotree.domain;

import com.kolotree.builder.Builder;
import com.kolotree.common.Error;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CustomerBuilder extends Builder<Customer> {

    public static CustomerBuilder aCustomerBuilder() {
        return new CustomerBuilder();
    }

    private Pattern emailPattern = compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private Optional<String> idOpt = Optional.empty();
    private Optional<String> firstNameOpt = Optional.empty();
    private Optional<String> lastNameOpt = Optional.empty();
    private Optional<String> emailAddressOpt = Optional.empty();

    private CustomerBuilder() {
    }

    @Override
    protected Optional<Error> Validate() {
        if (idOpt.map(String::isEmpty).orElse(true)) {
            return Optional.of(new ArgumentEmptyError("customer.id"));
        }

        if (firstNameOpt.map(String::isEmpty).orElse(true)) {
            return Optional.of(new ArgumentEmptyError("customer.firstName"));
        }

        if (lastNameOpt.map(String::isEmpty).orElse(true)) {
            return Optional.of(new ArgumentEmptyError("customer.lastname"));
        }

        Optional<Boolean> isEmailValidOpt = emailAddressOpt.map(emailAddress -> {
            Matcher matcher = emailPattern.matcher(emailAddress);
            return matcher.find();
        });

        if (!isEmailValidOpt.isPresent()) {
            return Optional.of(new ArgumentEmptyError("customer.emailAddress"));
        }

        if (!isEmailValidOpt.get()) {
            return Optional.of(new InvalidEmailAddressError(emailAddressOpt.get()));
        }

        return Optional.empty();
    }

    @Override
    protected Customer BuildInternal() {
        return new Customer(
                idOpt.get(),
                firstNameOpt.get(),
                lastNameOpt.get(),
                emailAddressOpt.get());
    }

    public CustomerBuilder id(String id) {
        this.idOpt = Optional.ofNullable(id);
        return this;
    }

    public CustomerBuilder firstName(String firstName) {
        this.firstNameOpt = Optional.ofNullable(firstName);
        return this;
    }

    public CustomerBuilder lastName(String lastName) {
        this.lastNameOpt = Optional.ofNullable(lastName);
        return this;
    }

    public CustomerBuilder emailAddress(String emailAddress) {
        this.emailAddressOpt = Optional.ofNullable(emailAddress);
        return this;
    }
}

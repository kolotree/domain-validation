package com.kolotree.common;

import java.util.Arrays;
import java.util.List;

public abstract class Error extends ValueObject<Error> {

    private final String message;

    public Error(String msg) {
        message = msg;
    }

    @Override
    public List<Object> GetEqualityComponents() {
        return Arrays.asList(message);
    }

    public String getMessage() {
        return message;
    }
}

package com.kolotree.common;

import java.util.List;

public class AggregateError extends Error {

    private final List<Error> nestedErrors;

    public AggregateError(List<Error> nestedErrors) {
        super("Aggregated error");
        this.nestedErrors = nestedErrors;
    }

    public List<Error> getNestedErrors() {
        return nestedErrors;
    }
}

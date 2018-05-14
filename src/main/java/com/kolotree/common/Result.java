package com.kolotree.common;

import java.util.Objects;

public class Result<T> {

    private final T value;
    private Error error;
    private boolean isSuccess;


    private Result(T value) {
        Objects.requireNonNull(value, "Value cannot be null.");
        this.isSuccess = true;
        this.value = value;
        this.error = null;
    }

    private Result(Error error) {
        Objects.requireNonNull(error, "Error cannot be null.");
        this.isSuccess = false;
        this.error = error;
        this.value = null;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public boolean isFailure() {
        return !this.isSuccess;
    }

    public T getValue() {
        if (this.isFailure()) {
            throw new IllegalStateException("Result is failure. Value not available.");
        }

        return this.value;
    }

    public Error getError() {
        if (this.isSuccess()) {
            throw new IllegalStateException("Result is success. Error not available.");
        }

        return this.error;
    }

    public boolean equals(Result<T> other) {
        if(this.isSuccess() && other.isFailure() || this.isFailure() && other.isSuccess()) {
            return false;
        }

        if(this.isSuccess() && other.isSuccess()) {
            return this.value.equals(other.value);
        }

        return this.error.equals(other.error);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        if(obj instanceof Result<?>) {
            Result<?> other = (Result<?>) obj;
            return this.value.equals(other.value);
        }
        return false;
    }

    public static <T> Result<T> ok(T value){
        return new Result<>(value);
    }

    public static <T> Result<T> failure(Error error) {
        return new Result<>(error);
    }
}

package com.kolotree.builder;

import com.kolotree.common.Result;
import com.kolotree.common.Error;

import java.util.Optional;

public abstract class Builder<T> {

    protected Optional<Error> Validate() {
        return Optional.empty();
    }

    public Result<T> Build() {
        return this.Validate()
                .<Result<T>>map(Result::failure)
                .orElseGet(() -> Result.ok(this.BuildInternal()));
    }

    protected abstract T BuildInternal();
}

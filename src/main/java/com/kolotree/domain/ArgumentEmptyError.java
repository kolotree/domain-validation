package com.kolotree.domain;

import com.kolotree.common.Error;

public class ArgumentEmptyError extends Error {

    public ArgumentEmptyError(String msg) {
        super(msg);
    }
}

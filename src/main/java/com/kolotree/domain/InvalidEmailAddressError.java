package com.kolotree.domain;

import com.kolotree.common.Error;

public class InvalidEmailAddressError extends Error {

    public InvalidEmailAddressError(String msg) {
        super(msg);
    }
}

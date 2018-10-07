package com.wxmlabs.springca.core;

public class SpringCAException extends RuntimeException {
    public SpringCAException(String message) {
        super(message);
    }

    public SpringCAException(String message, Throwable cause) {
        super(message, cause);
    }
}

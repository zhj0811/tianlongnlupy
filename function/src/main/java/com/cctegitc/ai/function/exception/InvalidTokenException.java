package com.cctegitc.ai.function.exception;

import org.apache.shiro.authc.CredentialsException;

public class InvalidTokenException extends CredentialsException {
    public InvalidTokenException(String message) {
        super(message);
    }
}

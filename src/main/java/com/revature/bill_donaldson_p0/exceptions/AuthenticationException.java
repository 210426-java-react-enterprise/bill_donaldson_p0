package com.revature.bill_donaldson_p0.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Could not authenticate using the provided credentials");
    }
}

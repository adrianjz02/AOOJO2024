package com.jeuxolympiques.jo2024.exception;

public class PasswordLengthException extends RuntimeException {

    public PasswordLengthException(String message) {
        super(message);
    }
}


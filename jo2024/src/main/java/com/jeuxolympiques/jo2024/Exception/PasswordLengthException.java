package com.jeuxolympiques.jo2024.Exception;

public class PasswordLengthException extends RuntimeException {

    public PasswordLengthException(String message) {
        super(message);
    }
}


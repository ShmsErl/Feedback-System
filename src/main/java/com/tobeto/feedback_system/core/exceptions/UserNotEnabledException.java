package com.tobeto.feedback_system.core.exceptions;

public class UserNotEnabledException extends RuntimeException   {
    public UserNotEnabledException(String message) {
        super(message);
    }
}

package com.tobeto.feedback_system.payload.requests;

public record SignInRequest(
        String username,
        String password
) {
}

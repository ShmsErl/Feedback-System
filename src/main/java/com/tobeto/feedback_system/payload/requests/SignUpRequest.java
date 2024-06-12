package com.tobeto.feedback_system.payload.requests;

import java.time.LocalDate;

public record SignUpRequest(
         String name,
         String surname,
         String username,
         String password,
         LocalDate birthDate
) {
}

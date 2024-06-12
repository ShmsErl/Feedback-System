package com.tobeto.feedback_system.payload.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {


    @NotNull(message = "The user id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 20, message = "Surname must be between 2 and 20 characters")
    private String surname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past(message = "Birth date must be in the past")
    public LocalDate birthDate;
}

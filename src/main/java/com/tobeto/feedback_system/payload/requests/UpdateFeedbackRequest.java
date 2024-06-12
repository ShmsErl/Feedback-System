package com.tobeto.feedback_system.payload.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UpdateFeedbackRequest {
    @NotNull
    @Positive
    private Long id;

    @NotEmpty(message = "Content must not be empty")
    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content;

    @NotNull(message = "Date must not be null")
    private LocalDateTime date;

    @NotNull(message = "User ID must not be null")
    @Positive(message = "Id must be a positive number.")
    private Long userId;

    @NotNull(message = "Category ID must not be null")
    @Positive(message = "Id must be a positive number.")
    private Long categoryId;
}

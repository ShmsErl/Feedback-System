package com.tobeto.feedback_system.payload.requests;

import com.tobeto.feedback_system.models.concretes.Category;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.payload.responses.GetCategoryResponse;
import com.tobeto.feedback_system.payload.responses.GetUserResponse;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AddFeedbackRequest {

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
    private Long categoryId;}

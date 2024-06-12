package com.tobeto.feedback_system.payload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateCategoryRequest {
    @NotNull
    @Positive(message = "Id must be a positive number.")
    private Long id;
    @NotNull
    private String name;

}

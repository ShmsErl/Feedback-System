package com.tobeto.feedback_system.payload.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetCategoryResponse {
    private Long id;
    private String  name;
}

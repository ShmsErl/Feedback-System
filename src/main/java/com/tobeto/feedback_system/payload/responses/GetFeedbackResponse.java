package com.tobeto.feedback_system.payload.responses;

import com.tobeto.feedback_system.models.concretes.Category;
import com.tobeto.feedback_system.models.concretes.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFeedbackResponse {

    private Long id;

    private String content;

    private LocalDateTime date;


    private  GetUserResponse user;


    private GetCategoryResponse category;

    private boolean isActive;
}

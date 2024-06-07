package com.tobeto.feedback_system.models.concretes;

import com.tobeto.feedback_system.models.abstracts.BaseEntity;
import com.tobeto.feedback_system.models.concretes.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback extends BaseEntity {


    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private String content;

    private LocalDateTime date;

}

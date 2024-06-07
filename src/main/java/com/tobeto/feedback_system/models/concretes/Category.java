package com.tobeto.feedback_system.models.concretes;

import com.tobeto.feedback_system.models.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Feedback> feedbacks;
}

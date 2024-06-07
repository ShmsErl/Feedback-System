package com.tobeto.feedback_system.repository;

import com.tobeto.feedback_system.models.concretes.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

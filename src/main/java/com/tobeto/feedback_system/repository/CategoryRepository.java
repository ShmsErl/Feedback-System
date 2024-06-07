package com.tobeto.feedback_system.repository;

import com.tobeto.feedback_system.models.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

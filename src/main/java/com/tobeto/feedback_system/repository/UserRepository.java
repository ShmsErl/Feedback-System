package com.tobeto.feedback_system.repository;

import com.tobeto.feedback_system.models.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

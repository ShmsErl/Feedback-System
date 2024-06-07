package com.tobeto.feedback_system.models.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public enum Role {

        ADMIN("ADMIN"),
        USER("USER");

        private final String roleName;

        Role(String roleName) {
                this.roleName = roleName;
        }

        public String getRoleName() {
                return roleName;
        }

}

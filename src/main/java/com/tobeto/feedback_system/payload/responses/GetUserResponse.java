package com.tobeto.feedback_system.payload.responses;

import com.tobeto.feedback_system.models.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse  {

    private int id;

    private String name;

    private String surname;

    private String username;

    private LocalDate birthDate;

    private Role role;
}

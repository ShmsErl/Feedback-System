package com.tobeto.feedback_system.config;



import com.tobeto.feedback_system.models.concretes.Role;
import com.tobeto.feedback_system.models.concretes.User;
import com.tobeto.feedback_system.repository.UserRepository;
import com.tobeto.feedback_system.services.abstracts.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User defaultUser = User
                    .builder()
                    .name("default")
                    .surname("default")
                    .username("deneme")
                    .password(passwordEncoder.encode("password"))
                    .isEnabled(true)
                    .role(Role.USER)
                    .build();
            userRepository.save(defaultUser);

            User admin = User
                    .builder()
                    .name("admin")
                    .surname("admin")
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.ADMIN)
                    .isEnabled(true)
                    .build();


            userRepository.save(admin);
        }
    }
}


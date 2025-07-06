package com.codesa.schoolRegistry.infrastructure.Configurations;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codesa.schoolRegistry.core.domain.Staff;
import com.codesa.schoolRegistry.core.domain.User;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDefaultUser(
        UserRepository userRepository,
        StaffRepository staffRepository,
        PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String defaultUsername = "admin";
            String defaultPassword = "admin123";

            if (userRepository.findByUsername(defaultUsername).isEmpty()) {

                Staff staff = Staff.builder()
                        .firstName("Administrador")
                        .lastName("Principal")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .email("admin@codesa.com")
                        .position("Administrador General")
                        .department("Dirección")
                        .phone("1234567890")
                        .build();

                staff = staffRepository.save(staff);

                User user = User.builder()
                        .username(defaultUsername)
                        .password(passwordEncoder.encode(defaultPassword))
                        .role("ADMIN")
                        .person(staff)
                        .build();

                userRepository.save(user);
                System.out.println("✅ Usuario admin creado por defecto.");
            } else {
                System.out.println("Usuario admin ya existe. No se creó otro.");
            }
        };
    }
}


package com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Update;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
public class UpdateTeacherCommand {
    
    @NotNull
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d+", message = "Phone must contain only digits")
    private String phone;

    @NotBlank(message = "Specialty is required")
    @Size(max = 100, message = "Specialty must not exceed 100 characters")
    private String specialty;

    @NotNull(message = "Hiring date is required")
    @PastOrPresent(message = "Hiring date must be in the past or present")
    private LocalDate hiringDate;
}

package com.codesa.schoolRegistry.application.UserCase.Student.Commands.Create;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Past;
import lombok.*;
@Getter
@Setter
public class CreateStudentCommand {
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

    @NotBlank(message = "Enrollment number is required")
    private String enrollmentNumber;

    @NotBlank(message = "Grade is required")
    private String grade;
}

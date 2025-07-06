package com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Update;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
public class UpdateStaffCommand {
    
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

    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    @NotBlank(message = "Department is required")
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
  
}

package com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Create;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateEnrollmentCommand {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @NotNull
    private LocalDate enrollmentDate;
}
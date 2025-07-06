package com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Update;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEnrollmentCommand {

    @NotNull
    private Long id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @NotNull
    private LocalDate enrollmentDate;
}


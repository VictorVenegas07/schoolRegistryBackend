package com.codesa.schoolRegistry.application.UserCase.Enrollment.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    private Long id;
    private LocalDate enrollmentDate;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;
}
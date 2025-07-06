package com.codesa.schoolRegistry.application.UserCase.Enrollment.Querys.GetAllEnrollments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllEnrollmentsQuery {
    private int page;
    private int size;
}
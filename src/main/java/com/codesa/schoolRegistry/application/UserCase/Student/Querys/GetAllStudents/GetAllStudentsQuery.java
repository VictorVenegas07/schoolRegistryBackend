package com.codesa.schoolRegistry.application.UserCase.Student.Querys.GetAllStudents;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllStudentsQuery {
    private int page;
    private int size;
}

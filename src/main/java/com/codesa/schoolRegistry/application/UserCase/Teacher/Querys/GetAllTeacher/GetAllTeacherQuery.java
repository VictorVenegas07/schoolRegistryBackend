package com.codesa.schoolRegistry.application.UserCase.Teacher.Querys.GetAllTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllTeacherQuery {
    private int page;
    private int size;
}

package com.codesa.schoolRegistry.application.UserCase.Course.Query.GetAllCourses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllCoursesQuery {
    private int page;
    private int size;
}

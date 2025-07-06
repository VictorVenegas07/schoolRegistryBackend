package com.codesa.schoolRegistry.application.UserCase.Course.Query.GetCourseById;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;

@Service
public class GetCourseByIdHandler implements RequestHandler<GetCourseByIdQuery, Course> {

    private final CourseRepository courseRepository;

    public GetCourseByIdHandler(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course handle(GetCourseByIdQuery query) {
        return courseRepository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}

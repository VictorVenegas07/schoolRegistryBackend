package com.codesa.schoolRegistry.application.UserCase.Course.Query.GetAllCourses;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;


@Service
public class GetAllCoursesHandler implements RequestHandler<GetAllCoursesQuery, Page<Course>> {

    private final CourseRepository repository;

    public GetAllCoursesHandler(CourseRepository repository) {
        this.repository = repository;
    }

    public Page<Course> handle(GetAllCoursesQuery query) {
        PageRequest pageable = PageRequest.of(query.getPage(), query.getSize());
        return repository.findAll(pageable);
    }
}

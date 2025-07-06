package com.codesa.schoolRegistry.application.UserCase.Course.Command.Delete;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;

@Service
public class DeleteCourseHandler implements RequestHandler<DeleteCourseCommand, Boolean> {

    private final CourseRepository repository;

    public DeleteCourseHandler(CourseRepository repository) {
        this.repository = repository;
    }

    public Boolean handle(DeleteCourseCommand command) {
        Course course = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        repository.delete(course);
        return true;
    }
}


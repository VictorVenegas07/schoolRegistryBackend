package com.codesa.schoolRegistry.application.UserCase.Course.Command.Create;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;

import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.core.domain.Teacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseHandler implements RequestHandler<CreateCourseCommand, Course> {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CreateCourseHandler(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public Course handle(CreateCourseCommand command) {
        Teacher teacher = null;
        if (command.getTeacherId() != null) {
            teacher = teacherRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        }

        Course course = Course.builder()
                .name(command.getName())
                .description(command.getDescription())
                .credits(command.getCredits())
                .teacher(teacher)
                .build();

        return courseRepository.save(course);
    }
}
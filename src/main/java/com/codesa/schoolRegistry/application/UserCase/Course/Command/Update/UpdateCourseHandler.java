package com.codesa.schoolRegistry.application.UserCase.Course.Command.Update;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.core.domain.Teacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

@Service
public class UpdateCourseHandler implements RequestHandler<UpdateCourseCommand, Course> {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public UpdateCourseHandler(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public Course handle(UpdateCourseCommand command) {
        Course course = courseRepository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // If the teacherId is null, we do not change the teacher
        if (command.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
            course.setTeacher(teacher);
        }

        course.setName(command.getName());
        course.setDescription(command.getDescription());
        course.setCredits(command.getCredits());

        return courseRepository.save(course);
    }
}


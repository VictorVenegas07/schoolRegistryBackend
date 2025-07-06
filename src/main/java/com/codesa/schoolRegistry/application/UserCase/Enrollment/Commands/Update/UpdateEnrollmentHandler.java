package com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Update;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.InvalidInputException;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.core.domain.Enrollment;
import com.codesa.schoolRegistry.core.domain.Student;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.EnrollmentRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;

@Service
public class UpdateEnrollmentHandler implements RequestHandler<UpdateEnrollmentCommand, Enrollment> {

        private final EnrollmentRepository repository;
        private final StudentRepository studentRepository;
        private final CourseRepository courseRepository;

        public UpdateEnrollmentHandler(EnrollmentRepository repository,
                        StudentRepository studentRepository,
                        CourseRepository courseRepository) {
                this.repository = repository;
                this.studentRepository = studentRepository;
                this.courseRepository = courseRepository;
        }

        @Override
        public Enrollment handle(UpdateEnrollmentCommand command) {
                Enrollment enrollment = repository.findById(command.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

                boolean sameStudent = enrollment.getStudent().getId().equals(command.getStudentId());
                boolean sameCourse = enrollment.getCourse().getId().equals(command.getCourseId());

                if (!(sameStudent && sameCourse)) {
                        if (repository.existsByStudentIdAndCourseId(command.getStudentId(), command.getCourseId())) {
                                throw new InvalidInputException("The student is already enrolled in this course.");
                        }
                }

                Student student = studentRepository.findById(command.getStudentId())
                                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

                Course course = courseRepository.findById(command.getCourseId())
                                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setEnrollmentDate(command.getEnrollmentDate());

                return repository.save(enrollment);
        }
}

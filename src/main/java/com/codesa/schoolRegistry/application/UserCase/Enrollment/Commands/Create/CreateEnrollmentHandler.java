package com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Create;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.InvalidInputException;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Course;
import com.codesa.schoolRegistry.core.domain.Enrollment;
import com.codesa.schoolRegistry.core.domain.Student;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.CourseRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.EnrollmentRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateEnrollmentHandler implements RequestHandler<CreateEnrollmentCommand, Enrollment> {

        private final EnrollmentRepository enrollmentRepository;
        private final StudentRepository studentRepository;
        private final CourseRepository courseRepository;

        public CreateEnrollmentHandler(EnrollmentRepository enrollmentRepository,
                        StudentRepository studentRepository,
                        CourseRepository courseRepository) {
                this.enrollmentRepository = enrollmentRepository;
                this.studentRepository = studentRepository;
                this.courseRepository = courseRepository;
        }

        @Transactional
        @Override
        public Enrollment handle(CreateEnrollmentCommand command) {
                if (enrollmentRepository.existsByStudentIdAndCourseId(command.getStudentId(), command.getCourseId())) {
                        throw new InvalidInputException("The student is already enrolled in this course.");
                }

                Student student = studentRepository.findById(command.getStudentId())
                                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

                Course course = courseRepository.findById(command.getCourseId())
                                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

                Enrollment enrollment = Enrollment.builder()
                                .student(student)
                                .course(course)
                                .enrollmentDate(command.getEnrollmentDate())
                                .build();

                return enrollmentRepository.save(enrollment);
        }
}
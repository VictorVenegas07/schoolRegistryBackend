package com.codesa.schoolRegistry.application.UserCase.Student.Commands.Create;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.InvalidInputException;
import com.codesa.schoolRegistry.core.domain.Student;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;


import jakarta.transaction.Transactional;

@Service
public class CreateStudentHandler implements RequestHandler<CreateStudentCommand, Student> {

    private final StudentRepository studentRepository;

    public CreateStudentHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student handle(CreateStudentCommand command) {

        if (studentRepository.existsByEmail(command.getEmail())) {
            throw new InvalidInputException("Email already exists");
        }

        if (studentRepository.existsByEnrollmentNumber(command.getEnrollmentNumber())) {
            throw new InvalidInputException("Enrollment number already exists");
        }
        Student student = Student.builder()
            .firstName(command.getFirstName())
            .lastName(command.getLastName())
            .birthDate(command.getBirthDate())
            .email(command.getEmail())
            .phone(command.getPhone())
            .enrollmentNumber(command.getEnrollmentNumber())
            .grade(command.getGrade())
            .build();

        return studentRepository.save(student);
    }
}

package com.codesa.schoolRegistry.application.UserCase.Student.Commands.Update;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Student;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;

@Service
public class UpdateStudentHandler implements RequestHandler<UpdateStudentCommand, Student> {
 private final StudentRepository repository;

    public UpdateStudentHandler(StudentRepository repository) {
        this.repository = repository;
    }

    public Student handle(UpdateStudentCommand command) {
        Student student = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
        if (repository.existsByEmailAndIdNot(command.getEmail(), command.getId())) {
            throw new ResourceNotFoundException("Email already exists");
        }
        if (repository.existsByEnrollmentNumberAndIdNot(command.getEnrollmentNumber(), command.getId())) {
            throw new ResourceNotFoundException("Enrollment number already exists");
        }

        student.setFirstName(command.getFirstName());
        student.setLastName(command.getLastName());
        student.setBirthDate(command.getBirthDate());
        student.setEmail(command.getEmail());
        student.setPhone(command.getPhone());
        student.setEnrollmentNumber(command.getEnrollmentNumber());
        student.setGrade(command.getGrade());

        return repository.save(student);
    }
}

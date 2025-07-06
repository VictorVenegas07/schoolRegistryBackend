package com.codesa.schoolRegistry.application.UserCase.Student.Commands.Delete;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteStudentHandler implements RequestHandler<DeleteStudentCommand, Boolean> {

    private final StudentRepository repository;

    public DeleteStudentHandler(StudentRepository repository) {
        this.repository = repository;
    }

    public Boolean handle(DeleteStudentCommand command) {
        var student = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        repository.delete(student);
        return true;
    }
}
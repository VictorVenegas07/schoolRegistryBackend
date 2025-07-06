package com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Delete;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteTeacherHandler implements RequestHandler<DeleteTeacherCommand, Boolean> {

    private final TeacherRepository repository;

    public DeleteTeacherHandler(TeacherRepository repository) {
        this.repository = repository;
    }

    public Boolean handle(DeleteTeacherCommand command) {
        var teacher = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        repository.delete(teacher);
        return true;
    }
}
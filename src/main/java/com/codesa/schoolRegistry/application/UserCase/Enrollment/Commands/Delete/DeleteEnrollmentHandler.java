package com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Delete;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Enrollment;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.EnrollmentRepository;

@Service
public class DeleteEnrollmentHandler implements RequestHandler<DeleteEnrollmentCommand, Void> {

    private final EnrollmentRepository repository;

    public DeleteEnrollmentHandler(EnrollmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteEnrollmentCommand command) {
        Enrollment enrollment = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
        repository.delete(enrollment);
        return null;
    }
}


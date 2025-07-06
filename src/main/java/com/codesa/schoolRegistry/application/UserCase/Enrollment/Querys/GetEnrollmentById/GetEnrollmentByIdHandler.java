package com.codesa.schoolRegistry.application.UserCase.Enrollment.Querys.GetEnrollmentById;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Enrollment;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.EnrollmentRepository;

@Service
public class GetEnrollmentByIdHandler implements RequestHandler<GetEnrollmentByIdQuery, Enrollment> {

    private final EnrollmentRepository repository;

    public GetEnrollmentByIdHandler(EnrollmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Enrollment handle(GetEnrollmentByIdQuery query) {
        return repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }
}

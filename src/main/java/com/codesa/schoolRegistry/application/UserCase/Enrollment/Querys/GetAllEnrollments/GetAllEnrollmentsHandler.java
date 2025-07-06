package com.codesa.schoolRegistry.application.UserCase.Enrollment.Querys.GetAllEnrollments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.application.Mappers.EnrollmentMapper;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.DTOs.EnrollmentDto;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.EnrollmentRepository;

@Service
public class GetAllEnrollmentsHandler implements RequestHandler<GetAllEnrollmentsQuery, Page<EnrollmentDto>> {

    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;

    public GetAllEnrollmentsHandler(EnrollmentRepository repository, EnrollmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<EnrollmentDto> handle(GetAllEnrollmentsQuery query) {
        PageRequest pageable = PageRequest.of(query.getPage(), query.getSize());
        
        
        return repository.findAll(pageable)
                         .map(mapper::toDto);
    }
}
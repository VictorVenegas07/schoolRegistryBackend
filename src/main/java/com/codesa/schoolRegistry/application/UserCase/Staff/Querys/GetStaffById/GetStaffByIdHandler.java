package com.codesa.schoolRegistry.application.UserCase.Staff.Querys.GetStaffById;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Staff;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;

import org.springframework.stereotype.Service;
@Service
public class GetStaffByIdHandler implements RequestHandler<GetStaffByIdQuery, Staff> {

    private final StaffRepository repository;

    public GetStaffByIdHandler(StaffRepository repository) {
        this.repository = repository;
    }

    public Staff handle(GetStaffByIdQuery query) {
        return repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));
    }
}
package com.codesa.schoolRegistry.application.UserCase.Staff.Querys.GetAllStaff;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;
import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.domain.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetAllStaffHandler implements RequestHandler<GetAllStaffQuery, Page<Staff>> {

    private final StaffRepository repository;

    public GetAllStaffHandler(StaffRepository repository) {
        this.repository = repository;
    }

    public Page<Staff> handle(GetAllStaffQuery query) {
        PageRequest pageable = PageRequest.of(query.getPage(), query.getSize());
        return repository.findAll(pageable);
    }
}
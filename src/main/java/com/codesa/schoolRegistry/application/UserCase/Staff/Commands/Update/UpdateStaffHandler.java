package com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Update;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Staff;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;

@Service
public class UpdateStaffHandler implements RequestHandler<UpdateStaffCommand, Staff> {
 private final StaffRepository repository;

    public UpdateStaffHandler(StaffRepository repository) {
        this.repository = repository;
    }

    public Staff handle(UpdateStaffCommand command) {
        Staff staff = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

        if (repository.existsByEmailAndIdNot(command.getEmail(), command.getId())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        staff.setFirstName(command.getFirstName());
        staff.setLastName(command.getLastName());
        staff.setBirthDate(command.getBirthDate());
        staff.setEmail(command.getEmail());
        staff.setPhone(command.getPhone());
        staff.setPosition(command.getPosition());
        staff.setDepartment(command.getDepartment());

        return repository.save(staff);
    }
}

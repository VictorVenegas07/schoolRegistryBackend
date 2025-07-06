package com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Delete;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteStaffHandler implements RequestHandler<DeleteStaffCommand, Boolean> {

    private final StaffRepository repository;
    private final UserRepository userRepository;

    public DeleteStaffHandler(StaffRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Boolean handle(DeleteStaffCommand command) {
    var staff = repository.findById(command.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Staff not found"));

    userRepository.findByPersonId(staff.getId()).ifPresent(userRepository::delete);

    repository.delete(staff);

    return true;
}
}
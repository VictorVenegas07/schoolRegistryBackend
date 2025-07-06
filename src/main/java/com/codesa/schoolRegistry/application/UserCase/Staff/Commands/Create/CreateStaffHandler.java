package com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Create;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.InvalidInputException;
import com.codesa.schoolRegistry.core.domain.Staff;
import com.codesa.schoolRegistry.core.domain.User;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.PersonRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StaffRepository;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateStaffHandler implements RequestHandler<CreateStaffCommand, Staff> {

    private final StaffRepository staffRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public CreateStaffHandler(StaffRepository staffRepository, PersonRepository personRepository, UserRepository userRepository) {
        this.staffRepository = staffRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Staff handle(CreateStaffCommand command) {

        if (personRepository.existsByEmail(command.getEmail())) {
            throw new InvalidInputException("Email already exists");
        }

        Staff staff = Staff.builder()
            .firstName(command.getFirstName())
            .lastName(command.getLastName())
            .birthDate(command.getBirthDate())
            .email(command.getEmail())
            .phone(command.getPhone())
            .position(command.getPosition())
            .department(command.getDepartment())
            .build();

            var response = staffRepository.save(staff);
        
        User user = User.builder()
            .username(command.getUserName())
            .password(command.getPassword())
            .role("STAFF")
            .person(response)
            .build();

        userRepository.save(user);
        return response;
    }
}

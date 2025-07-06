package com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Update;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Teacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

@Service
public class UpdateTeacherHandler implements RequestHandler<UpdateTeacherCommand, Teacher> {
 private final TeacherRepository repository;

    public UpdateTeacherHandler(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher handle(UpdateTeacherCommand command) {
        Teacher teacher = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        if (repository.existsByEmailAndIdNot(command.getEmail(), command.getId())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        teacher.setFirstName(command.getFirstName());
        teacher.setLastName(command.getLastName());
        teacher.setBirthDate(command.getBirthDate());
        teacher.setEmail(command.getEmail());
        teacher.setPhone(command.getPhone());
        teacher.setSpecialty(command.getSpecialty());
        teacher.setHiringDate(command.getHiringDate());
        teacher.setSpecialty(command.getSpecialty());
        teacher.setHiringDate(command.getHiringDate());


        return repository.save(teacher);
    }
}

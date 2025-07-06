package com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Create;

import org.springframework.stereotype.Service;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.InvalidInputException;
import com.codesa.schoolRegistry.core.domain.Teacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateTeacherHandler implements RequestHandler<CreateTeacherCommand, Teacher> {

    private final TeacherRepository teacherRepository;

    public CreateTeacherHandler(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public Teacher handle(CreateTeacherCommand command) {

        if (teacherRepository.existsByEmail(command.getEmail())) {
            throw new InvalidInputException("Email already exists");
        }

        Teacher teacher = Teacher.builder()
            .firstName(command.getFirstName())
            .lastName(command.getLastName())
            .birthDate(command.getBirthDate())
            .email(command.getEmail())
            .phone(command.getPhone())
            .specialty(command.getSpecialty())
            .hiringDate(command.getHiringDate())
            .build();

        return teacherRepository.save(teacher);
    }
}

package com.codesa.schoolRegistry.application.UserCase.Teacher.Querys.GetTeacherById;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Teacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;

import org.springframework.stereotype.Service;
@Service
public class GetTeacherByIdHandler implements RequestHandler<GetTeacherByIdQuery, Teacher> {

    private final TeacherRepository repository;

    public GetTeacherByIdHandler(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher handle(GetTeacherByIdQuery query) {
        return repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }
}
package com.codesa.schoolRegistry.application.UserCase.Student.Querys.GetStudentById;

import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.Exceptions.ResourceNotFoundException;
import com.codesa.schoolRegistry.core.domain.Student;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;

import org.springframework.stereotype.Service;
@Service
public class GetStudentByIdHandler implements RequestHandler<GetStudentByIdQuery, Student> {

    private final StudentRepository repository;

    public GetStudentByIdHandler(StudentRepository repository) {
        this.repository = repository;
    }

    public Student handle(GetStudentByIdQuery query) {
        return repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
}
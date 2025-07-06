package com.codesa.schoolRegistry.application.UserCase.Student.Querys.GetAllStudents;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.StudentRepository;
import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.domain.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetAllStudentsHandler implements RequestHandler<GetAllStudentsQuery, Page<Student>> {

    private final StudentRepository repository;

    public GetAllStudentsHandler(StudentRepository repository) {
        this.repository = repository;
    }

    public Page<Student> handle(GetAllStudentsQuery query) {
        PageRequest pageable = PageRequest.of(query.getPage(), query.getSize());
        return repository.findAll(pageable);
    }
}
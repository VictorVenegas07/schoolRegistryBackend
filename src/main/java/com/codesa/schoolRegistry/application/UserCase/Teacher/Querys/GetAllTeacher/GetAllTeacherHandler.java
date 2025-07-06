package com.codesa.schoolRegistry.application.UserCase.Teacher.Querys.GetAllTeacher;
import com.codesa.schoolRegistry.infrastructure.adapters.Repositories.TeacherRepository;
import com.codesa.schoolRegistry.application.Interfaces.RequestHandler;
import com.codesa.schoolRegistry.core.domain.Teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetAllTeacherHandler implements RequestHandler<GetAllTeacherQuery, Page<Teacher>> {

    private final TeacherRepository repository;

    public GetAllTeacherHandler(TeacherRepository repository) {
        this.repository = repository;
    }

    public Page<Teacher> handle(GetAllTeacherQuery query) {
        var pageable = PageRequest.of(query.getPage(), query.getSize());
        return repository.findAll(pageable);
    }
}
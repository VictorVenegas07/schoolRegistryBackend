package com.codesa.schoolRegistry.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codesa.schoolRegistry.application.UserCase.Student.Commands.Create.CreateStudentCommand;
import com.codesa.schoolRegistry.application.UserCase.Student.Commands.Delete.DeleteStudentCommand;
import com.codesa.schoolRegistry.application.UserCase.Student.Commands.Update.UpdateStudentCommand;
import com.codesa.schoolRegistry.application.UserCase.Student.Querys.GetAllStudents.GetAllStudentsQuery;
import com.codesa.schoolRegistry.application.UserCase.Student.Querys.GetStudentById.GetStudentByIdQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import com.codesa.schoolRegistry.core.domain.Student;
import org.springframework.data.domain.Page;


import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final Mediator mediator;

    public StudentController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody CreateStudentCommand command) {
        Student created = mediator.dispatch(command);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediator.dispatch(new GetStudentByIdQuery(id)));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var query = new GetAllStudentsQuery(page, size);
        return ResponseEntity.ok(mediator.dispatch(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody UpdateStudentCommand command) {
        command.setId(id);
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediator.dispatch(new DeleteStudentCommand(id));
        return ResponseEntity.noContent().build();
    }
}

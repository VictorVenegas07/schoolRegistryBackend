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

import com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Create.CreateTeacherCommand;
import com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Delete.DeleteTeacherCommand;
import com.codesa.schoolRegistry.application.UserCase.Teacher.Commands.Update.UpdateTeacherCommand;
import com.codesa.schoolRegistry.application.UserCase.Teacher.Querys.GetAllTeacher.GetAllTeacherQuery;
import com.codesa.schoolRegistry.application.UserCase.Teacher.Querys.GetTeacherById.GetTeacherByIdQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import com.codesa.schoolRegistry.core.domain.Teacher;

import org.springframework.data.domain.Page;


import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final Mediator mediator;

    public TeacherController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody CreateTeacherCommand command) {
        Teacher created = mediator.dispatch(command);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediator.dispatch(new GetTeacherByIdQuery(id)));
    }

    @GetMapping
    public ResponseEntity<Page<Teacher>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var query = new GetAllTeacherQuery(page, size);
        return ResponseEntity.ok(mediator.dispatch(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @Valid @RequestBody UpdateTeacherCommand command) {
        command.setId(id);
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediator.dispatch(new DeleteTeacherCommand(id));
        return ResponseEntity.noContent().build();
    }
}

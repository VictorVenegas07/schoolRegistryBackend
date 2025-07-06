package com.codesa.schoolRegistry.infrastructure.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Create.CreateEnrollmentCommand;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Delete.DeleteEnrollmentCommand;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.Commands.Update.UpdateEnrollmentCommand;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.DTOs.EnrollmentDto;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.Querys.GetAllEnrollments.GetAllEnrollmentsQuery;
import com.codesa.schoolRegistry.application.UserCase.Enrollment.Querys.GetEnrollmentById.GetEnrollmentByIdQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import com.codesa.schoolRegistry.core.domain.Enrollment;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final Mediator mediator;

    public EnrollmentController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Enrollment> create(@Valid @RequestBody CreateEnrollmentCommand command) {
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediator.dispatch(new GetEnrollmentByIdQuery(id)));
    }

    @GetMapping
    public ResponseEntity<Page<EnrollmentDto>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.dispatch(new GetAllEnrollmentsQuery(page, size)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id,
                                             @Valid @RequestBody UpdateEnrollmentCommand command) {
        command.setId(id);
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediator.dispatch(new DeleteEnrollmentCommand(id));
        return ResponseEntity.noContent().build();
    }
}
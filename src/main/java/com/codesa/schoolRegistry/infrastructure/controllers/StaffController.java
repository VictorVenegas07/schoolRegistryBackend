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

import com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Create.CreateStaffCommand;
import com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Delete.DeleteStaffCommand;
import com.codesa.schoolRegistry.application.UserCase.Staff.Commands.Update.UpdateStaffCommand;
import com.codesa.schoolRegistry.application.UserCase.Staff.Querys.GetAllStaff.GetAllStaffQuery;
import com.codesa.schoolRegistry.application.UserCase.Staff.Querys.GetStaffById.GetStaffByIdQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import com.codesa.schoolRegistry.core.domain.Staff;

import org.springframework.data.domain.Page;


import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final Mediator mediator;

    public StaffController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Staff> create(@Valid @RequestBody CreateStaffCommand command) {
        Staff created = mediator.dispatch(command);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediator.dispatch(new GetStaffByIdQuery(id)));
    }

    @GetMapping
    public ResponseEntity<Page<Staff>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        var query = new GetAllStaffQuery(page, size);
        return ResponseEntity.ok(mediator.dispatch(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> update(@PathVariable Long id, @Valid @RequestBody UpdateStaffCommand command) {
        command.setId(id);
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediator.dispatch(new DeleteStaffCommand(id));
        return ResponseEntity.noContent().build();
    }
}

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

import com.codesa.schoolRegistry.application.UserCase.Course.Command.Create.CreateCourseCommand;
import com.codesa.schoolRegistry.application.UserCase.Course.Command.Delete.DeleteCourseCommand;
import com.codesa.schoolRegistry.application.UserCase.Course.Command.Update.UpdateCourseCommand;
import com.codesa.schoolRegistry.application.UserCase.Course.Query.GetAllCourses.GetAllCoursesQuery;
import com.codesa.schoolRegistry.application.UserCase.Course.Query.GetCourseById.GetCourseByIdQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import com.codesa.schoolRegistry.core.domain.Course;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final Mediator mediator;

    public CourseController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody CreateCourseCommand command) {
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @GetMapping
    public ResponseEntity<Page<Course>> getAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.dispatch(new GetAllCoursesQuery(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediator.dispatch(new GetCourseByIdQuery(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id,
                                         @Valid @RequestBody UpdateCourseCommand command) {
        command.setId(id);
        return ResponseEntity.ok(mediator.dispatch(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediator.dispatch(new DeleteCourseCommand(id));
        return ResponseEntity.noContent().build();
    }
}
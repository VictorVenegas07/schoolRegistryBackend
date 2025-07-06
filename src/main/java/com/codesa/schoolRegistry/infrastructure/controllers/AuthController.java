package com.codesa.schoolRegistry.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codesa.schoolRegistry.application.UserCase.Auth.Querys.Login.LoginQuery;
import com.codesa.schoolRegistry.application.mediator.Mediator;
import jakarta.validation.Valid;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

   private final Mediator mediator;

    public AuthController(Mediator mediator) {
        this.mediator = mediator;
    }

        @PostMapping
    public ResponseEntity<Map<String, String>> create(@Valid @RequestBody LoginQuery command) {
        String token = mediator.dispatch(command);
        return ResponseEntity.ok(Map.of("token", token));
    }
}


package com.codesa.schoolRegistry.core.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor 
public class Teacher extends Person {

   @Column(name = "especialidad", nullable = false)
    private String specialty;

    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate hiringDate;
}


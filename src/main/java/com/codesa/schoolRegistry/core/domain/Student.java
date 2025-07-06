package com.codesa.schoolRegistry.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor 
public class Student extends Person {

    @Column(name = "numero_matricula", nullable = false, unique = true)
    private String enrollmentNumber;

    @Column(name = "grado", nullable = false)
    private String grade;
}
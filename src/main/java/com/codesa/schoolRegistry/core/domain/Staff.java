package com.codesa.schoolRegistry.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "administrativo")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Staff extends Person {

    @Column(name = "cargo", nullable = false)
    private String position;

    @Column(name = "departamento", nullable = false)
    private String department;

}

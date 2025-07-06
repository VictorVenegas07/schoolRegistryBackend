package com.codesa.schoolRegistry.core.domain;

import com.codesa.schoolRegistry.core.domain.Base.BaseEntity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor 
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "persona")
public class Person extends BaseEntity {

    @Column(name = "nombre", nullable = false)
    private String firstName;

    @Column(name = "apellido", nullable = false)
    private String lastName;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefono", nullable = false)
    private String phone;

}

package com.codesa.schoolRegistry.core.domain;

import com.codesa.schoolRegistry.core.domain.Base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor 
public class User extends BaseEntity {

    @Column(name = "usuario", unique = true, nullable = false)
    private String username;
    @Column(name = "contrasena", nullable = false)
    private String password;
    @Column(name = "rol", nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Person person;
}

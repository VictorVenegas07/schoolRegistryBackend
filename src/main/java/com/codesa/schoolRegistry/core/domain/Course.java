package com.codesa.schoolRegistry.core.domain;

import com.codesa.schoolRegistry.core.domain.Base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor 
@Entity
@Table(name = "curso")
public class Course extends BaseEntity {

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "creditos", nullable = false)
    private int credits;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = true)
    private Teacher teacher;
}

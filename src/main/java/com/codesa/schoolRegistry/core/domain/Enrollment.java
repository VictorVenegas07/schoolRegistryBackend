package com.codesa.schoolRegistry.core.domain;

import java.time.LocalDate;

import com.codesa.schoolRegistry.core.domain.Base.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Enrollment extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate enrollmentDate;
}

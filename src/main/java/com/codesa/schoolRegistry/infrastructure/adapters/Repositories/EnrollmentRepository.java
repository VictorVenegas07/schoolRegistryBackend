package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;

import com.codesa.schoolRegistry.core.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
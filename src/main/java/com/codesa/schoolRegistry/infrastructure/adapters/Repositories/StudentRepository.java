package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;

import com.codesa.schoolRegistry.core.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    boolean existsByEnrollmentNumber(String enrollmentNumber);
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByEnrollmentNumberAndIdNot(String enrollmentNumber, Long id);
}
package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesa.schoolRegistry.core.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}

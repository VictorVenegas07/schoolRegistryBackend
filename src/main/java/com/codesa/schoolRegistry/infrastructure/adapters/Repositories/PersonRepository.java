package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codesa.schoolRegistry.core.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}

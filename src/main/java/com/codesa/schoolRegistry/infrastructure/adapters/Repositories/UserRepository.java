package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesa.schoolRegistry.core.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByPersonId(Long personId);
}
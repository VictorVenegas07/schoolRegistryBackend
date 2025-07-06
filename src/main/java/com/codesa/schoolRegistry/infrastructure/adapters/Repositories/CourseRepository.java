package com.codesa.schoolRegistry.infrastructure.adapters.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesa.schoolRegistry.core.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
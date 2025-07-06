package com.codesa.schoolRegistry.application.Mappers;


import com.codesa.schoolRegistry.application.UserCase.Enrollment.DTOs.EnrollmentDto;
import com.codesa.schoolRegistry.core.domain.Enrollment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {
    
    public EnrollmentDto toDto(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }
        
        return EnrollmentDto.builder()
            .id(enrollment.getId())
            .studentId(enrollment.getStudent() != null ? enrollment.getStudent().getId() : null)
            .studentName(enrollment.getStudent() != null ? enrollment.getStudent().getFirstName() : null)
            .courseId(enrollment.getCourse() != null ? enrollment.getCourse().getId() : null)
            .courseName(enrollment.getCourse() != null ? enrollment.getCourse().getName() : null)
            .enrollmentDate(enrollment.getEnrollmentDate())
            .build();
    }
    
    public List<EnrollmentDto> toDtoList(List<Enrollment> enrollments) {
        if (enrollments == null) {
            return null;
        }
        
        return enrollments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
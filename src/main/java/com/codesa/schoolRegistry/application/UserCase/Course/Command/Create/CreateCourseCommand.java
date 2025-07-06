package com.codesa.schoolRegistry.application.UserCase.Course.Command.Create;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseCommand {

    @NotBlank
    private String name;

    private String description;

    @Min(1)
    private int credits;

    private Long teacherId;
}
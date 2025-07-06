package com.codesa.schoolRegistry.application.UserCase.Course.Command.Update;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UpdateCourseCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Min(1)
    private int credits;

    private Long teacherId;
}

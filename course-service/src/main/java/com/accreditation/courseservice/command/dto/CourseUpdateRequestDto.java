package com.accreditation.courseservice.command.dto;

import com.accreditation.courseservice.util.ExceptionConstant;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateRequestDto {

    @NotNull
    @Positive(message = ExceptionConstant.COURSE_ID_SHOULD_BE_NUMERIC_GREATER_THAN_ZERO)
    private int courseId;

    @NotBlank(message = ExceptionConstant.COURSE_NAME_IS_MANDATORY)
    @Size(min = 20, message = ExceptionConstant.COURSE_NAME_SHOULD_BE_MINIMUM_20)
    private String courseName;

    @NotBlank(message = ExceptionConstant.COURSE_DESCRIPTION_IS_MANDATORY)
    @Size(min = 100, message = ExceptionConstant.COURSE_DESCRIPTION_SHOULD_BE_MINIMUM_100)
    private String courseDescription;

    @NotNull
    @Positive(message = ExceptionConstant.COURSE_DURATION_SHOULD_BE_NUMERIC_GREATER_THAN_ZERO)
    private int courseDuration;

    @NotBlank(message = ExceptionConstant.COURSE_TECHNOLOGY_IS_MANDATORY)
    private String courseTechnology;

    @NotBlank(message = ExceptionConstant.COURSE_LAUNCH_URL_IS_MANDATORY)
    private String courseLaunchURL;
}

package com.accreditation.courseservice.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateRequestDto {

    private String courseName;

    private String courseDescription;

    private int courseDuration;

    private String courseTechnology;

    private String courseLaunchURL;

}

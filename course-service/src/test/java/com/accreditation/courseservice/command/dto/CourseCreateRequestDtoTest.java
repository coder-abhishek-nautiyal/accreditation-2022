package com.accreditation.courseservice.command.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseCreateRequestDtoTest {


    @Test
    void test_courseCreateRequestDto() {

        CourseCreateRequestDto courseCreateRequestDto=new CourseCreateRequestDto();
        courseCreateRequestDto.setCourseDescription("desc");
        courseCreateRequestDto.setCourseName("name");
        courseCreateRequestDto.setCourseTechnology("tech");
        courseCreateRequestDto.setCourseDuration(1);
        courseCreateRequestDto.setCourseLaunchURL("test");


        assertEquals("name",courseCreateRequestDto.getCourseName());
        assertEquals("desc",courseCreateRequestDto.getCourseDescription());
        assertEquals(1,courseCreateRequestDto.getCourseDuration());
        assertEquals("test",courseCreateRequestDto.getCourseLaunchURL());
        assertEquals("tech",courseCreateRequestDto.getCourseTechnology());
        assertEquals("CourseCreateRequestDto(courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseCreateRequestDto.toString());


        CourseCreateRequestDto courseCreateRequestDto1= CourseCreateRequestDto.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        assertEquals("name",courseCreateRequestDto1.getCourseName());
        assertEquals("desc",courseCreateRequestDto1.getCourseDescription());
        assertEquals(1,courseCreateRequestDto1.getCourseDuration());
        assertEquals("test",courseCreateRequestDto1.getCourseLaunchURL());
        assertEquals("tech",courseCreateRequestDto1.getCourseTechnology());
        assertEquals("CourseCreateRequestDto(courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseCreateRequestDto1.toString());

    }

}
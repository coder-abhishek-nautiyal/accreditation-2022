package com.accreditation.courseservice.command.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseUpdateRequestDtoTest {

    @Test
    void test_courseUpdateRequestDto() {

        CourseUpdateRequestDto courseUpdateRequestDto=new CourseUpdateRequestDto();
        courseUpdateRequestDto.setCourseId(1);
        courseUpdateRequestDto.setCourseDescription("desc");
        courseUpdateRequestDto.setCourseName("name");
        courseUpdateRequestDto.setCourseTechnology("tech");
        courseUpdateRequestDto.setCourseDuration(1);
        courseUpdateRequestDto.setCourseLaunchURL("test");


        assertEquals(1,courseUpdateRequestDto.getCourseId());
        assertEquals("name",courseUpdateRequestDto.getCourseName());
        assertEquals("desc",courseUpdateRequestDto.getCourseDescription());
        assertEquals(1,courseUpdateRequestDto.getCourseDuration());
        assertEquals("test",courseUpdateRequestDto.getCourseLaunchURL());
        assertEquals("tech",courseUpdateRequestDto.getCourseTechnology());
        assertEquals("CourseUpdateRequestDto(courseId=1, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseUpdateRequestDto.toString());


        CourseUpdateRequestDto courseUpdateRequestDto1= CourseUpdateRequestDto.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        assertEquals(2,courseUpdateRequestDto1.getCourseId());
        assertEquals("name",courseUpdateRequestDto1.getCourseName());
        assertEquals("desc",courseUpdateRequestDto1.getCourseDescription());
        assertEquals(1,courseUpdateRequestDto1.getCourseDuration());
        assertEquals("test",courseUpdateRequestDto1.getCourseLaunchURL());
        assertEquals("tech",courseUpdateRequestDto1.getCourseTechnology());
        assertEquals("CourseUpdateRequestDto(courseId=2, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseUpdateRequestDto1.toString());

    }


}
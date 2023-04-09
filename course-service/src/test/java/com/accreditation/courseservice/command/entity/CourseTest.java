package com.accreditation.courseservice.command.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {


    @Test
    void test_course() {

        Course course=new Course();
        course.setCourseId(1);
        course.setCourseDescription("desc");
        course.setCourseName("name");
        course.setCourseTechnology("tech");
        course.setCourseDuration(1);
        course.setCourseLaunchURL("test");


        assertEquals(1,course.getCourseId());
        assertEquals("name",course.getCourseName());
        assertEquals("desc",course.getCourseDescription());
        assertEquals(1,course.getCourseDuration());
        assertEquals("test",course.getCourseLaunchURL());
        assertEquals("tech",course.getCourseTechnology());
        assertEquals("Course(courseId=1, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",course.toString());


        Course course1= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        assertEquals(2,course1.getCourseId());
        assertEquals("name",course1.getCourseName());
        assertEquals("desc",course1.getCourseDescription());
        assertEquals(1,course1.getCourseDuration());
        assertEquals("test",course1.getCourseLaunchURL());
        assertEquals("tech",course1.getCourseTechnology());
        assertEquals("Course(courseId=2, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",course1.toString());

    }


}
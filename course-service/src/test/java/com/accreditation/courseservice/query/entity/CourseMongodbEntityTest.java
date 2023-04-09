package com.accreditation.courseservice.query.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseMongodbEntityTest {



    @Test
    void test_courseMongodbEntity() {

        CourseMongodbEntity courseMongodbEntity=new CourseMongodbEntity();
        courseMongodbEntity.setId(1);
        courseMongodbEntity.setCourseDescription("desc");
        courseMongodbEntity.setCourseName("name");
        courseMongodbEntity.setCourseTechnology("tech");
        courseMongodbEntity.setCourseDuration(1);
        courseMongodbEntity.setCourseLaunchURL("test");


        assertEquals(1,courseMongodbEntity.getId());
        assertEquals("name",courseMongodbEntity.getCourseName());
        assertEquals("desc",courseMongodbEntity.getCourseDescription());
        assertEquals(1,courseMongodbEntity.getCourseDuration());
        assertEquals("test",courseMongodbEntity.getCourseLaunchURL());
        assertEquals("tech",courseMongodbEntity.getCourseTechnology());
        assertEquals("CourseMongodbEntity(id=1, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseMongodbEntity.toString());


        CourseMongodbEntity courseMongodbEntity1= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        assertEquals(2,courseMongodbEntity1.getId());
        assertEquals("name",courseMongodbEntity1.getCourseName());
        assertEquals("desc",courseMongodbEntity1.getCourseDescription());
        assertEquals(1,courseMongodbEntity1.getCourseDuration());
        assertEquals("test",courseMongodbEntity1.getCourseLaunchURL());
        assertEquals("tech",courseMongodbEntity1.getCourseTechnology());
        assertEquals("CourseMongodbEntity(id=2, courseName=name, courseDescription=desc, courseDuration=1, courseTechnology=tech, courseLaunchURL=test)",courseMongodbEntity1.toString());

    }



}
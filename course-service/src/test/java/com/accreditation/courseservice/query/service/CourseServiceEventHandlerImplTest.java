package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.query.entity.CourseMongodbEntity;
import com.accreditation.courseservice.query.repository.CourseMongodbRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CourseServiceEventHandlerImplTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @InjectMocks
    private CourseServiceEventHandlerImpl courseServiceEventHandler;

    @Mock
    private CourseMongodbRepository courseMongodbRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_addCourseListener() throws JsonProcessingException {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        String value = OBJECT_MAPPER.writeValueAsString(course);

        courseServiceEventHandler.addCourseListener(value);
        assertNotNull(value);
    }

    @Test
    void test_deleteByCourseNameListener() throws JsonProcessingException {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        String value = OBJECT_MAPPER.writeValueAsString(course);

        courseServiceEventHandler.deleteByCourseNameListener(value);
        assertNotNull(value);
    }

    @Test
    void test_deleteByCourseIdListener() throws JsonProcessingException {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        String value = OBJECT_MAPPER.writeValueAsString(course);

        courseServiceEventHandler.deleteByCourseIdListener(value);
        assertNotNull(value);
    }

    @Test
    void test_updateCourseListener() throws JsonProcessingException {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        String value = OBJECT_MAPPER.writeValueAsString(course);
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        when(courseMongodbRepository.findById(any())).thenReturn(Optional.ofNullable(courseMongodbEntity));
        courseServiceEventHandler.updateCourseListener(value);
        assertNotNull(value);
    }

    @Test
    void test_updateCourseListener_DataNotPresent() throws JsonProcessingException {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        String value = OBJECT_MAPPER.writeValueAsString(course);

        courseServiceEventHandler.updateCourseListener(value);
        assertNotNull(value);
    }


}
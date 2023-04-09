package com.accreditation.courseservice.query.controller;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.query.service.CourseQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CourseQueryControllerTest {



    @InjectMocks
    private CourseQueryController courseQueryController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourseQueryService courseService;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseQueryController).build();

    }

    @Test
    void test_getAllCourses() throws Exception {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseService.getAllCourses()).thenReturn(courseList);
        ResponseEntity<?> response=courseQueryController.getAllCourses();
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v/1.0/lms/courses/getall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void test_getAllCourses_Null() {

        when(courseService.getAllCourses()).thenReturn(null);
        ResponseEntity<?> response=courseQueryController.getAllCourses();
        assertEquals(200,response.getStatusCode().value());

    }



    @Test
    void test_getAllCourses_Empty() {

        ResponseEntity<?> response=courseQueryController.getAllCourses();
        assertEquals(200,response.getStatusCode().value());

    }

    @Test
    void test_getCourseByTechnology() throws Exception {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseService.getCourseByTechnology(any())).thenReturn(courseList);
        ResponseEntity<?> response=courseQueryController.getCourseByTechnology("tech");
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v/1.0/lms/courses/info/{courseTechnology}","tech").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void test_getCourseByTechnology_NullResult() {

        when(courseService.getCourseByTechnology(any())).thenReturn(null);
        ResponseEntity<?> response=courseQueryController.getCourseByTechnology("tech");
        assertEquals(404,response.getStatusCode().value());

    }


    @Test
    void test_getCourseByTechnology_NotFound() {

        ResponseEntity<?> response=courseQueryController.getCourseByTechnology("tech");
        assertEquals(404,response.getStatusCode().value());

    }

    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified() throws Exception {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseService.getCourseByTechnologyBetweenDurationSpecified(any(),anyInt(),anyInt())).thenReturn(courseList);
        ResponseEntity<?> response=courseQueryController.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v/1.0/lms/courses/get/{courseTechnology}/{courseDurationFrom}/{courseDurationTo}","tech",1,2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_NullResult() {

        when(courseService.getCourseByTechnologyBetweenDurationSpecified(any(),anyInt(),anyInt())).thenReturn(null);
        ResponseEntity<?> response=courseQueryController.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertEquals(200,response.getStatusCode().value());

    }


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_NotFound() {

        ResponseEntity<?> response=courseQueryController.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertEquals(200,response.getStatusCode().value());

    }


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_courseDurationToIsLess() {

        ResponseEntity<?> response=courseQueryController.getCourseByTechnologyBetweenDurationSpecified("tech",2,1);
        assertEquals(406,response.getStatusCode().value());

    }




}
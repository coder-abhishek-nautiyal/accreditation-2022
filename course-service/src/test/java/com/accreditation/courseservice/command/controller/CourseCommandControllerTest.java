package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.CourseCreateRequestDto;
import com.accreditation.courseservice.command.dto.CourseUpdateRequestDto;
import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.service.CourseCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CourseCommandControllerTest {


    @InjectMocks
    private CourseCommandController courseCommandController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourseCommandService courseService;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseCommandController).build();

    }

    @Test
    void test_addCourse() throws Exception {

        CourseCreateRequestDto courseCreateRequestDto=CourseCreateRequestDto.builder().courseName("Java Course by Abhishek Nautiyal").courseDescription("Java Course by Abhishek Nautiyal with advance concepts of Spring , Spring Boot and Hibernate Do connect for queries").courseDuration(1).courseLaunchURL("test").courseTechnology("tech").build();
        String courseCreateRequestDtoString = new ObjectMapper().writeValueAsString(courseCreateRequestDto);
        when(courseService.addCourse(any())).thenReturn(new Course());
        ResponseEntity<?> response=courseCommandController.addCourse(courseCreateRequestDto);
        assertEquals(201,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/courses/add").contentType(MediaType.APPLICATION_JSON).content(courseCreateRequestDtoString))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }



    @Test
    void test_addCourse_courseConflict() throws Exception {

        CourseCreateRequestDto courseCreateRequestDto=CourseCreateRequestDto.builder().courseName("Java Course by Abhishek Nautiyal").courseDescription("Java Course by Abhishek Nautiyal with advance concepts of Spring , Spring Boot and Hibernate Do connect for queries").courseDuration(1).courseLaunchURL("test").courseTechnology("tech").build();
        String courseCreateRequestDtoString = new ObjectMapper().writeValueAsString(courseCreateRequestDto);

        ResponseEntity<?> response=courseCommandController.addCourse(courseCreateRequestDto);
        assertEquals(409,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/courses/add").contentType(MediaType.APPLICATION_JSON).content(courseCreateRequestDtoString))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void test_deleteCourseByName() throws Exception {

        when(courseService.deleteCourseByName(any())).thenReturn(true);
        ResponseEntity<?> response=courseCommandController.deleteCourseByName("Java Course by Abhishek Nautiyal");
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v/1.0/lms/courses/delete/{courseName}","name").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }



    @Test
    void test_deleteCourseByName_courseNotFound() throws Exception {

        ResponseEntity<?> response=courseCommandController.deleteCourseByName("Java Course by Abhishek Nautiyal");
        assertEquals(404,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v/1.0/lms/courses/delete/{courseName}","name").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void test_deleteCourseById() throws Exception {

        when(courseService.deleteCourseById(1)).thenReturn(true);
        ResponseEntity<?> response=courseCommandController.deleteCourseById(1);
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v/1.0/lms/courses/deleteByCourseId/{courseId}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }



    @Test
    void test_deleteCourseById_courseNotFound() throws Exception {

        ResponseEntity<?> response=courseCommandController.deleteCourseById(1);
        assertEquals(404,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v/1.0/lms/courses/deleteByCourseId/{courseId}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }



    @Test
    void test_updateCourse() throws Exception {
        CourseUpdateRequestDto courseUpdateRequestDto= CourseUpdateRequestDto.builder().courseId(1).courseName("Java Course by Abhishek Nautiyal").courseDescription("Java Course by Abhishek Nautiyal with advance concepts of Spring , Spring Boot and Hibernate Do connect for queries").courseDuration(1).courseLaunchURL("test").courseTechnology("tech").build();
        String courseUpdateRequestDtoString = new ObjectMapper().writeValueAsString(courseUpdateRequestDto);

        when(courseService.updateCourse(any())).thenReturn(true);
        ResponseEntity<?> response=courseCommandController.updateCourse(courseUpdateRequestDto);
        assertEquals(200,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v/1.0/lms/courses/update",1).contentType(MediaType.APPLICATION_JSON).content(courseUpdateRequestDtoString))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }



    @Test
    void test_updateCourse_Failure() throws Exception {

        CourseUpdateRequestDto courseUpdateRequestDto= CourseUpdateRequestDto.builder().courseId(1).courseName("Java Course by Abhishek Nautiyal").courseDescription("Java Course by Abhishek Nautiyal with advance concepts of Spring , Spring Boot and Hibernate Do connect for queries").courseDuration(1).courseLaunchURL("test").courseTechnology("tech").build();
        String courseUpdateRequestDtoString = new ObjectMapper().writeValueAsString(courseUpdateRequestDto);

        when(courseService.updateCourse(any())).thenReturn(false);
        ResponseEntity<?> response=courseCommandController.updateCourse(courseUpdateRequestDto);
        assertEquals(500,response.getStatusCode().value());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v/1.0/lms/courses/update",1).contentType(MediaType.APPLICATION_JSON).content(courseUpdateRequestDtoString))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());



    }



}
package com.accreditation.courseservice.command.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CourseCommandServiceImplTest {


    @InjectMocks
    private CourseCommandServiceImpl courseCommandService;

    @Mock
    private CourseCommandRepository courseCommandRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_addCourse()  {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();

        Course response=courseCommandService.addCourse(course);
        assertEquals("name",response.getCourseName());

    }



    @Test
    void test_addCourse_courseConflict() {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        when(courseCommandRepository.findByCourseName(any())).thenReturn(Optional.ofNullable(course));
        assertThrows(ResponseStatusException.class, () -> {
            courseCommandService.addCourse(course);
        });

    }

    @Test
    void test_deleteCourseByName()  {

        Course course= Course.builder().courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        when(courseCommandRepository.findByCourseName(any())).thenReturn(Optional.ofNullable(course));
        boolean response=courseCommandService.deleteCourseByName("name");
        assertTrue(response);

    }



    @Test
    void test_deleteCourseByName_courseNotExist() {

        assertThrows(ResponseStatusException.class, () -> {
            courseCommandService.deleteCourseByName("name");
        });

    }


    @Test
    void test_deleteCourseById()  {

        Course course= Course.builder().courseId(1).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        when(courseCommandRepository.findById(any())).thenReturn(Optional.ofNullable(course));
        boolean response=courseCommandService.deleteCourseById(1);
        assertTrue(response);

    }



    @Test
    void test_deleteCourseById_courseNotExist() {

        assertThrows(ResponseStatusException.class, () -> {
            courseCommandService.deleteCourseById(1);
        });

    }



    @Test
    void test_updateCourse()  {

        Course course= Course.builder().courseId(1).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        when(courseCommandRepository.findById(any())).thenReturn(Optional.ofNullable(course));
        boolean response=courseCommandService.updateCourse(course);
        assertTrue(response);

    }



    @Test
    void test_updateCourse_courseNameExist() {

        Course course= Course.builder().courseId(1).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        Course course1= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();

        when(courseCommandRepository.findByCourseName(any())).thenReturn(Optional.ofNullable(course));
        assertThrows(ResponseStatusException.class, () -> {
            courseCommandService.updateCourse(course1);
        });

    }


    @Test
    void test_updateCourse_courseIdNotExist() {

        Course course= Course.builder().courseId(1).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();

        when(courseCommandRepository.findByCourseName(any())).thenReturn(Optional.ofNullable(course));
        boolean response=courseCommandService.updateCourse(course);
        assertFalse(response);

    }



}
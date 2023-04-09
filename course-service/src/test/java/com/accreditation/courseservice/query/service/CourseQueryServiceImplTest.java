package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CourseQueryServiceImplTest {

    @InjectMocks
    private CourseQueryServiceImpl courseQueryService;

    @Mock
    private CourseCommandRepository courseCommandRepository;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_getAllCourses()  {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseCommandRepository.findAll()).thenReturn(courseList);
        List<Course> response=courseQueryService.getAllCourses();
        assertEquals(1,response.size());

    }


    @Test
    void test_getAllCourses_Empty() {

        List<Course> response=courseQueryService.getAllCourses();
        assertNull(response);

    }

    @Test
    void test_getCourseByTechnology()  {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(courseList);
        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertEquals(1,response.size());

    }

    @Test
    void test_getCourseByTechnology_Null()  {

        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(null);
        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertNull(response);

    }


    @Test
    void test_getCourseByTechnology_Empty() {

        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertNull(response);

    }


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified()  {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(courseList);
        when(courseCommandRepository.findByCourseTechnologyBetweenCourseDuration(any(),anyInt(),anyInt())).thenReturn(courseList);
        List<Course> response=courseQueryService.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertEquals(1,response.size());

    }

    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_courseTechNotExist()  {

        assertThrows(ResponseStatusException.class, () -> {
            courseQueryService.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        });

    }


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_Null()  {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(courseList);
        when(courseCommandRepository.findByCourseTechnologyBetweenCourseDuration(any(),anyInt(),anyInt())).thenReturn(null);
        List<Course> response=courseQueryService.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertNull(response);

    }


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_Empty() {

        List<Course> courseList=new ArrayList<>();
        Course course= Course.builder().courseId(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseList.add(course);
        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(courseList);
        List<Course> response=courseQueryService.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertNull(response);

    }




}
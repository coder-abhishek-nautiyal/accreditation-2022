package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import com.accreditation.courseservice.query.entity.CourseMongodbEntity;
import com.accreditation.courseservice.query.repository.CourseMongodbRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
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

    @Mock
    private CourseMongodbRepository courseMongodbRepository;


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
    void test_getAllCourses_isKafkaEnabled()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        List<CourseMongodbEntity> courseMongodbEntities=new ArrayList<>();
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseMongodbEntities.add(courseMongodbEntity);
        when(courseMongodbRepository.findAll()).thenReturn(courseMongodbEntities);
        List<Course> response=courseQueryService.getAllCourses();
        assertEquals(1,response.size());

    }

    @Test
    void test_getAllCourses_isKafkaEnabled_Empty()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        List<Course> response=courseQueryService.getAllCourses();
        assertNull(response);

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
    void test_getCourseByTechnology_isKafkaEnabled()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        List<CourseMongodbEntity> courseMongodbEntities=new ArrayList<>();
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseMongodbEntities.add(courseMongodbEntity);
        when(courseMongodbRepository.findByCourseTechnology(any())).thenReturn(courseMongodbEntities);
        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertEquals(1,response.size());

    }

    @Test
    void test_getCourseByTechnology_isKafkaEnabled_Null()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        when(courseMongodbRepository.findByCourseTechnology(any())).thenReturn(null);
        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertNull(response);

    }


    @Test
    void test_getCourseByTechnology_Null()  {

        when(courseCommandRepository.findByCourseTechnology(any())).thenReturn(null);
        List<Course> response=courseQueryService.getCourseByTechnology("tech");
        assertNull(response);

    }


    @Test
    void test_getCourseByTechnology_isKafkaEnabled_Empty() {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
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
    void test_getCourseByTechnologyBetweenDurationSpecified_isKafkaEnabled()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        List<CourseMongodbEntity> courseMongodbEntities=new ArrayList<>();
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseMongodbEntities.add(courseMongodbEntity);
        when(courseMongodbRepository.findByCourseTechnology(any())).thenReturn(courseMongodbEntities);
        when(courseMongodbRepository.findByCourseTechnologyBetweenCourseDuration(any(),anyInt(),anyInt())).thenReturn(courseMongodbEntities);
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
    void test_getCourseByTechnologyBetweenDurationSpecified_isKafkaEnabled_courseTechNotExist()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
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
    void test_getCourseByTechnologyBetweenDurationSpecified_isKafkaEnabled_Null()  {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);
        List<CourseMongodbEntity> courseMongodbEntities=new ArrayList<>();
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseMongodbEntities.add(courseMongodbEntity);
        when(courseMongodbRepository.findByCourseTechnology(any())).thenReturn(courseMongodbEntities);
        when(courseMongodbRepository.findByCourseTechnologyBetweenCourseDuration(any(),anyInt(),anyInt())).thenReturn(null);
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


    @Test
    void test_getCourseByTechnologyBetweenDurationSpecified_isKafkaEnabled_Empty() {
        ReflectionTestUtils.setField(courseQueryService, "isKafkaEnabled", true);

        List<CourseMongodbEntity> courseMongodbEntities=new ArrayList<>();
        CourseMongodbEntity courseMongodbEntity= CourseMongodbEntity.builder().id(2).courseName("name").courseDescription("desc").courseLaunchURL("test").courseDuration(1).courseTechnology("tech").build();
        courseMongodbEntities.add(courseMongodbEntity);
        when(courseMongodbRepository.findByCourseTechnology(any())).thenReturn(courseMongodbEntities);
        List<Course> response=courseQueryService.getCourseByTechnologyBetweenDurationSpecified("tech",1,2);
        assertNull(response);

    }





}
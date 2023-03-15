package com.accreditation.courseservice.query.controller;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.util.ExceptionConstant;
import com.accreditation.courseservice.query.service.CourseQueryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v/1.0/lms/courses")
public class CourseQueryController {

    @Autowired
    private CourseQueryService courseQueryService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCourses() {

        List<Course> courseList = courseQueryService.getAllCourses();

        if (courseList != null && !courseList.isEmpty()) {
            return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Course List is empty ", HttpStatus.OK);

    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/info/{courseTechnology}")
    public ResponseEntity<?> getCourseByTechnology(@PathVariable("courseTechnology") String courseTechnology) {

        List<Course> courseList = courseQueryService.getCourseByTechnology(courseTechnology);

        if (courseList != null && !courseList.isEmpty()) {
            return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
        }
        return new ResponseEntity<String>(ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/get/{courseTechnology}/{courseDurationFrom}/{courseDurationTo}")
    public ResponseEntity<?> getCourseByTechnologyBetweenDurationSpecified(@PathVariable("courseTechnology") String courseTechnology,
                                                                           @PathVariable("courseDurationFrom") int courseDurationFrom, @PathVariable("courseDurationTo") int courseDurationTo) {

        if (courseDurationFrom <= courseDurationTo) {

            List<Course> courseList = courseQueryService.getCourseByTechnologyBetweenDurationSpecified(courseTechnology, courseDurationFrom, courseDurationTo);

            if (courseList != null && !courseList.isEmpty()) {
                return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
            }

            return new ResponseEntity<String>("Course List is empty ", HttpStatus.OK);

        }

        return new ResponseEntity<String>(ExceptionConstant.COURSE_DURATION_FROM_SHOULD_BE_LESS_THAN_TO, HttpStatus.NOT_ACCEPTABLE);
    }

}

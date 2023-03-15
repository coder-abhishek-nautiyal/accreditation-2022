package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.CourseCreateRequestDto;
import com.accreditation.courseservice.command.dto.CourseUpdateRequestDto;
import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.service.CourseCommandService;
import com.accreditation.courseservice.exception.CourseServiceException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v/1.0/lms/courses")
public class CourseCommandController {

    @Autowired
    private CourseCommandService courseService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseCreateRequestDto courseRequestDto) throws CourseServiceException {

        Course course = Course.builder()
                .courseName(courseRequestDto.getCourseName())
                .courseDescription(courseRequestDto.getCourseDescription())
                .courseDuration(courseRequestDto.getCourseDuration())
                .courseTechnology(courseRequestDto.getCourseTechnology())
                .courseLaunchURL(courseRequestDto.getCourseLaunchURL())
                .build();

        if (courseService.addCourse(course) != null) {
            return new ResponseEntity<CourseCreateRequestDto>(courseRequestDto, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping("/delete/{courseName}")
    public ResponseEntity<?> deleteCourseByName(@PathVariable("courseName") String courseName) {
        if (courseService.deleteCourseByName(courseName)) {
            return new ResponseEntity<String>("Course deleted from table", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Course is not deleted from table", HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping("/deleteByCourseId/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("courseId") int courseId) {
        if (courseService.deleteCourseById(courseId)) {
            return new ResponseEntity<String>("Course deleted from table", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Course is not deleted from table", HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseUpdateRequestDto courseUpdateRequestDto) {

        //validate Course Parameter before saving to DB

        Course course = Course.builder()
                .courseId(courseUpdateRequestDto.getCourseId())
                .courseName(courseUpdateRequestDto.getCourseName())
                .courseDescription(courseUpdateRequestDto.getCourseDescription())
                .courseDuration(courseUpdateRequestDto.getCourseDuration())
                .courseTechnology(courseUpdateRequestDto.getCourseTechnology())
                .courseLaunchURL(courseUpdateRequestDto.getCourseLaunchURL())
                .build();

        if (courseService.updateCourse(course)) {
            return new ResponseEntity<String>("Course Record is updated in the table", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Course Record is not updated in the table", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}

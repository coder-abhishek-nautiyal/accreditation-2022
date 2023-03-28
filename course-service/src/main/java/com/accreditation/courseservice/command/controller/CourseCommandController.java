package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.CourseCreateRequestDto;
import com.accreditation.courseservice.command.dto.CourseUpdateRequestDto;
import com.accreditation.courseservice.command.dto.StringResponse;
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
@CrossOrigin("*")
public class CourseCommandController {

    @Autowired
    private CourseCommandService courseService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/add")
    public ResponseEntity<CourseCreateRequestDto> addCourse(@Valid @RequestBody CourseCreateRequestDto courseRequestDto) throws CourseServiceException {

        Course course = Course.builder()
                .courseName(courseRequestDto.getCourseName().trim())
                .courseDescription(courseRequestDto.getCourseDescription().trim())
                .courseDuration(courseRequestDto.getCourseDuration())
                .courseTechnology(courseRequestDto.getCourseTechnology().trim())
                .courseLaunchURL(courseRequestDto.getCourseLaunchURL().trim())
                .build();

        if (courseService.addCourse(course) != null) {
            return new ResponseEntity<>(courseRequestDto, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping(value = "/delete/{courseName}")
    public ResponseEntity<StringResponse> deleteCourseByName(@PathVariable("courseName") String courseName) {
        if (courseService.deleteCourseByName(courseName)) {
            return new ResponseEntity<>(new StringResponse("Course deleted from table"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StringResponse("Course is not deleted from table"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @DeleteMapping("/deleteByCourseId/{courseId}")
    public ResponseEntity<StringResponse> deleteCourseById(@PathVariable("courseId") int courseId) {
        if (courseService.deleteCourseById(courseId)) {
            return new ResponseEntity<>(new StringResponse("Course deleted from table"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new StringResponse("Course is not deleted from table"), HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PutMapping("/update")
    public ResponseEntity<StringResponse> updateCourse(@Valid @RequestBody CourseUpdateRequestDto courseUpdateRequestDto) {

        //validate Course Parameter before saving to DB

        Course course = Course.builder()
                .courseId(courseUpdateRequestDto.getCourseId())
                .courseName(courseUpdateRequestDto.getCourseName().trim())
                .courseDescription(courseUpdateRequestDto.getCourseDescription().trim())
                .courseDuration(courseUpdateRequestDto.getCourseDuration())
                .courseTechnology(courseUpdateRequestDto.getCourseTechnology().trim())
                .courseLaunchURL(courseUpdateRequestDto.getCourseLaunchURL().trim())
                .build();

        if (courseService.updateCourse(course)) {
            return new ResponseEntity<>(new StringResponse("Course Record is updated in the table"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new StringResponse("Course Record is not updated in the table"), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}

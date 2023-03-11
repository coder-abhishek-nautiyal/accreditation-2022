package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import com.accreditation.courseservice.util.ExceptionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    @Autowired
    private CourseCommandRepository courseCommandRepository;

    @Override
    public List<Course> getAllCourses() {

        List<Course> courseList = courseCommandRepository.findAll();
        if (courseList != null && !courseList.isEmpty()) {
            return courseList;
        }

        return null;
    }

    @Override
    public  List<Course> getCourseByTechnology(String courseTechnology) {

        /*We can get multiple courses based on Course Technology*/
        List<Course> courseList = courseCommandRepository.findByCourseTechnology(courseTechnology);

        if (courseList != null && !courseList.isEmpty()) {
            return courseList;
        }

        return null;
    }

    @Override
    public List<Course> getCourseByTechnologyBetweenDurationSpecified(String courseTechnology, int courseDurationFrom, int courseDurationTo) {

        /*Below logic to confirm courseTechnology exist*/
        List<Course> existingCourseList =getCourseByTechnology(courseTechnology);

        if(existingCourseList==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST);
        }

        List<Course> courseList = courseCommandRepository.findByCourseTechnologyBetweenCourseDuration(courseTechnology,courseDurationFrom,courseDurationTo);
        if (courseList != null && !courseList.isEmpty()) {
            return courseList;
        }

        return null;
    }
}

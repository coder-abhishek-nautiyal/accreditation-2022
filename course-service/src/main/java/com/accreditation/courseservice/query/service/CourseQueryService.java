package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;

import java.util.List;

public interface CourseQueryService {

    public List<Course> getAllCourses();

    public List<Course> getCourseByTechnology(String courseTechnology);

    public List<Course> getCourseByTechnologyBetweenDurationSpecified(String courseTechnology,int courseDurationFrom,int courseDurationTo);

}

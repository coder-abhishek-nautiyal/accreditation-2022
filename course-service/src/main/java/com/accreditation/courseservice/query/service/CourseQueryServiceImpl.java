package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import com.accreditation.courseservice.query.entity.CourseMongodbEntity;
import com.accreditation.courseservice.query.repository.CourseMongodbRepository;
import com.accreditation.courseservice.util.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CourseQueryServiceImpl implements CourseQueryService {

    @Value("${kafka.enabled}")
    private boolean isKafkaEnabled;

    @Autowired
    private CourseCommandRepository courseCommandRepository;

    @Autowired
    private CourseMongodbRepository courseMongodbRepository;

    @Override
    public List<Course> getAllCourses() {

        log.info("Inside getAllCourses method of Service Impl" + " and isKafkaEnabled is " + isKafkaEnabled);

        if (isKafkaEnabled) {
            return getAllCourseFromMongoDb();
        } else {
            return getAllCourseFromMySqlDb();
        }


    }

    @Override
    public List<Course> getCourseByTechnology(String courseTechnology) {

        log.info("Inside getCourseByTechnology method of Service Impl with courseTechnology " + courseTechnology + " and isKafkaEnabled is " + isKafkaEnabled);
        if (isKafkaEnabled) {
            return getCourseByTechnologyFromMongoDb(courseTechnology);
        } else {
            return getCourseByTechnologyFromMySqlDb(courseTechnology);
        }

    }

    @Override
    public List<Course> getCourseByTechnologyBetweenDurationSpecified(String courseTechnology, int courseDurationFrom, int courseDurationTo) {

        log.info("Inside getCourseByTechnologyBetweenDurationSpecified method of Service Impl with courseTechnology " + courseTechnology + " and courseDurationFrom is " + courseDurationFrom + " and courseDurationTo is " + courseDurationTo + " and isKafkaEnabled is " + isKafkaEnabled);
        if (isKafkaEnabled) {
            return getCourseByTechnologyBetweenDurationSpecifiedFromMongoDb(courseTechnology, courseDurationFrom, courseDurationTo);
        } else {
            return getCourseByTechnologyBetweenDurationSpecifiedFromMySqlDb(courseTechnology, courseDurationFrom, courseDurationTo);
        }


    }

    private List<Course> getAllCourseFromMySqlDb() {

        List<Course> courseList = courseCommandRepository.findAll();
        if (!courseList.isEmpty()) {
            return courseList;
        }

        return null;
    }

    private List<Course> getAllCourseFromMongoDb() {

        List<CourseMongodbEntity> courseMongodbEntities = courseMongodbRepository.findAll();
        if (!courseMongodbEntities.isEmpty()) {
            return getCourseList(courseMongodbEntities);
        }

        return null;
    }

    private List<Course> getCourseList(List<CourseMongodbEntity> courseMongodbEntities) {
        List<Course> courseList = new ArrayList<>();
        for (CourseMongodbEntity courseMongodbEntity : courseMongodbEntities) {
            Course course = Course.builder().courseId(courseMongodbEntity.getId())
                    .courseTechnology(courseMongodbEntity.getCourseTechnology()).courseName(courseMongodbEntity.getCourseName())
                    .courseDuration(courseMongodbEntity.getCourseDuration()).courseDescription(courseMongodbEntity.getCourseDescription())
                    .courseLaunchURL(courseMongodbEntity.getCourseLaunchURL()).build();
            courseList.add(course);
        }

        return courseList;
    }


    private List<Course> getCourseByTechnologyFromMySqlDb(String courseTechnology) {
        /*We can get multiple courses based on Course Technology*/
        List<Course> courseList = courseCommandRepository.findByCourseTechnology(courseTechnology);

        if (courseList != null && !courseList.isEmpty()) {
            return courseList;
        }

        return null;

    }

    private List<Course> getCourseByTechnologyFromMongoDb(String courseTechnology) {
        /*We can get multiple courses based on Course Technology*/
        List<CourseMongodbEntity> courseMongodbEntities= courseMongodbRepository.findByCourseTechnology(courseTechnology);

        if (courseMongodbEntities != null && !courseMongodbEntities.isEmpty()) {
            return getCourseList(courseMongodbEntities);
        }

        return null;

    }

    private List<Course> getCourseByTechnologyBetweenDurationSpecifiedFromMySqlDb(String courseTechnology, int courseDurationFrom, int courseDurationTo) {
        /*Below logic to confirm courseTechnology exist*/
        List<Course> existingCourseList = getCourseByTechnology(courseTechnology);

        if (existingCourseList == null) {
            log.error("Inside getCourseByTechnologyBetweenDurationSpecified method of Service Impl with Exception " + ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST);
        }

        List<Course> courseList = courseCommandRepository.findByCourseTechnologyBetweenCourseDuration(courseTechnology, courseDurationFrom, courseDurationTo);
        if (courseList != null && !courseList.isEmpty()) {
            return courseList;
        }

        return null;

    }

    private List<Course> getCourseByTechnologyBetweenDurationSpecifiedFromMongoDb(String courseTechnology, int courseDurationFrom, int courseDurationTo) {
        /*Below logic to confirm courseTechnology exist*/
        List<Course> existingCourseList = getCourseByTechnology(courseTechnology);

        if (existingCourseList == null) {
            log.error("Inside getCourseByTechnologyBetweenDurationSpecified method of Service Impl with Exception " + ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_TECHNOLOGY_DOES_NOT_EXIST);
        }

        List<CourseMongodbEntity> courseMongodbEntities = courseMongodbRepository.findByCourseTechnologyBetweenCourseDuration(courseTechnology, courseDurationFrom, courseDurationTo);
        if (courseMongodbEntities != null && !courseMongodbEntities.isEmpty()) {
            return getCourseList(courseMongodbEntities);
        }

        return null;

    }


}

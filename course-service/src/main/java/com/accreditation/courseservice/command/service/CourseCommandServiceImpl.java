package com.accreditation.courseservice.command.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import com.accreditation.courseservice.util.ExceptionConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Slf4j
@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Value("${kafka.enabled}")
    private boolean isKafkaEnabled;

    @Autowired
    private CourseCommandRepository courseCommandRepository;

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Override
    public Course addCourse(Course course) {

        log.info("Inside addCourse method of Service Impl with course details " + course.toString());

        Optional<Course> optionalCourse = courseCommandRepository.findByCourseName(course.getCourseName());

        if (optionalCourse.isPresent()) {
            log.error("Inside addCourse method of Service Impl with Exception " + ExceptionConstant.COURSE_NAME_ALREADY_PRESENT);
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.COURSE_NAME_ALREADY_PRESENT);
        } else {
            courseCommandRepository.save(course);
            log.info("Inside addCourse method of Service Impl , course details successfully added in Database ");
            if (isKafkaEnabled) {
                this.raiseEvent(course,"course-service-add-event");
            }
            return course;
        }

    }

    @Override
    public boolean deleteCourseByName(String courseName) {

        log.info("Inside deleteCourseByName method of Service Impl with course name " + courseName);

        Optional<Course> optionalCourse = courseCommandRepository.findByCourseName(courseName);
        if (optionalCourse.isPresent()) {
            courseCommandRepository.deleteByCourseName(courseName);
            log.info("Inside deleteCourseByName method of Service Impl, course name successfully deleted from Database");
            if (isKafkaEnabled) {
                this.raiseEvent(optionalCourse.get(),"course-service-delete-courseName-event");
            }
            return true;

        } else {
            log.error("Inside deleteCourseByName method of Service Impl with Exception " + ExceptionConstant.COURSE_NAME_DOES_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_NAME_DOES_NOT_EXIST);
        }

    }

    @Override
    public boolean deleteCourseById(int courseId) {

        log.info("Inside deleteCourseById method of Service Impl with course id " + courseId);

        Optional<Course> optionalCourse = courseCommandRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            courseCommandRepository.deleteById(courseId);
            log.info("Inside deleteCourseById method of Service Imp, course id is successfully deleted from Database ");
            if (isKafkaEnabled) {
                this.raiseEvent(optionalCourse.get(),"course-service-delete-courseId-event");
            }
            return true;

        } else {
            log.error("Inside deleteCourseById method of Service Impl with Exception " + ExceptionConstant.COURSE_ID_DOES_NOT_EXIST);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_ID_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean updateCourse(Course course) {

        log.info("Inside updateCourse method of Service Impl with course details " + course);

        Optional<Course> optionalExistingCourse = courseCommandRepository.findByCourseName(course.getCourseName());

        /*Below logic is added to check while updating course same course name is not added as other course*/
        if (optionalExistingCourse.isPresent() && optionalExistingCourse.get().getCourseId() != course.getCourseId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.COURSE_NAME_ALREADY_PRESENT);
        }

        Optional<Course> optionalCourse = courseCommandRepository.findById(course.getCourseId());
        if (optionalCourse.isPresent()) {
            courseCommandRepository.save(course);
            log.info("Inside updateCourse method of Service Impl, course details successfully updated in Database");
            if (isKafkaEnabled) {
                this.raiseEvent(optionalCourse.get(),"course-service-update-event");
            }
            return true;
        }

        return false;
    }


    private void raiseEvent(Course course,String topic) {
        try {
            String value = OBJECT_MAPPER.writeValueAsString(course);
            this.kafkaTemplate.send(topic,course.getCourseId(), value);
        } catch (Exception e) {
            log.error("Error received while raiseEvent for Kafka Listener value with message " + e.getMessage());
        }
    }



}

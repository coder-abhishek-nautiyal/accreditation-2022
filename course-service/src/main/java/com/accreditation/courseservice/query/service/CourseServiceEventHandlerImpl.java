package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.query.entity.CourseMongodbEntity;
import com.accreditation.courseservice.query.repository.CourseMongodbRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@ConditionalOnProperty(
        value = "kafka.enabled",
        havingValue = "true",
        matchIfMissing = false)
public class CourseServiceEventHandlerImpl implements CourseServiceEventHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private CourseMongodbRepository courseMongodbRepository;

    @KafkaListener(topics = "course-service-add-event")
    public void addCourseListener(String courseStr) {
        try {
            Course course = OBJECT_MAPPER.readValue(courseStr, Course.class);
            this.addCourse(course);
        } catch (Exception e) {
            log.error("Error received while addCourseListener Kafka Listener value with message " + e.getMessage());
        }
    }

    @KafkaListener(topics = "course-service-delete-courseName-event")
    public void deleteByCourseNameListener(String courseStr) {
        try {
            Course course = OBJECT_MAPPER.readValue(courseStr, Course.class);
            this.deleteByCourseName(course);
        } catch (Exception e) {
            log.error("Error received while deleteByCourseNameListener Kafka Listener value with message " + e.getMessage());
        }
    }

    @KafkaListener(topics = "course-service-delete-courseId-event")
    public void deleteByCourseIdListener(String courseStr) {
        try {
            Course course = OBJECT_MAPPER.readValue(courseStr, Course.class);
            this.deleteByCourseId(course);
        } catch (Exception e) {
            log.error("Error received while deleteByCourseIdListener Kafka Listener value with message " + e.getMessage());
        }
    }

    @KafkaListener(topics = "course-service-update-event")
    public void updateCourseListener(String courseStr) {
        try {
            Course course = OBJECT_MAPPER.readValue(courseStr, Course.class);
            this.updateCourse(course);
        } catch (Exception e) {
            log.error("Error received while updateCourseListener Kafka Listener value with message " + e.getMessage());
        }
    }


    @Override
    public void addCourse(Course course) {
        log.info("Course Details to Add in Mongodb " + course.toString());
        CourseMongodbEntity courseMongodbEntity = CourseMongodbEntity.builder().id(course.getCourseId()).courseDescription(course.getCourseDescription())
                .courseDuration(course.getCourseDuration()).courseLaunchURL(course.getCourseLaunchURL())
                .courseTechnology(course.getCourseTechnology()).courseName(course.getCourseName()).build();
        CourseMongodbEntity courseMongodb = courseMongodbRepository.save(courseMongodbEntity);
        log.info("Course Details successfully added to Mongodb is " + courseMongodb);

    }

    @Override
    public void deleteByCourseName(Course course) {
        log.info("Course Details to deleteByCourseName in Mongodb " + course.toString());
        CourseMongodbEntity courseMongodbEntity = CourseMongodbEntity.builder().id(course.getCourseId()).courseDescription(course.getCourseDescription())
                .courseDuration(course.getCourseDuration()).courseLaunchURL(course.getCourseLaunchURL())
                .courseTechnology(course.getCourseTechnology()).courseName(course.getCourseName()).build();
        courseMongodbRepository.deleteByCourseName(courseMongodbEntity.getCourseName());
        log.info("Course Detail successfully deleted for deleteByCourseName");

    }

    @Override
    public void deleteByCourseId(Course course) {
        log.info("Course Details to deleteByCourseId in Mongodb " + course.toString());
        CourseMongodbEntity courseMongodbEntity = CourseMongodbEntity.builder().id(course.getCourseId()).courseDescription(course.getCourseDescription())
                .courseDuration(course.getCourseDuration()).courseLaunchURL(course.getCourseLaunchURL())
                .courseTechnology(course.getCourseTechnology()).courseName(course.getCourseName()).build();
        courseMongodbRepository.deleteById(courseMongodbEntity.getId());
        log.info("Course Detail successfully deleted for deleteByCourseId");

    }

    @Override
    public void updateCourse(Course course) {
        log.info("Course Details to updateCourse in Mongodb " + course.toString());

        Optional<CourseMongodbEntity> courseMongodbEntity = courseMongodbRepository.findById(course.getCourseId());
        CourseMongodbEntity courseMongodb = null;
        if (courseMongodbEntity.isPresent()) {
            courseMongodbEntity.get().setCourseName(course.getCourseName());
            courseMongodbEntity.get().setCourseDuration(course.getCourseDuration());
            courseMongodbEntity.get().setCourseTechnology(course.getCourseTechnology());
            courseMongodbEntity.get().setCourseDescription(course.getCourseDescription());
            courseMongodbEntity.get().setCourseLaunchURL(course.getCourseLaunchURL());
            courseMongodb = courseMongodbRepository.save(courseMongodbEntity.get());
        }


        log.info("Course Details successfully updated to Mongodb is " + courseMongodb);

    }


}

package com.accreditation.courseservice.command.service;

import com.accreditation.courseservice.command.entity.Course;
import com.accreditation.courseservice.command.repository.CourseCommandRepository;
import com.accreditation.courseservice.util.ExceptionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    @Autowired
    private CourseCommandRepository courseCommandRepository;

    @Override
    public Course addCourse(Course course) {

        Optional<Course> optionalCourse = courseCommandRepository.findByCourseName(course.getCourseName());

        if (optionalCourse.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstant.COURSE_NAME_ALREADY_PRESENT);
        } else {
            return courseCommandRepository.save(course);
        }

    }

    @Override
    public boolean deleteCourseByName(String courseName) {

        Optional<Course> optionalCourse = courseCommandRepository.findByCourseName(courseName);
        if (optionalCourse.isPresent()) {
            courseCommandRepository.deleteByCourseName(courseName);
            return true;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_NAME_DOES_NOT_EXIST);
        }

    }

    @Override
    public boolean deleteCourseById(int courseId) {

        Optional<Course> optionalCourse = courseCommandRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            courseCommandRepository.deleteById(courseId);
            return true;

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstant.COURSE_ID_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean updateCourse(Course course) {

        Optional<Course> optionalCourse = courseCommandRepository.findById(course.getCourseId());
        if (optionalCourse.isPresent()) {
            courseCommandRepository.save(course);
            return true;
        }

        return false;
    }


}

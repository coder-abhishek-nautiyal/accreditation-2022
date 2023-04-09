package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;

public interface CourseServiceEventHandler {
    void addCourse(Course course);

    void deleteByCourseId(Course course);

    void deleteByCourseName(Course course);

    void updateCourse(Course course);
}

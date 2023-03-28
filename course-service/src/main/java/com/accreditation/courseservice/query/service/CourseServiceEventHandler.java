package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;

public interface CourseServiceEventHandler {
    void addCourse(Course course);

}

package com.accreditation.courseservice.command.service;

import com.accreditation.courseservice.command.entity.Course;

public interface CourseCommandService {

    public Course addCourse(Course course);

    public boolean deleteCourseByName(String courseName);

    public boolean deleteCourseById(int courseId);

    public boolean updateCourse(Course course);

}

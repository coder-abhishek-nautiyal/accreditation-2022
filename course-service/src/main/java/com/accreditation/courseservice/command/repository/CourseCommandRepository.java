package com.accreditation.courseservice.command.repository;

import com.accreditation.courseservice.command.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CourseCommandRepository extends JpaRepository<Course, Integer> {


    @Modifying
    @Query(value = "delete from Course where course_name = :courseName") // Need to use class name in query
    public void deleteByCourseName(String courseName);

    public Optional<Course> findByCourseName(String courseName);

    Optional<Course> findByCourseTechnology(String courseTechnology);

    @Query(value = "select c from Course c where c.courseTechnology = ?1 and c.courseDuration between ?2 and ?3")
    List<Course> findByCourseTechnologyBetweenCourseDuration(String courseTechnology, int courseDurationFrom, int courseDurationTo);
}

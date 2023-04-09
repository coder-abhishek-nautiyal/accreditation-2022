package com.accreditation.courseservice.query.repository;

import com.accreditation.courseservice.query.entity.CourseMongodbEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseMongodbRepository extends MongoRepository<CourseMongodbEntity, Integer> {

    void deleteByCourseName(String courseName);

    List<CourseMongodbEntity> findByCourseTechnology(String courseTechnology);

    @Query(value="{'courseTechnology':?0,'courseDuration' :{ $gte : ?1, $lte :?2 }}")
    List<CourseMongodbEntity> findByCourseTechnologyBetweenCourseDuration(String courseTechnology, int courseDurationFrom, int courseDurationTo);
}

package com.accreditation.courseservice.query.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="course")
public class CourseMongodbEntity {

    @Id
    private int id;

    private String courseName;

    private String courseDescription;

    private int courseDuration;

    private String courseTechnology;

    private String courseLaunchURL;


}

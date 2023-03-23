package com.accreditation.courseservice.command.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // this is combo of getter , setter , toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    @Column(length = 1000)
    private String courseDescription;

    private int courseDuration;

    private String courseTechnology;

    private String courseLaunchURL;


}

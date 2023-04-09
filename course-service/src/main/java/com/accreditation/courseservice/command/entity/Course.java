package com.accreditation.courseservice.command.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
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

package com.accreditation.courseservice.query.service;

import com.accreditation.courseservice.command.entity.Course;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(
        value="kafka.enabled",
        havingValue = "true",
        matchIfMissing = false)
public class CourseServiceEventHandlerImpl implements CourseServiceEventHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

//    @Autowired
//    private PurchaseOrderRepository purchaseOrderRepository;

    @KafkaListener(topics = "user-service-event")
    public void consume(String userStr) {
        try {
            Course course = OBJECT_MAPPER.readValue(userStr, Course.class);
            this.addCourse(course);
        } catch (Exception e) {
            log.error("Error received while consuming Kafka Listener value with message "+e.getMessage());
        }
    }


    @Override
    public void addCourse(Course course) {
        log.info(course.toString());
    }
}

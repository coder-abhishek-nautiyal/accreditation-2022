package com.accreditation.courseservice.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceExceptionTest {

    @Test
    void test_courseServiceException() {

        CourseServiceException courseServiceException=new CourseServiceException("Exception Received");

        assertEquals("Exception Received", courseServiceException.getMessage());

    }


}
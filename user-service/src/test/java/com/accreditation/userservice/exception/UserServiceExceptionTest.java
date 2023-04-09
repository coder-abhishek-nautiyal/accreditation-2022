package com.accreditation.userservice.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceExceptionTest {


    @Test
    void test_userServiceException() {

        UserServiceException userServiceException=new UserServiceException("Exception Received");

        assertEquals("Exception Received", userServiceException.getMessage());

    }


}
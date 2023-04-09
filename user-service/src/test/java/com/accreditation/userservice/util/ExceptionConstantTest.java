package com.accreditation.userservice.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionConstantTest {

    @Test
    void test_exceptionConstant() {

        assertEquals("Email id is mandatory",ExceptionConstant.EMAIL_ID_IS_MANDATORY);

    }



}
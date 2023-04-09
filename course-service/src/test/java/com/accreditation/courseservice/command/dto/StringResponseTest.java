package com.accreditation.courseservice.command.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringResponseTest {


    @Test
    void test_stringResponse() {

        StringResponse stringResponse =new StringResponse();
        stringResponse.setResponse("success");

        assertEquals("success",stringResponse.getResponse());
        assertEquals("StringResponse(response=success)",stringResponse.toString());

        StringResponse stringResponse1= StringResponse.builder().response("success").build();

        assertEquals("success",stringResponse1.getResponse());
        assertEquals("StringResponse(response=success)",stringResponse1.toString());

    }


}
package com.accreditation.userservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationResponseTest {


    @Test
    void test_authenticationResponse() {

        AuthenticationResponse authenticationResponse =new AuthenticationResponse();
        authenticationResponse.setToken("token1");

        assertEquals("token1",authenticationResponse.getToken());
        assertEquals("AuthenticationResponse(token=token1)",authenticationResponse.toString());

        AuthenticationResponse authenticationResponse1= AuthenticationResponse.builder().token("token2").build();

        assertEquals("token2",authenticationResponse1.getToken());
        assertEquals("AuthenticationResponse(token=token2)",authenticationResponse1.toString());

    }


}
package com.accreditation.userservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {


    @Test
    void test_authenticationRequest() {

        AuthenticationRequest authenticationRequest=new AuthenticationRequest();
        authenticationRequest.setEmail("admin@admin.com");
        authenticationRequest.setPassword("password123");

        assertEquals("password123",authenticationRequest.getPassword());
        assertEquals("admin@admin.com",authenticationRequest.getEmail());
        assertEquals("AuthenticationRequest(email=admin@admin.com, password=password123)",authenticationRequest.toString());

        AuthenticationRequest authenticationRequest1= AuthenticationRequest.builder().email("test@test.com").password("password123").build();

        assertEquals("password123",authenticationRequest1.getPassword());
        assertEquals("test@test.com",authenticationRequest1.getEmail());
        assertEquals("AuthenticationRequest(email=test@test.com, password=password123)",authenticationRequest1.toString());

    }



}
package com.accreditation.userservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    void test_getUserTokenResponse() throws Exception {

        String response=authenticationController.getUserTokenResponse();
        assertEquals("Hello, User is authenticated !!",response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v/1.0/lms/company/userTokenTest").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void test_getAdminTokenResponse() throws Exception {

        String response=authenticationController.getAdminTokenResponse();
        assertEquals("Hello, Admin is authenticated !!",response);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v/1.0/lms/company/adminTokenTest").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }


}
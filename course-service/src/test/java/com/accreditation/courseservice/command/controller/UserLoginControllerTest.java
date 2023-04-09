package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.StringResponse;
import com.accreditation.courseservice.command.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserLoginControllerTest {

    @InjectMocks
    private UserLoginController userLoginController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
        ReflectionTestUtils.setField(userLoginController, "loginBaseUrl", "https://test.com");

    }

    @Test
    void test_login_success() throws Exception {

        UserDto userDto=UserDto.builder().email("test@test.com").password("password123").build();
        String userDtoString = new ObjectMapper().writeValueAsString(userDto);
        Map<String, String> stringStringMap=new HashMap<>();
        stringStringMap.put("token","test");
        ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(stringStringMap, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(), any(), any(ParameterizedTypeReference.class), any(Object[].class)))
                .thenReturn(responseEntity);

        ResponseEntity<?> response=userLoginController.login(userDto);
        assertEquals("test",((Map<String, String>)response.getBody()).get("token"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/courses/login").contentType(MediaType.APPLICATION_JSON).content(userDtoString))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


    }



    @Test
    void test_login_failed() throws Exception {

        UserDto userDto=UserDto.builder().email("test@test.com").password("password123").build();
        String userDtoString = new ObjectMapper().writeValueAsString(userDto);

        when(restTemplate.exchange(anyString(), any(), any(), any(ParameterizedTypeReference.class), any(Object[].class)))
                .thenThrow(RuntimeException.class);

        ResponseEntity<?> response=userLoginController.login(userDto);
        assertEquals("Login was not successful , Please enter valid credentials",((StringResponse)response.getBody()).getResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/courses/login").contentType(MediaType.APPLICATION_JSON).content(userDtoString))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void test_login_serviceUnavailable() throws Exception {

        UserDto userDto=UserDto.builder().email("test@test.com").password("password123").build();
        String userDtoString = new ObjectMapper().writeValueAsString(userDto);

        when(restTemplate.exchange(anyString(), any(), any(), any(ParameterizedTypeReference.class), any(Object[].class)))
                .thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        ResponseEntity<?> response=userLoginController.login(userDto);
        assertEquals("Service Unavailable",((StringResponse)response.getBody()).getResponse());

    }


}
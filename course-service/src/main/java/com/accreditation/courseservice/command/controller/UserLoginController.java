package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.StringResponse;
import com.accreditation.courseservice.command.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v/1.0/lms/courses")
@CrossOrigin("*")
public class UserLoginController {

    @Value("${loginBaseUrl}")
    private String loginBaseUrl;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, String>> response = null;

        try {
            response = restTemplate.exchange(loginBaseUrl, HttpMethod.POST, getHeaders(userDto), new ParameterizedTypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            return new ResponseEntity<StringResponse>(new StringResponse("Login was not successful , Please enter valid credentials"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<Map<String, String>>(response.getBody(), HttpStatus.OK);
    }

    private static HttpEntity<UserDto> getHeaders(UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<UserDto>(userDto, headers);
    }

}

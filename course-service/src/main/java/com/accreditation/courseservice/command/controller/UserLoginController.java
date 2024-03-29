package com.accreditation.courseservice.command.controller;

import com.accreditation.courseservice.command.dto.StringResponse;
import com.accreditation.courseservice.command.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v/1.0/lms/courses")
@CrossOrigin("*")
public class UserLoginController {

    @Value("${loginBaseUrl}")
    private String loginBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {

        ResponseEntity<Map<String, String>> response = null;

        try {
            log.info("Calling User Service with loginBaseUrl as "+loginBaseUrl);
            log.info("Calling User Service with email as "+userDto.getEmail());
            response = restTemplate.exchange(loginBaseUrl, HttpMethod.POST, getHeaders(userDto), new ParameterizedTypeReference<Map<String, String>>() {
            });

            log.info("Response from User Service with token as "+response);


        }catch(HttpServerErrorException.ServiceUnavailable e){
            log.error("HttpServerErrorException.ServiceUnavailable received is "+e.getMessage());
            return new ResponseEntity<>(new StringResponse("Service Unavailable"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Exception received is "+e.getMessage());
            return new ResponseEntity<>(new StringResponse("Login was not successful , Please enter valid credentials"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    private static HttpEntity<UserDto> getHeaders(UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(userDto, headers);
    }

}

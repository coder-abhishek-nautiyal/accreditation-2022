package com.accreditation.userservice.controller;

import com.accreditation.userservice.dto.UserDetailDto;
import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/1.0/lms/company")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDetailDto userDetailDto)  {

        UserDetail userDetail = UserDetail.builder()
                .username(userDetailDto.getUsername())
                .password(bcryptEncoder.encode(userDetailDto.getPassword()))
                .role(userDetailDto.getRole())
                .build();

        if(userService.save(userDetail) !=null){
            return new ResponseEntity<String>("User is successfully registered !!", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

}

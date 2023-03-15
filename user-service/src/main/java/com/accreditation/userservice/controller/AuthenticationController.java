package com.accreditation.userservice.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v/1.0/lms/company")
public class AuthenticationController {


    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/userTokenTest")
    public String getUserTokenResponse() {
        return "Hello, User is authenticated !!";
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/adminTokenTest")
    public String getAdminTokenResponse() {
        return "Hello, Admin is authenticated !!";
    }


}

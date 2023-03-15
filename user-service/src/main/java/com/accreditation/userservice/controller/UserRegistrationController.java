package com.accreditation.userservice.controller;

import com.accreditation.userservice.dto.AuthenticationRequest;
import com.accreditation.userservice.dto.AuthenticationResponse;
import com.accreditation.userservice.dto.StringResponse;
import com.accreditation.userservice.dto.UserDetailDto;
import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.security.config.JwtUtil;
import com.accreditation.userservice.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v/1.0/lms/company")
@CrossOrigin("*")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDetailDto userDetailDto) {


        UserDetail userDetail = UserDetail.builder()
                .email(userDetailDto.getEmail())
                .username(userDetailDto.getUsername())
                .password(bcryptEncoder.encode(userDetailDto.getPassword()))
                .role((userDetailDto.getRole() != null && !userDetailDto.getRole().isEmpty()) ? userDetailDto.getRole() : "ROLE_USER") /*If user role not specified consider ROLE_USER by default*/
                .build();

        if (userService.save(userDetail) != null) {
            return new ResponseEntity<StringResponse>(new StringResponse("User is successfully registered !!"), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userService.findUserByEmail(authenticationRequest.getEmail()).getUsername(), authenticationRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new AuthenticationResponse(token));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    @GetMapping(value = "/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonWebTokenClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonWebTokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }


}

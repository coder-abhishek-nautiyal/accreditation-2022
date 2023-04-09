package com.accreditation.userservice.controller;

import com.accreditation.userservice.dto.AuthenticationRequest;
import com.accreditation.userservice.dto.AuthenticationResponse;
import com.accreditation.userservice.dto.StringResponse;
import com.accreditation.userservice.dto.UserDetailDto;
import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.exception.UserServiceException;
import com.accreditation.userservice.security.config.JwtUtil;
import com.accreditation.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.impl.DefaultClaims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserRegistrationControllerTest {

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @Mock
    private PasswordEncoder bcryptEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
    }

    @Test
    void test_saveUser_userExist_roleIsNull() throws Exception {

        UserDetailDto userDetailDto=UserDetailDto.builder().username("test").email("test@test.com").password("password123").build();
        String userDetailDtoString = new ObjectMapper().writeValueAsString(userDetailDto);

        ResponseEntity<StringResponse> response=userRegistrationController.saveUser(userDetailDto);
        assertNull(response.getBody());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/company/register").contentType(MediaType.APPLICATION_JSON).content(userDetailDtoString))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void test_saveUser_userExist_roleIsEmpty() throws Exception {

        UserDetailDto userDetailDto=UserDetailDto.builder().username("test").email("test@test.com").password("password123").role("").build();
        String userDetailDtoString = new ObjectMapper().writeValueAsString(userDetailDto);

        ResponseEntity<StringResponse> response=userRegistrationController.saveUser(userDetailDto);
        assertNull(response.getBody());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/company/register").contentType(MediaType.APPLICATION_JSON).content(userDetailDtoString))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());


    }

    @Test
    void test_saveUser_newUser() throws Exception {

        UserDetailDto userDetailDto=UserDetailDto.builder().username("test").email("test@test.com").password("password123").role("ROLE_USER").build();
        String userDetailDtoString = new ObjectMapper().writeValueAsString(userDetailDto);

        when(userService.save(any())).thenReturn(new UserDetail());

        ResponseEntity<StringResponse> response=userRegistrationController.saveUser(userDetailDto);
        assertEquals("User is successfully registered !!",response.getBody().getResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/company/register").contentType(MediaType.APPLICATION_JSON).content(userDetailDtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated());


    }


    @Test
    void test_login() throws Exception {

        AuthenticationRequest authenticationRequest=AuthenticationRequest.builder().email("test@test.com").password("password123").build();
        String authenticationRequestString = new ObjectMapper().writeValueAsString(authenticationRequest);
        UserDetail userDetail=UserDetail.builder().id(1).username("test").email("test@test.com").role("ROLE_USER").password("password123").build();
        when(userService.findUserByEmail(any())).thenReturn(userDetail);

        when(authenticationManager.authenticate(any())).thenReturn(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return new User("test","", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        });

        ResponseEntity<AuthenticationResponse> response=userRegistrationController.login(authenticationRequest);
        assertEquals(200,response.getStatusCodeValue());


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v/1.0/lms/company/login").contentType(MediaType.APPLICATION_JSON).content(authenticationRequestString))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }


    @Test
    void test_login_disabledException()  {

        AuthenticationRequest authenticationRequest=AuthenticationRequest.builder().email("test@test.com").password("password123").build();
        UserDetail userDetail=UserDetail.builder().id(1).username("test").email("test@test.com").role("ROLE_USER").password("password123").build();
        when(userService.findUserByEmail(any())).thenReturn(userDetail);

        when(authenticationManager.authenticate(any())).thenThrow(DisabledException.class);
        assertThrows(UserServiceException.class, () -> {
            userRegistrationController.login(authenticationRequest);
        });

    }

    @Test
    void test_login_badCredentialException() {

        AuthenticationRequest authenticationRequest=AuthenticationRequest.builder().email("test@test.com").password("password123").build();
        UserDetail userDetail=UserDetail.builder().id(1).username("test").email("test@test.com").role("ROLE_USER").password("password123").build();
        when(userService.findUserByEmail(any())).thenReturn(userDetail);

        when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);
        assertThrows(UserServiceException.class, () -> {
            userRegistrationController.login(authenticationRequest);
        });

    }

    @Test
    void test_refreshToken() {

        DefaultClaims claims = new DefaultClaims();
        claims.setSubject("test");
        claims.setIssuer("test");
        when(request.getAttribute(any())).thenReturn(claims);

        ResponseEntity<AuthenticationResponse> response=userRegistrationController.refreshToken(request);
        assertNull(response.getBody().getToken());

    }


}
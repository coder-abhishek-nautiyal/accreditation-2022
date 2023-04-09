package com.accreditation.userservice.security.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JwtAuthenticationEntryPointTest {

    @InjectMocks
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authException;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_commence() throws IOException {

        when(request.getAttribute(any())).thenReturn(new RuntimeException("Runtime Exception"));
        when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
        jwtAuthenticationEntryPoint.commence(request,response,authException);
        assertEquals("Runtime Exception",((Exception) request.getAttribute(any())).getMessage());

    }

    @Test
    void test_commence_authExceptionCauseNull() throws IOException {

        when(authException.getMessage()).thenReturn("Exception");
        when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
         jwtAuthenticationEntryPoint.commence(request,response,authException);
        assertEquals("Exception",authException.getMessage());

    }

    @Test
    void test_commence_authExceptionCauseNotNull() throws IOException {

        when(authException.getCause()).thenReturn(new RuntimeException("Runtime Exception"));
        when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
        jwtAuthenticationEntryPoint.commence(request,response,authException);
        assertEquals("Runtime Exception",authException.getCause().getMessage());

    }


}
package com.accreditation.courseservice.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomJwtAuthenticationFilterTest {

    @InjectMocks
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Mock
    private JwtUtil jwtTokenUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterChain chain;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_doFilterInternal_withToken() throws Exception {

        when(request.getHeader("Authorization")).thenReturn("Bearer test");
        when(jwtTokenUtil.validateToken(any())).thenReturn(true);
        when(jwtTokenUtil.getRolesFromToken(any())).thenReturn(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("test");
        customJwtAuthenticationFilter.doFilterInternal(request,servletResponse,chain);
        assertNotNull(request.getHeader("Authorization"));
    }

    @Test
    void test_doFilterInternal_withTokenExpiredException() throws Exception {

        when(request.getHeader("Authorization")).thenReturn("Bearer test");
        when(jwtTokenUtil.validateToken(any())).thenThrow(ExpiredJwtException.class);
        when(request.getRequestURL()).thenReturn(new StringBuffer());
        when(jwtTokenUtil.getRolesFromToken(any())).thenReturn(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("test");
        customJwtAuthenticationFilter.doFilterInternal(request,servletResponse,chain);
        assertNotNull(request.getHeader("Authorization"));
    }


    @Test
    void test_doFilterInternal_withoutToken() throws Exception {

        when(request.getHeader("Authorization")).thenReturn("Bearer  ");
        customJwtAuthenticationFilter.doFilterInternal(request,servletResponse,chain);
        assertNotNull(request.getHeader("Authorization"));
    }

    @Test
    void test_doFilterInternal_withoutAuthorizationToken() throws Exception {

        when(request.getHeader("Authorization")).thenReturn(" ");
        customJwtAuthenticationFilter.doFilterInternal(request,servletResponse,chain);
        assertEquals(" ",request.getHeader("Authorization"));
    }



}
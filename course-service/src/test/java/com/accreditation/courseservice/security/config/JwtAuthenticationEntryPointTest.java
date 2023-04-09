package com.accreditation.courseservice.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.impl.DefaultClaims;
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
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void test_commence_exceptionInstanceOfExpiredException() throws IOException {

        when(request.getAttribute(any())).thenReturn(new ExpiredJwtException(new Header() {
            @Override
            public String getType() {
                return null;
            }

            @Override
            public Header setType(String s) {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public Header setContentType(String s) {
                return null;
            }

            @Override
            public String getCompressionAlgorithm() {
                return null;
            }

            @Override
            public Header setCompressionAlgorithm(String s) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Object get(Object key) {
                return null;
            }

            @Override
            public Object put(Object key, Object value) {
                return null;
            }

            @Override
            public Object remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set keySet() {
                return null;
            }

            @Override
            public Collection values() {
                return null;
            }

            @Override
            public Set<Entry> entrySet() {
                return null;
            }
        }, new DefaultClaims(),""));
        when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
        jwtAuthenticationEntryPoint.commence(request,response,authException);
        assertEquals("",((Exception) request.getAttribute(any())).getMessage());

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
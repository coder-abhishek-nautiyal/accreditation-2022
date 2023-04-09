package com.accreditation.courseservice.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtil, "secret", "secret");
    }

    @Test
    void test_getUsernameFromToken() {

        String token= Jwts.builder().setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
        String response=jwtUtil.getUsernameFromToken(token);
        assertEquals("test",response);

    }

    @Test
    void test_getRolesFromToken() {

        Map<String, Object> claims= new HashMap<>();
        claims.put("role", "ROLE_USER");
        String token= Jwts.builder().setClaims(claims).setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
        List<SimpleGrantedAuthority> response=jwtUtil.getRolesFromToken(token);
        assertEquals(1,response.size());

    }


    @Test
    void test_getRolesFromToken_withoutClaims() {

        String token= Jwts.builder().setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
        List<SimpleGrantedAuthority> response=jwtUtil.getRolesFromToken(token);
        assertNull(response);

    }


    @Test
    void test_validateToken() {

        String token= Jwts.builder().setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
        Boolean response=jwtUtil.validateToken(token);
        assertTrue(response);

    }

    @Test
    void test_validateToken_expiredToken() {

        String token= Jwts.builder().setSubject("test").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()-1))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
        assertThrows(ExpiredJwtException.class, () -> jwtUtil.validateToken(token));

    }


    @Test
    void test_validateToken_unsupportedJWTException() {

        assertThrows(BadCredentialsException.class, () -> jwtUtil.validateToken("test"));

    }



}
package com.accreditation.courseservice.security.config;

import com.accreditation.courseservice.util.ExceptionConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Exception exception = (Exception) request.getAttribute("exception");

        String message;

        if (exception != null) {
            byte[] body = null;


            if(exception instanceof ExpiredJwtException){
                body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("message", ExceptionConstant.SESSION_EXPIRED_LOGIN_AGAIN));
            }else{
                body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("message", exception.toString()));

            }


            response.getOutputStream().write(body);

        } else {

            if (authException.getCause() != null) {
                message = authException.getCause().toString() + " " + authException.getMessage();
            } else {
                message = authException.getMessage();
            }

            byte[] body=null;

            if(authException instanceof InsufficientAuthenticationException){
                body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("message", ExceptionConstant.TOKEN_MISSING_LOGIN_AGAIN));
            }else{
                body  = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("message", message));

            }

            response.getOutputStream().write(body);
        }
    }

}

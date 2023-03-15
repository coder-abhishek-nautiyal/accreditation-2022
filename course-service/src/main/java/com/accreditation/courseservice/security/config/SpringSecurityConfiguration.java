package com.accreditation.courseservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {


    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/api/v/1.0/lms/courses/login", "/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**").permitAll()
                .antMatchers("/api/v/1.0/lms/courses/add", "/api/v/1.0/lms/courses/update", "/api/v/1.0/lms/courses/delete**").hasRole("ADMIN")
                .antMatchers("/api/v/1.0/lms/courses/getall", "/api/v/1.0/lms/courses/info/**", "/api/v/1.0/lms/courses/get/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

}

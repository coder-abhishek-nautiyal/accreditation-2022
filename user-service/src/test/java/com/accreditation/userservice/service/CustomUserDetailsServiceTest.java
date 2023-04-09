package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {

    @InjectMocks
    private  CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_loadUserByUsername() {

        UserDetail userDetail= UserDetail.builder().email("test@test.com").username("test").password("password123").role("ROLE_USER").build();
        when(userRepository.findUserByUsername("test")).thenReturn(Optional.ofNullable(userDetail));

        UserDetails userDetails=customUserDetailsService.loadUserByUsername("test");
        assertEquals("test", userDetails.getUsername());

    }


    @Test
    void test_loadUserByUsername_UserNotExist() {

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("test"));

    }


}
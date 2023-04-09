package com.accreditation.userservice.service;

import com.accreditation.userservice.entity.UserDetail;
import com.accreditation.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private  UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_save() {

        UserDetail userDetail= UserDetail.builder().email("test@test.com").username("test").password("password123").role("ROLE_USER").build();

        UserDetail response= userServiceImpl.save(userDetail);
        assertNull(response);
    }


    @Test
    void test_save_userNameExist() {

        UserDetail userDetail= UserDetail.builder().email("test@test.com").username("test").password("password123").role("ROLE_USER").build();
        when(userRepository.findUserByUsername("test")).thenReturn(Optional.ofNullable(userDetail));

        assertThrows(ResponseStatusException.class, () -> userServiceImpl.save(userDetail));

    }

    @Test
    void test_save_emailExist() {

        UserDetail userDetail= UserDetail.builder().email("test@test.com").username("test").password("password123").role("ROLE_USER").build();
        when(userRepository.findUserByEmail("test@test.com")).thenReturn(Optional.ofNullable(userDetail));

        assertThrows(ResponseStatusException.class, () -> userServiceImpl.save(userDetail));

    }


    @Test
    void test_findUserByEmail_emailNotExist() {

        assertThrows(ResponseStatusException.class, () -> userServiceImpl.findUserByEmail("test@test.com"));

    }


    @Test
    void test_findUserByEmail_emailExist() {

        UserDetail userDetail= UserDetail.builder().email("test@test.com").username("test").password("password123").role("ROLE_USER").build();
        when(userRepository.findUserByEmail("test@test.com")).thenReturn(Optional.ofNullable(userDetail));

        UserDetail response=userServiceImpl.findUserByEmail("test@test.com");
        assertEquals("test", response.getUsername());

    }



}
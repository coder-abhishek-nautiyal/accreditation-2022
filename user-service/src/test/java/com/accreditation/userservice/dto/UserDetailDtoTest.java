package com.accreditation.userservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailDtoTest {


    @Test
    void test_userDetailDto() {

        UserDetailDto userDetailDto=new UserDetailDto();
        userDetailDto.setUsername("admin");
        userDetailDto.setEmail("admin@admin.com");
        userDetailDto.setRole("ROLE_ADMIN");
        userDetailDto.setPassword("password123");

        assertEquals("admin",userDetailDto.getUsername());
        assertEquals("password123",userDetailDto.getPassword());
        assertEquals("admin@admin.com",userDetailDto.getEmail());
        assertEquals("ROLE_ADMIN",userDetailDto.getRole());
        assertEquals("UserDetailDto(username=admin, email=admin@admin.com, password=password123, role=ROLE_ADMIN)",userDetailDto.toString());

        UserDetailDto userDetailDto1= UserDetailDto.builder().username("test").email("test@test.com").password("password123").role("ROLE_USER").build();

        assertEquals("test",userDetailDto1.getUsername());
        assertEquals("password123",userDetailDto1.getPassword());
        assertEquals("test@test.com",userDetailDto1.getEmail());
        assertEquals("ROLE_USER",userDetailDto1.getRole());
        assertEquals("UserDetailDto(username=test, email=test@test.com, password=password123, role=ROLE_USER)",userDetailDto1.toString());

    }


}
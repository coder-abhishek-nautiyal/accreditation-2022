package com.accreditation.courseservice.command.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void test_userDto() {

        UserDto userDto=new UserDto();
        userDto.setEmail("admin@admin.com");
        userDto.setPassword("password123");

        assertEquals("password123",userDto.getPassword());
        assertEquals("admin@admin.com",userDto.getEmail());
        assertEquals("UserDto(email=admin@admin.com, password=password123)",userDto.toString());

        UserDto userDto1= UserDto.builder().email("test@test.com").password("password123").build();

        assertEquals("password123",userDto1.getPassword());
        assertEquals("test@test.com",userDto1.getEmail());
        assertEquals("UserDto(email=test@test.com, password=password123)",userDto1.toString());

    }



}
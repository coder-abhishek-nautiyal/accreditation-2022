package com.accreditation.userservice.entity;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserDetailTest {

    @Test
    void test_userDetail() {

        UserDetail userDetail=new UserDetail();
        userDetail.setId(1);
        userDetail.setUsername("admin");
        userDetail.setEmail("admin@admin.com");
        userDetail.setRole("ROLE_ADMIN");
        userDetail.setPassword("password123");

        assertEquals(1,userDetail.getId());
        assertEquals("admin",userDetail.getUsername());
        assertEquals("password123",userDetail.getPassword());
        assertEquals("admin@admin.com",userDetail.getEmail());
        assertEquals("ROLE_ADMIN",userDetail.getRole());
        assertEquals("UserDetail(id=1, username=admin, email=admin@admin.com, password=password123, role=ROLE_ADMIN)",userDetail.toString());

        //UserDetail userDetail1=new UserDetail(2,"test","test@test.com","password123","ROLE_USER");
        UserDetail userDetail1= UserDetail.builder().id(2).username("test").email("test@test.com").password("password123").role("ROLE_USER").build();
        assertEquals(2,userDetail1.getId());
        assertEquals("test",userDetail1.getUsername());
        assertEquals("password123",userDetail1.getPassword());
        assertEquals("test@test.com",userDetail1.getEmail());
        assertEquals("ROLE_USER",userDetail1.getRole());
        assertEquals("UserDetail(id=2, username=test, email=test@test.com, password=password123, role=ROLE_USER)",userDetail1.toString());

    }

}
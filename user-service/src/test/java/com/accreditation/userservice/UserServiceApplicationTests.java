package com.accreditation.userservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {

		UserServiceApplication.main(new String[] {});
		assertTrue(true);
	}

}

package com.accreditation.courseservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CourseCommandServiceApplicationTests {

	@Test
	void contextLoads() {

		CourseServiceApplication.main(new String[] {});
		assertTrue(true);
	}

}

package com.methodsecurity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MethodSecurityApplicationTests {

	@BeforeAll
	static void beforeAll() {
		System.out.println("**--- Executed once before all test methods in this class ---**");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("**--- Executed once after all test methods in this class ---**");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("**--- Executed before each test method in this class ---**");
	}

	@Test
	void testMethod1() {
		System.out.println("**--- Test method1 executed ---**");
	}

	@DisplayName("Test method2 with condition")
	@Test
	void testMethod2() {
		System.out.println("**--- Test method2 executed ---**");
	}

	@Test
	@Disabled("implementation pending")
	void testMethod3() {
		System.out.println("**--- Test method3 executed ---**");
	}

	@AfterEach
	void afterEach() {
		System.out.println("**--- Executed after each test method in this class ---**");
	}

	@DisplayName("Test method4 with condition")
	@RepeatedTest(3)
	void testMethod4() {
		System.out.println("**--- Test method4 executed ---**");
	}

}
package tech.luv2code99;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(Lifecycle.PER_METHOD)
@DisplayName("When running MathUtilsTest")
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	static void BeforeAllInit() {
		System.out.println("Running BeforeAllInit()...");
	}

	@AfterAll
	static void AfterAllCleanup() {
		System.out.println("Running AfterAll cleanup...");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("Before each test...");
		testReporter.publishEntry("Running Init() before each test with tags" + testInfo.getTags() + " with Display Name: " + testInfo.getDisplayName());
		mathUtils = new MathUtils();
	}

	@AfterEach
	@RepeatedTest(1)
	void clean() {
		System.out.println("Cleanup");
	}

	@Nested
	@DisplayName("while testing substract")
	@Tag("Math")
	class testSubstract {

		@Test
		@DisplayName("method of negative results")
		void testSubstractNegative() {
			assertEquals(-1, mathUtils.substract(3, 4), () -> "should result in a negative number");
		}

		@Test
		@DisplayName("method of negative results")
		void testSubstractPositive() {
			assertEquals(1, mathUtils.substract(4, 3), "should result in a positive number");
		}

	}

	@Test
	@DisplayName("Testing Add method")
	void testAdd() {
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual, () -> "was expected " + expected + " but returned "+ actual);
		int  expected_2 = 0;
		int actual_2  = mathUtils.add(-1, 1);
		assertEquals(expected_2, actual_2, "This should result in zero");
	}

	@Test
	@Tag("Math")
	@DisplayName("Testing Compute Circie Area")
	void testComputeCircleArea() {
		boolean isServerUp = true;
		assumeTrue(isServerUp);
		double expected = 153.93804002589985;
		double actual = mathUtils.computeCircleArea(7);
		assertEquals(expected, actual, "This computes area of a circle");
	}

	@Test
	@DisplayName("Testing Divide Method")
	@EnabledOnOs(OS.LINUX)
	@EnabledOnJre(JRE.JAVA_8)
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "This throws Arithmetic Exception");
	}

	@Test
	@DisplayName("Tested Multiply Method")
	void testMultiply() {
		// assertEquals(2, mathUtils.multiply(2, 2), "should output the right product");
		assertAll(
				() -> assertEquals(4, mathUtils.multiply(2, 2)),
				() -> assertEquals(-2, mathUtils.multiply(-1, 2)),
				() -> assertEquals(0, mathUtils.multiply(2, 0)));
	}

	@Test
	@DisplayName("TDD Method - Should not run")
	@Disabled
	void testDisabled() {
		fail("This is bound to fail");
	}
}

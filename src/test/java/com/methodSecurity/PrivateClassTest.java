package com.methodSecurity;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PrivateClassTest {

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        System.out.println("**--- Executed before each test method in this class ---** " + displayName);
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        System.out.println("**--- Executed after each test method in this class ---** " + displayName);
    }

    @Test
    @Disabled
    @DisplayName("Test Private Method for Repeated String")
    void testPrivateMethod() throws Exception {
        PrivateClass testClass = new PrivateClass();
        Method privateMethod = testClass.getClass().getDeclaredMethod("repeat", String.class);
        privateMethod.setAccessible(true);
        String result = (String) privateMethod.invoke(testClass, " Hello");
        assertEquals(" Hello Hello", result);
        System.out.println("Test Private Method for Repeated String passed");
    }

    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = {" ok ", " yes"})
    void checkForBlankStringTest(String input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PrivateClass testClass = new PrivateClass();
        Method privateMethod = testClass.getClass().getDeclaredMethod("checkForBlankString", String.class);
        privateMethod.setAccessible(true);
        Boolean result = (Boolean) privateMethod.invoke(testClass, input);
        assertEquals(false, result);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    @Order(3)
    @ParameterizedTest(name = "{index} {0} is 30 days long")
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void someMonths_Are30DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(30, month.length(isALeapYear));
    }

}

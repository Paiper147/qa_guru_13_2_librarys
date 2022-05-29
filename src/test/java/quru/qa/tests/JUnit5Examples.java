package quru.qa.tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit5Examples {

    @BeforeAll
    static void beforeAll_1(){
        System.out.println("==  Something before All tests_1");
    }

    @BeforeAll
    static void beforeAll_21(){
        System.out.println("==  Something before All tests_2");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("==  Something after All tests");
    }

    @BeforeEach
    void beforeEach_1(){
        System.out.println("==== Something before Each tests");
    }

    @AfterEach
    void afterEach_1(){
        System.out.println("==== Something after Each tests");
    }

    @Test
    void firstTest() {
        System.out.println("=========  Started = firstTest !!!!");
    }

    @Test
    void secondTest() {
        System.out.println("=========  Started = secondTest !!!!");
        assertEquals(2, 2);
    }

    @Test
    void thirdTest() {
        System.out.println("=========  Started = thirdTest !!!!!");
    }
}

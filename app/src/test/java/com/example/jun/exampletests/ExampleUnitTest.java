package com.example.jun.exampletests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


// Testing Person class
public class ExampleUnitTest {

    //initialize the test
    Person XiaoMing = new Person("XiaoMing", "ming@gmail.com", 18);

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testName() {
        assertEquals(18, XiaoMing.getAge());
    }

    @Test
    public void testHappyBirthday() {
        XiaoMing.happyBirthday();
        assertEquals(19, XiaoMing.getAge());
    }
}
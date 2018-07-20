package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterCourseJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.RegisterCourse");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'RegisterCourse'.");
        }

    }
}
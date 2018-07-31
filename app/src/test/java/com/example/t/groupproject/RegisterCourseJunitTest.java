package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class RegisterCourseJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.RegisterCourse");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'RegisterCourse'.");
        }

    }
}
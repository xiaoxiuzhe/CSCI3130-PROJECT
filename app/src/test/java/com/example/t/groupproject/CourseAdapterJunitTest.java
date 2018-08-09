package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class CourseAdapterJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.ViewAdapter.CourseAdapter");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'CourseAdapter'.");
        }

    }
}
package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class CourseListsJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.CourseLists");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'CourseLists'.");
        }

    }
}
package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

//test the existence of the 'Tuition' class
public class TuitionTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.Tuition");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Tuition'.");
        }

    }
}
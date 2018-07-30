package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class HomeJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.Home");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Home'.");
        }

    }
}
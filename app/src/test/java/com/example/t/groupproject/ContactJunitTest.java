package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class ContactJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.Contact");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'Contact'.");
        }

    }
}
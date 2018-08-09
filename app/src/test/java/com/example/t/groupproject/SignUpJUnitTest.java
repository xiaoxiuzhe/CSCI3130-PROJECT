package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

public class SignUpJUnitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.View.SignUp");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'SignUp'.");
        }

    }
}
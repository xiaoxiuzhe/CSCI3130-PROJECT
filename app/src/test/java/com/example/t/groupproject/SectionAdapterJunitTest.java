package com.example.t.groupproject;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SectionAdapterJunitTest {
    @Test
    public void tstMethods(){
        try {
            Class.forName("com.example.t.groupproject.SectionAdapter");
        } catch(ClassNotFoundException e) {
            Assert.fail("Should create a class called 'SectionAdapter'.");
        }

    }
}
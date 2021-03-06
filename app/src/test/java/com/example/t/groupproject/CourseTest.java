package com.example.t.groupproject;

import com.example.t.groupproject.Model.Course;
import com.example.t.groupproject.Model.Section;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class CourseTest {
    private String courseName, courseLink;
    private ArrayList<Section> sections= new ArrayList<Section>();
    Course demo;
    @Before
    public void setUp(){
        courseName="1";
        courseLink="1";
        Section s1=new Section("1","1","1","1","1","1","1","1","1","1", "1", "1","","","","","","","","");
        Section s2=new Section("2","2","2","2","2","1","2","2","2","2", "2","2","","","","","","","","");
        sections.add(s1);
        sections.add(s2);
        demo=new Course(courseName, courseLink);

    }

    @Test
    public void getCourseName() {
        assertEquals(demo.getCourseName(),"1");
    }

    @Test
    public void setCourseName() {
        demo.setCourseName("3");
        assertEquals(demo.getCourseName(),"3");
    }

    @Test
    public void getCourseLink() {
        assertEquals(demo.getCourseLink(),"1");

    }

    @Test
    public void setCourseLink() {
        demo.setCourseLink("3");
        assertEquals(demo.getCourseLink(),"3");

    }

    //@Test
    //public void getSections() {
        //assertEquals(demo.getSections(), sections);
   // }

    @Test
    public void setSections() {
        Section s3=new Section("n","n","n","n","n","n","n","n","n","n","n","n","n","","","","","","","");
        sections.add(s3);
        //demo.setSections(sections);
        //assertEquals(demo.getSections(), sections);
    }
}
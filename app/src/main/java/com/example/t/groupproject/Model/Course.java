package com.example.t.groupproject.Model;
/**
 * Class that defines the Course
 */

public class Course {
    private String courseName;
    private String courseLink;
//    private ArrayList<Section> sections;

    public Course(){}

    public Course(String courseName, String courseLink) {
        this.courseName = courseName;
        this.courseLink = courseLink;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }


}
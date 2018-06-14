package com.example.t.groupproject;

public class Section {
    private int sectionId;
    private int courseId;
    private String location;

    public Section(int sectionId, int courseId, String location){
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.location = location;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

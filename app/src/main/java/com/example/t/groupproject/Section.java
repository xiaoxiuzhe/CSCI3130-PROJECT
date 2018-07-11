package com.example.t.groupproject;

public class Section {
    private String sectionCode;
    private String sectionType;
    private String courseId;
    private String location;
    private String times;
    private String currentSeat;
    private String availableSeat;
    private String maxSeat;
    private String waitList;
    private String professorLink;
    private String tutCode;
    private String professorName;
    private String billingHours;
    private String billingCode;

    public Section() {
    }

    public Section(
            String sectionCode,
            String sectionType,
            String courseId,
            String location,
            String times,
            String currentSeat,
            String availableSeat,
            String maxSeat,
            String waitList,
            String professorLink,
            String tutCode,
            String professorName,
            String billingHours,
            String billingCode
    ) {
        this.sectionCode = sectionCode;
        this.sectionType = sectionType;
        this.courseId = courseId;
        this.location = location;
        this.times = times;
        this.currentSeat = currentSeat;
        this.availableSeat = availableSeat;
        this.maxSeat = maxSeat;
        this.waitList = waitList;
        this.professorLink = professorLink;
        this.tutCode = tutCode;
        this.professorName = professorName;
        this.billingHours = billingHours;
        this.billingCode = billingCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getCurrentSeat() {
        return currentSeat;
    }

    public void setCurrentSeat(String currentSeat) {
        this.currentSeat = currentSeat;
    }

    public String getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(String availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(String maxSeat) {
        this.maxSeat = maxSeat;
    }

    public String getWaitList() {
        return waitList;
    }

    public void setWaitList(String waitList) {
        this.waitList = waitList;
    }

    public String getProfessorLink() {
        return professorLink;
    }

    public void setProfessorLink(String professorLink) {
        this.professorLink = professorLink;
    }

    public String getTutCode() {
        return tutCode;
    }

    public void setTutCode(String tutCode) {
        this.tutCode = tutCode;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getBillingHours() {
        return billingHours;
    }

    public void setBillingHours(String billingHours) {
        this.billingHours = billingHours;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }
}
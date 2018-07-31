package com.example.t.groupproject.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Section object, this object will store a Section attributes
 * and can be converted to JSON format
 */
public class Section {

    private String crn;
    private String sectionCode;
    private String sectionType;
    private String sectionId;
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
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public Section() {
    }

    public Section(
            String crn,
            String sectionCode,
            String sectionType,
            String sectionId,
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
            String billingCode,
            String monday,
            String tuesday,
            String wednesday,
            String thursday,
            String friday
    ) {
        this.crn = crn;
        this.sectionCode = sectionCode;
        this.sectionType = sectionType;
        this.sectionId = sectionId;
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
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
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

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
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

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("crn", crn);
        result.put("section_code", sectionCode);
        result.put("section_type", sectionType);
        result.put("section_id",sectionId);
        result.put("location",location);
        result.put("times",times);
        result.put("cur",currentSeat);
        result.put("avail",availableSeat);
        result.put("max",maxSeat);
        result.put("wtlist",waitList);
        result.put("proflink",professorLink);
        result.put("tu",tutCode);
        result.put("instructor",professorName);
        result.put("bhrs",billingHours);
        result.put("code",billingCode);
        result.put("mo",monday);
        result.put("tu",tuesday);
        result.put("we",wednesday);
        result.put("th",thursday);
        result.put("fr",friday);

        return result;
    }

}
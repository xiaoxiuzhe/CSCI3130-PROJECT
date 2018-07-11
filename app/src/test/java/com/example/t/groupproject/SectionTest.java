package com.example.t.groupproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SectionTest {
    private String crn,sectionCode, sectionType, sectionId, location, times, currentSeat, availableSeat, maxSeat, waitList, professorLink, tutCode, professorName,billingHours,billingCode,monday,tuesday,wednesday,thursday,friday;
    Section demo;
    @Before
    public void setUp(){
        crn="";
        sectionCode="Sec1";
        sectionType="type1";
        sectionId="20296";
        location="Room1";
        times="1305-1425";
        currentSeat="69";
        availableSeat="31";
        maxSeat="100";
        waitList="20";
        professorLink="tst";
        tutCode="s";
        professorName="name";
        billingHours="";
        billingCode="";
        monday="";
        tuesday="";
        wednesday="";
        thursday="";
        friday="";
        demo=new Section(crn,sectionCode, sectionType, sectionId, location, times, currentSeat, availableSeat, maxSeat, waitList, professorLink,tutCode,professorName,billingHours,billingCode,monday,tuesday,wednesday,thursday,friday);
    }
    @Test
    public void getSectionCode() {
        assertEquals(demo.getSectionCode(),"Sec1");
    }

    @Test
    public void setSectionCode() {
        demo.setSectionCode("Sec2");
        assertEquals(demo.getSectionCode(),"Sec2");
    }

    @Test
    public void getSectionType() {
        assertEquals(demo.getSectionType(),"type1");
    }

    @Test
    public void setSectionType() {
        demo.setSectionType("type2");
        assertEquals(demo.getSectionType(),"type2");
    }

    @Test
    public void getCourseId() {
        assertEquals(demo.getSectionId(),"20296");
    }

    @Test
    public void setCourseId() {
        demo.setSectionId("20297");
        assertEquals(demo.getSectionId(),"20297");

    }

    @Test
    public void getLocation() {
        assertEquals(demo.getLocation(),"Room1");
    }

    @Test
    public void setLocation() {
        demo.setLocation("Room2");
        assertEquals(demo.getLocation(),"Room2");

    }

    @Test
    public void getTimes() {
        assertEquals(demo.getTimes(),"1305-1425");
    }

    @Test
    public void setTimes() {
        demo.setTimes("1425-1355");
        assertEquals(demo.getTimes(),"1425-1355");

    }

    @Test
    public void getCurrentSeat() {
        assertEquals(demo.getCurrentSeat(),"69");
    }

    @Test
    public void setCurrentSeat() {
        demo.setCurrentSeat("70");
        assertEquals(demo.getCurrentSeat(),"70");
    }

    @Test
    public void getAvailableSeat() {
        assertEquals(demo.getAvailableSeat(),"31");
    }

    @Test
    public void setAvailableSeat() {
        demo.setAvailableSeat("30");
        assertEquals(demo.getAvailableSeat(),"30");

    }

    @Test
    public void getMaxSeat() {
        assertEquals(demo.getMaxSeat(),"100");
    }

    @Test
    public void setMaxSeat() {
        demo.setMaxSeat("120");
        assertEquals(demo.getMaxSeat(),"120");

    }

    @Test
    public void getWaitList() {
        assertEquals(demo.getWaitList(),"20");
    }

    @Test
    public void setWaitList() {
        demo.setWaitList("30");
        assertEquals(demo.getWaitList(),"30");

    }
}
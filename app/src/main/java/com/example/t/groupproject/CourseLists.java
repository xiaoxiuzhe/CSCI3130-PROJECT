package com.example.t.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourseLists extends AppCompatActivity {
    private DatabaseReference database;
    private ArrayList<Course> courseList;
    private String faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_lists);


        // ---------------------------------new edited--------------------
        // Initialize values
        faculty = getIntent().getExtras().getString("FACULTY");
        database = FirebaseDatabase.getInstance().getReference().child(faculty);
        courseList = new ArrayList<>();

        // retrieve data from Database, then show them on textView
        database.addValueEventListener(new ValueEventListener() {
            // 'onDataChange' method reloads data while changes happened in database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String courseName, courseLink = "";
                String courseId, currentSeat, availableSeat, maxSeat, waitList;
                String sectionCode, sectionType, location, times, professorLink, tutCode, professorName;
                String result = "";

                for (DataSnapshot course : dataSnapshot.getChildren()) {
                    courseName = course.getKey();
                    ArrayList<Section> sections = new ArrayList<>();

                    for (DataSnapshot section : course.getChildren()) {
                        if (section.getKey().equals("classlink")) {
                            courseLink = section.getValue().toString();
                        } else {
                            courseId = section.child("crn").getValue().toString();
                            currentSeat = section.child("cur").getValue().toString();
                            availableSeat = section.child("avail").getValue().toString();
                            maxSeat = section.child("max").getValue().toString();
                            waitList = section.child("wtlist").getValue().toString();
                            
                            location = section.child("location").getValue().toString();
                            sectionType = section.child("section_type").getValue().toString();
                            sectionCode = section.child("section_code").getValue().toString();
                            times = section.child("times").getValue().toString();
                            professorLink= section.child("proflink").getValue().toString();
                            tutCode =  section.child("code").getValue().toString();
                            professorName = section.child("instructor").getValue().toString();

                            Section singleSection = new Section(sectionCode, sectionType, courseId, location, times, currentSeat, availableSeat, maxSeat, waitList, professorLink, tutCode, professorName);
                            sections.add(singleSection);

//                            result = result + courseName + " - " + location + "- courseID:" + sectionCode + "\n";
                        }
                    }

                    courseList.add(new Course(courseName, courseLink, sections));
                }

                displayCourseList();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //--------------------------------end--------------------------

    }


    public void displayCourseList() {
        LinearLayout courseListLayout = (LinearLayout) findViewById(R.id.courseListLayout);

        for (Course course : courseList) {
            TextView text = new TextView(CourseLists.this);
            String courseDetail = "";

            int tutFee = 0;
            for (int i = 0; i < course.getSections().size(); i++) {
                tutFee = 0;
                if(course.getSections().get(i).getTutCode().equals("T210") )
                    tutFee = 500;
                    if(course.getSections().get(i).getTutCode().equals("T410"))
                        tutFee = 600;
                        if(course.getSections().get(i).getTutCode().equals("T610"))
                            tutFee = 700;


                courseDetail += "\n\t\t\t" + course.getSections().get(i).getSectionCode() + "\t" +
                        course.getSections().get(i).getCourseId() + "\t" +
                        course.getSections().get(i).getTimes()+"\t\t" + "$"+tutFee+
                       "\t\t"+ course.getSections().get(i).getProfessorName()+"\t"+
                "\t\t"+ course.getSections().get(i).getProfLink()+ "\t";
            }

            text.setText(course.getCourseName() + courseDetail+"\n");


            courseListLayout.addView(text);
        }
    }
}






package com.example.t.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CourseLists extends AppCompatActivity {
    private DatabaseReference database;
    private TextView courseView2;
    private HashMap<String, Section> sectionList;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_lists);


        // Write a message to the database
        database = FirebaseDatabase.getInstance().getReference().child("Computer_Science");
        courseView2 = (TextView) findViewById(R.id.courseView2);


        // retrieve data from Database, then show them on textView
        database.addValueEventListener(new ValueEventListener() {

            // 'onDataChange' method reloads data while changes happened in database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int sectionId, courseId;
                String courseName, location, classlink;
                String result = "";

                for (DataSnapshot course : dataSnapshot.getChildren()) {
                    courseName = course.getKey();
                    for (DataSnapshot section : course.getChildren()) {
                        if (section.getKey().equals("classlink")) {
                            classlink = section.getValue().toString();
                        } else {
                            sectionId = Integer.parseInt(section.getKey());
                            courseId = Integer.parseInt(section.child("crn").getValue().toString());
                            location = section.child("location").getValue().toString();

                            result = result + courseName + " - " + location + "- courseID:" + courseId + " - sectionID:" + sectionId + "\n";
                        }
                    }
                }

                //display result on NameView
                  courseView2.setText(result);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

    
    
    
    
    

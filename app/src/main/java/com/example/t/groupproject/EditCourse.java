package com.example.t.groupproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourse extends AppCompatActivity {
    private String faculty, courseName, userUid, sectionCode;
    private TextView sectionTextView, crnTextView;
    private EditText sectionTypeEditText, availableEditText, maxEditText, locationEditText, daysEditText, timeEditText;
    private Button updateButton;
    private DatabaseReference database;
    private Section section;
    private int sectionInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("map");
//        Log.v("HashMapTest", hashMap.get("sectionCode"));
        faculty = hashMap.get("faculty");
        courseName = hashMap.get("courseName");
        userUid = hashMap.get("userUid");
        sectionCode = hashMap.get("sectionCode");

        //initialize elements
        sectionTextView = (TextView) findViewById(R.id.sectionTextView);
        crnTextView = (TextView) findViewById(R.id.crnTextView);

        sectionTypeEditText = (EditText) findViewById(R.id.sectionTypeEditText);
        availableEditText = (EditText) findViewById(R.id.availableEditText);
        maxEditText = (EditText) findViewById(R.id.maxEditText);
        locationEditText = (EditText) findViewById(R.id.locationEditText);
        daysEditText = (EditText) findViewById(R.id.daysEditText);
        timeEditText = (EditText) findViewById(R.id.timeEditText);

        // Initialize values
        setTitle(courseName.replace("_", " "));
        sectionInt = Integer.parseInt(sectionCode);
        final String sectionString = Integer.toString(sectionInt);
        database = FirebaseDatabase.getInstance().getReference().child(faculty).child(courseName).child(sectionString);


        // read data from database
        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String sectionType = String.valueOf(dataSnapshot.child("section_type").getValue());
//                String crn= String.valueOf(dataSnapshot.child("crn").getValue());
//                String available = String.valueOf(dataSnapshot.child("avail").getValue());
//                String max = String.valueOf(dataSnapshot.child("max").getValue());
//                String location = String.valueOf(dataSnapshot.child("location").getValue());
//                String time = String.valueOf(dataSnapshot.child("times").getValue());
//                String monday= String.valueOf(dataSnapshot.child("mo").getValue());
//                String tuesday= String.valueOf(dataSnapshot.child("tu").getValue());
//                String wednesday= String.valueOf(dataSnapshot.child("we").getValue());
//                String thursday= String.valueOf(dataSnapshot.child("th").getValue());
//                String friday= String.valueOf(dataSnapshot.child("fr").getValue());
//                String days = "MWF";
//                Log.v("HashMapTest", sectionType);
                String crn;
                String sectionCode;
                String sectionType;
                String sectionId;
                String location;
                String times;
                String currentSeat;
                String availableSeat;
                String maxSeat;
                String waitList;
                String professorLink;
                String tutCode;
                String professorName;
                String billingHours;
                String billingCode;
                String monday;
                String tuesday;
                String wednesday;
                String thursday;
                String friday;


                crn = String.valueOf(dataSnapshot.child("crn").getValue());
                sectionId = String.valueOf(dataSnapshot.getKey());
                currentSeat = String.valueOf(dataSnapshot.child("cur").getValue());
                availableSeat = String.valueOf(dataSnapshot.child("avail").getValue());
                maxSeat = String.valueOf(dataSnapshot.child("max").getValue());
                waitList = String.valueOf(dataSnapshot.child("wtlist").getValue());
                billingHours = String.valueOf(dataSnapshot.child("bhrs").getValue());

                sectionCode = dataSnapshot.child("section_code").getValue().toString();
                sectionType = dataSnapshot.child("section_type").getValue().toString();
                location = dataSnapshot.child("location").getValue().toString();
                times = dataSnapshot.child("times").getValue().toString();

                professorLink = dataSnapshot.child("proflink").getValue().toString();

                tutCode = dataSnapshot.child("tu").getValue().toString();

                professorName = dataSnapshot.child("instructor").getValue().toString();
                billingCode = dataSnapshot.child("code").getValue().toString();
                monday = dataSnapshot.child("mo").getValue().toString();
                tuesday = dataSnapshot.child("tu").getValue().toString();
                wednesday = dataSnapshot.child("we").getValue().toString();
                thursday = dataSnapshot.child("th").getValue().toString();

                friday = dataSnapshot.child("fr").getValue().toString();

                section =
                        new Section(
                                crn,
                                sectionCode,
                                sectionType,
                                sectionId,
                                location,
                                times,
                                currentSeat,
                                availableSeat,
                                maxSeat,
                                waitList,
                                professorLink,
                                tutCode,
                                professorName,
                                billingHours,
                                billingCode,
                                monday,
                                tuesday,
                                wednesday,
                                thursday,
                                friday
                        );


                sectionTextView.setText("Section: " + sectionString);
                sectionTypeEditText.setText(sectionType);
                crnTextView.setText(crn);
                availableEditText.setText(availableSeat);
                maxEditText.setText(maxSeat);
                locationEditText.setText(location);
                timeEditText.setText(times);
                daysEditText.setText(monday + tuesday + wednesday + thursday + friday);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // button event
        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
                section.setAvailableSeat(availableEditText.getText().toString());
                section.setSectionType(sectionTypeEditText.getText().toString());
                section.setMaxSeat(maxEditText.getText().toString());
                section.setLocation(locationEditText.getText().toString());
                section.setTimes(timeEditText.getText().toString());
                String monday = "", tuesday = "", wednesday = "", thursday = "", friday = "";
                String days = daysEditText.getText().toString();


                for (char ch: days.toCharArray()) {
                    if(ch=='M') monday = "M";
                    else if(ch=='T') tuesday = "T";
                    else if(ch=='W') wednesday = "W";
                    else if(ch=='R') thursday= "R";
                    else if(ch=='F') friday= "F";
                }

                section.setMonday(monday);
                section.setTuesday(tuesday);
                section.setWednesday(wednesday);
                section.setThursday(thursday);
                section.setFriday(friday);

                Map<String, Object> map = section.toMap();

                Map<String, Object> childUpdates = new HashMap<>();

                database = database.getRoot();
                childUpdates.put("/" + faculty + "/" + courseName + "/" + sectionInt , map);
                database.updateChildren(childUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"Update Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Error happed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}

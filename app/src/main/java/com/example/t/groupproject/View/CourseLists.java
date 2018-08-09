package com.example.t.groupproject.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.t.groupproject.Model.Course;
import com.example.t.groupproject.R;
import com.example.t.groupproject.ViewAdapter.CourseAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * View that display a list of course
 */
public class CourseLists extends AppCompatActivity implements ValueEventListener{
    private static final String TAG = "CourseLists";

    private DatabaseReference database;
    private List<Course> courseList;
    private String faculty;


    private RecyclerView recyclerView;
    private CourseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_lists);


        // ---------------------------------new edited--------------------
        // Initialize values
        faculty = getIntent().getExtras().getString("FACULTY");
        database = FirebaseDatabase.getInstance().getReference().child(faculty);
        setTitle(faculty.replace("_", " "));


        courseList = new ArrayList<Course>();

        // retrieve data from Database, then show them on textView
        database.addListenerForSingleValueEvent(this);



        //RecyclerView adapter settings
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void displayCourseList(){
        adapter = new CourseAdapter(this, courseList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String courseName, courseLink = "";

        for (DataSnapshot course : dataSnapshot.getChildren()) {
            courseName = course.getKey();
            courseLink = course.child("classlink").getValue().toString();
            courseList.add(new Course(courseName,courseLink));
        }

        displayCourseList();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}






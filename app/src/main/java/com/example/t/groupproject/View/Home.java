package com.example.t.groupproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t.groupproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Main View
 */
public class Home extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseUser user;
    private TextView homeTitle;
    private Button logOutButton, courseTableButton, infoButton, tuitionButton, scheduleButton, myClassButton, addCourseButton;
    private Boolean isAdmin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initialize values and components
        database = FirebaseDatabase.getInstance().getReference().child("UserInfo");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        homeTitle = (TextView) findViewById(R.id.homeTitle);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        courseTableButton = (Button) findViewById(R.id.courseTableButton);
        infoButton = (Button) findViewById(R.id.infoButton);
        tuitionButton = (Button) findViewById(R.id.tuitionButton);
        scheduleButton = (Button) findViewById(R.id.scheduleButton);
        myClassButton = (Button) findViewById(R.id.myClassButton);
        addCourseButton = (Button) findViewById(R.id.addCourseButton);

        // initialize button listener
        logOutButton.setOnClickListener(this);
        courseTableButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);

//        logOutButton.setVisibility(View.INVISIBLE);
        // initialize displayed text
        if (user == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        homeTitle.setText("Welcome " + user.getEmail());


        // detect if is a admin user
        database.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("admin")){
                    isAdmin = true;
                    courseTableButton.setText("Manage Courses");
                    infoButton.setVisibility(View.INVISIBLE);
                    tuitionButton.setVisibility(View.INVISIBLE);
                    scheduleButton.setVisibility(View.INVISIBLE);
                    myClassButton.setVisibility(View.INVISIBLE);
                    addCourseButton.setVisibility(View.INVISIBLE);
                }
                else{
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    // course listener for every click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logOutButton:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.courseTableButton:
//                if(courseTableButton.getText().equals("Manage Courses")){
//                    makeToast(user.getEmail());
//                    startActivity(new Intent(Home.this,ManageCoursesLists.class));
//                    break;
//                }
                startActivity(new Intent(Home.this, RegisterClass.class));
                break;
            case R.id.tuitionButton:
                startActivity(new Intent(Home.this, Tuition.class));
                break;
            case R.id.scheduleButton:
                startActivity(new Intent(Home.this, Tuition.class));
                break;
            case R.id.myClassButton:
                startActivity(new Intent(Home.this, MyClass.class));
                break;
            case R.id.infoButton:
                startActivity(new Intent(Home.this, UserInfo.class));
                break;
            case R.id.addCourse:
                startActivity(new Intent(Home.this, RegisterCourse.class));
                break;
        }
    }

    public void makeToast (String message ){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


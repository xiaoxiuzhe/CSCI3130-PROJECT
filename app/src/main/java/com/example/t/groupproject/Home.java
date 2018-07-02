package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView homeTitle;
    private Button logOutButton,courseTableButton, infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // initialize values and components
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        homeTitle = (TextView) findViewById(R.id.homeTitle);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        courseTableButton = (Button) findViewById(R.id.courseTableButton);
        infoButton = (Button) findViewById(R.id.infoButton);

        // initialize button listener
        logOutButton.setOnClickListener(this);
        courseTableButton.setOnClickListener(this);
        infoButton.setOnClickListener(this);



        // initialize displayed text
        if(user == null){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        homeTitle.setText("Welcome " + user.getEmail());

    }


    // course listener for every click
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logOutButton:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.courseTableButton:
                startActivity(new Intent(Home.this, RegisterClass.class));
                break;
            case R.id.infoButton:
                startActivity(new Intent(Home.this, UserInfo.class));
                break;
        }
    }
}


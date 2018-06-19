package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView homeTitle;
    private Button LogOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        FirebaseUser user = mAuth.getCurrentUser();

        homeTitle = (TextView) findViewById(R.id.homeTitle);
        homeTitle.setText("Welcome " + user.getEmail());

        LogOutButton = (Button) findViewById(R.id.LogOutButton);
        LogOutButton.setOnClickListener(this);

    }


    public void courseTableButton(View view) {
        startActivity(new Intent(Home.this, RegisterClass.class));
    }

    @Override
    public void onClick(View v) {
        if(v == LogOutButton){
            mAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }
}


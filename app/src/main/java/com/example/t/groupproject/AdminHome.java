package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHome extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // initialize values and components
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
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
            case R.id.courseManButton:
                startActivity(new Intent(AdminHome.this, UserMana.class));
                break;
            case R.id.tuitionButton:
                startActivity(new Intent(AdminHome.this, CourseMana.class));
                break;
        }
    }
}

package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginButton(View view) {
        startActivity(new Intent(Login.this, RegisterClass.class));
    }

    public void signUpButton(View view) {
        startActivity(new Intent(Login.this, SignUp.class));
    }
}

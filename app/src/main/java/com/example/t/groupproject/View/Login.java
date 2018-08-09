package com.example.t.groupproject.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t.groupproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * View of login page
 */
public class Login extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {

    private FirebaseAuth mAuth;
    private EditText EmailField, passwordField;
    private TextView textViewSignUp;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize variables
        mAuth = FirebaseAuth.getInstance();

        EmailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        loginButton = (Button) findViewById(R.id.loginButton);

        // initialize button click listeners
        loginButton.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);


        // jump to Home if user has already login
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

    }

    // handle all the clicks
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                userLogIn();
                break;
            case R.id.textViewSignUp:
                startActivity(new Intent(this, SignUp.class));
                break;
        }
    }


    private void userLogIn() {
        String email = EmailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (isValidatedLogin(email, password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, this);
        }
    }

    // once login complete or not
    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        } else {
            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    // validator for emal and password
    private boolean isValidatedLogin(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Pleas enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Pleas enter password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}

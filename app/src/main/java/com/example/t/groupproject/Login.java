package com.example.t.groupproject;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText EmailField, passwordField;
    private TextView textViewSignUp;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null){
            //user is logged in, go to home
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        EmailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    private void userLogIn(){
        String email = EmailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Pleas enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Pleas enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Could not login, incorrect credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){
            userLogIn();
        }
        if(v == textViewSignUp){
            startActivity(new Intent(this, SignUp.class));
        }
    }
}

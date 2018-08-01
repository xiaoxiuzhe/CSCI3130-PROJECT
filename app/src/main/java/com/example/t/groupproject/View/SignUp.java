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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * View of sign up page
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText EmailField, passwordField;
    private TextView textViewLogin;
    private Button registerButton;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        EmailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = EmailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // after password validation, add user to the firebase
        if( password.matches(passwordRegex) && email.matches(emailRegex)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (mAuth.getCurrentUser() != null) {

                                    //init userinfo right after register
                                    database = FirebaseDatabase.getInstance().getReference("UserInfo");
                                    String personID = mAuth.getCurrentUser().getUid();
                                    Contact userinfo = new Contact(personID, "", "", "", "Chinese");
                                    database.child(personID).setValue(userinfo);

                                    //user is logged in, go to UserInfoInit
                                    startActivity(new Intent(getApplicationContext(), UserInfoInit.class));
                                    Toast.makeText(SignUp.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else if(!email.matches(emailRegex)){
            Toast.makeText(SignUp.this, "Wrong email address", Toast.LENGTH_LONG).show();
        }
        else if(!password.matches(passwordRegex)){
            Toast.makeText(SignUp.this, "Password should has 8 digits long, at least 1 uppcase and 1 lower case.", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                registerUser();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, Login.class));
                finish();
        }
    }
}

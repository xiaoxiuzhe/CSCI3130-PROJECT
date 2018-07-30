package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends AppCompatActivity implements View.OnClickListener {


    private Spinner Major;
    private EditText nameField, addressField, phoneField, passwordField;
    private DatabaseReference database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Button SaveButton, changePasswordButton;
    Contact receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        // initialize displayed text
        if (user == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        // Spinner element
        Major = (Spinner) findViewById(R.id.Major);

        // Spinner Drop down elements
        final List<String> Majors = new ArrayList<String>();
        Majors.add("Chinese");
        Majors.add("Computer Science");
        Majors.add("Statistics");

        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Majors);

        // attaching data adapter to spinner
        Major.setAdapter(Adapter);

        SaveButton = (Button) findViewById(R.id.SaveButton);
        changePasswordButton = (Button) findViewById(R.id.changePasswordButton);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        phoneField = (EditText) findViewById(R.id.phone);
        passwordField = (EditText) findViewById(R.id.password);

        // initialize button click listeners
        SaveButton.setOnClickListener(this);
        changePasswordButton.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference("UserInfo");
        ValueEventListener ContactListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Contact userInfo = dataSnapshot.getValue(Contact.class);
                nameField.setText(userInfo.name);
                addressField.setText(userInfo.address);
                phoneField.setText(userInfo.phone);
                Major.setSelection(Majors.indexOf(userInfo.major));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        database.child(user.getUid()).addValueEventListener(ContactListener);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SaveButton:
                submitInfoButton();
                break;
            case R.id.changePasswordButton:
                changePassword();
                break;
        }
    }

    public void submitInfoButton(){

        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String phone = phoneField.getText().toString();
        String major = Major.getSelectedItem().toString();

        String personID = user.getUid();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter your phone", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact userinfo = new Contact(personID, name, address, phone, major);

        database.child(personID).setValue(userinfo);

        startActivity(new Intent(getApplicationContext(), Home.class));;
    }

    public void changePassword (){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if( password.matches(passwordRegex)){
            user.updatePassword(password);
            Toast.makeText(this, "Password changed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), Home.class));;
        }
        else{
            Toast.makeText(this, "Password should has 8 digits long, at least 1 uppcase and 1 lower case.", Toast.LENGTH_LONG).show();
            return;
        }

    }
}

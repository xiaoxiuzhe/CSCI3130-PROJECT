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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserInfoInit extends AppCompatActivity implements View.OnClickListener{

    private Spinner Major;
    private EditText nameField, addressField, phoneField;
    private DatabaseReference database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Button SaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_init);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance().getReference("UserInfo");

        // Spinner element
        Major = (Spinner) findViewById(R.id.Major);

        // Spinner Drop down elements
        List<String> Majors = new ArrayList<String>();
        Majors.add("Chinese");
        Majors.add("Computer Science");
        Majors.add("Statistics");

        // Creating adapter for spinner
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Majors);

        // attaching data adapter to spinner
        Major.setAdapter(Adapter);

        SaveButton = (Button) findViewById(R.id.SaveButton);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        phoneField = (EditText) findViewById(R.id.phone);

        // initialize button click listeners
        SaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SaveButton:
                submitInfoButton();
        }
    }

    public void submitInfoButton() {

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
}

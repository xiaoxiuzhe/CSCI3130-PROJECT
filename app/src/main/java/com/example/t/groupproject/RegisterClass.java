package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterClass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);


        //Dropdown list for term selection
        //https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
        Spinner termSelect = findViewById(R.id.termSpinner);
        Spinner programSelect = findViewById(R.id.programSelect);
        String[] term = new String[]{"2017-2018 Summer", "2018-2019 Fall"};
        String[] program = new String[]{"Science", "Music", "Computer Science"};
        ArrayAdapter<String> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, term);
        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, program);
        termSelect.setAdapter(termAdapter);
        programSelect.setAdapter(programAdapter);
    }


    public void logoutButton(View view) {
        startActivity(new Intent(RegisterClass.this, Login.class));
    }

    public void submitButton(View view) {
        startActivity(new Intent(RegisterClass.this, CourseLists.class));
    }



}

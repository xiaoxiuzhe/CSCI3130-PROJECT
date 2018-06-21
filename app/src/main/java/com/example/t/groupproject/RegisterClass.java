package com.example.t.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner termSelect;
    private Spinner programSelect;
    private Button submitButton;
    private String term;
    private String faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);

        submitButton = findViewById(R.id.submitButton);

        //Dropdown list for term selection
        //https://stackoverflow.com/questions/13377361/how-to-create-a-drop-down-list
        termSelect = findViewById(R.id.termSpinner);
        programSelect = findViewById(R.id.programSpinner);

        // make spinner selectable and save its data
        termSelect.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        programSelect.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        String[] term = new String[]{"2017-2018 Summer", "2018-2019 Fall"};
        String[] program = new String[]{"Computer Science", "Chinese", "Statistics"};

        ArrayAdapter<String> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, term);
        ArrayAdapter<String> programAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, program);
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        termSelect.setAdapter(termAdapter);
        programSelect.setAdapter(programAdapter);


    }


    public void logoutButton(View view) {
        startActivity(new Intent(RegisterClass.this, Login.class));
    }

    public void submitButton(View view) {
        // passing faculty to courselists class
        Intent intent = new Intent(RegisterClass.this, CourseLists.class);

        switch (faculty){
            case "Chinese":
                intent.putExtra("FACULTY", "Chinese"); break;
            case "Statistics":
                intent.putExtra("FACULTY", "Statistics"); break;
            case "Computer Science":
            default:
                intent.putExtra("FACULTY", "Computer_Science");
        }
        startActivity(intent);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.termSpinner:
                term = parent.getSelectedItem().toString();
                Log.w("RegisterClass", term);
                break;
            case R.id.programSpinner:
                faculty = parent.getSelectedItem().toString();
                Log.w("RegisterClass", faculty);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch(parent.getId()){
            case R.id.termSpinner:
                 term = "2017_2018_Summer";
                Log.w("RegisterClass", term);
                break;
            case R.id.programSpinner:
                faculty = "Computer_Science";
                Log.w("RegisterClass", faculty);
                break;
        }
    }
}
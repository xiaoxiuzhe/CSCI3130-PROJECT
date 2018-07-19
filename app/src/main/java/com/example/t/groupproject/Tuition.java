package com.example.t.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tuition extends AppCompatActivity {

    static final double Chin_Billing_Hours = 254.62;
    static final double Chin_Fall = 863.03;
    static final double Csci_Billing_Hours = 288.9;
    static final double Csci_Fall = 824.25;
    static final double Stat_Billing_Hours1 = 276.3;
    static final double Stat_Fall1 = 794.98;
    static final double Stat_Billing_Hours2 = 293.7;
    static final double Stat_Fall2 = 806.39;

    private DatabaseReference database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private double chFee = 0;
    private double csFee = 0;
    private double stFee = 0;
    private double tFee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition);

        final TextView chinFee = (TextView)findViewById(R.id.chineseFee);
        final TextView statFee = (TextView)findViewById(R.id.StatFee);
        final TextView csciFee = (TextView)findViewById(R.id.CSFee);
        final TextView totalFee = (TextView)findViewById(R.id.totalFee);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance().getReference("UserInfo");
        ValueEventListener ContactListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> courses = new ArrayList<String>();
                for (DataSnapshot course : dataSnapshot.getChildren()) {
                    courses.add(course.child("code").getValue().toString());
                }
                if (courses.get(0) != null) {
                    for (int i = 0; i < courses.size(); i++) {
                        String feeCode = courses.get(i);
                        if (feeCode.equals("T210")) {
                            chFee += (Chin_Billing_Hours + Chin_Fall);
                        }
                        else if (feeCode.equals("T410")) {
                            csFee += (Csci_Billing_Hours + Csci_Fall);
                        }
                        else if (feeCode.equals("T610")) {
                            stFee += (Stat_Billing_Hours1 + Stat_Fall1);
                        }
                        else if (feeCode.equals("T620")) {
                            stFee += (Stat_Billing_Hours2 + Stat_Fall2);
                        }
                    }
                }
                tFee = chFee + csFee + stFee;
                chinFee.setText(Double.toString(chFee));
                csciFee.setText(Double.toString(csFee));
                statFee.setText(Double.toString(stFee));
                totalFee.setText(Double.toString(tFee));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        database.child(user.getUid()).child("registered_courses").addValueEventListener(ContactListener);

    }




}

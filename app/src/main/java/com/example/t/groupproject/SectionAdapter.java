package com.example.t.groupproject;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    private Context mCtx;
    private List<Section> sectionList;
    private DatabaseReference database;
    private String courseName, faculty, userUid;


    public SectionAdapter(Context mCtx, List<Section> sectionList, DatabaseReference database, Map<String, String> passInfo) {
        this.mCtx = mCtx;
        this.courseName = passInfo.get("courseName");
        this.faculty = passInfo.get("faculty");
        this.userUid = passInfo.get("userUid");
        this.database = database;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public SectionAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.section_layout, null);

        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SectionAdapter.SectionViewHolder holder, int position) {
        final Section section = sectionList.get(position);

        String sectionTitle = section.getSectionType();
        String professorName = section.getProfessorName();
        String availMax = "Available " + section.getAvailableSeat() + "/" + section.getMaxSeat();
        String waitList;
        if (section.getWaitList().equals(""))
            waitList = "WaitList 0";
        else
            waitList = "WaitList " + section.getWaitList();

        final String sectionCode = "Sec:" + section.getSectionCode() + " CRN:" + section.getCrn();
        String times = section.getMonday()+
                section.getTuesday()+
                section.getWednesday()+
                section.getThursday()+
                section.getFriday()+
                " " +section.getTimes();
        String location = "Location: " + section.getLocation();


        holder.textViewTitle.setText(sectionTitle);
        holder.textViewProfessor.setText(professorName);
        holder.textViewAvailMax.setText(availMax);
        holder.textViewWaitList.setText(waitList);
        holder.textViewSectionCode.setText(sectionCode);
        holder.textViewTimes.setText(times);
        holder.textViewLocation.setText(location);
        holder.toggleButtonRegister.setText("Click to Register");
        holder.toggleButtonRegister.setTextOff("Click to Register");
        holder.toggleButtonRegister.setTextOn("Registered");


        // user clicked toggleButton event listener
        holder.toggleButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.toggleButtonRegister.isChecked()) {
//                    holder.toggleButtonRegister.setTextOn("Registered");

                    String crn = section.getCrn();
                    String sectionId = section.getSectionId();
                    int availableSeat = Integer.parseInt(section.getAvailableSeat()) - 1;
                    int currentSeat = Integer.parseInt(section.getCurrentSeat()) + 1;
                    section.setAvailableSeat(String.valueOf(availableSeat));
                    section.setCurrentSeat(String.valueOf(currentSeat));

                    Map<String, Object> newSectionValues = section.toMap();
                    newSectionValues.put("course_name", courseName);
                    newSectionValues.put("faculty", faculty);
                    Map<String, Object> childUpdates = new HashMap<>();

                    childUpdates.put("/UserInfo/" + userUid + "/registered_courses/" + crn, newSectionValues);
                    childUpdates.put("/" + faculty + "/" + courseName + "/" + sectionId, newSectionValues);

                    database.updateChildren(childUpdates);
                    holder.textViewAvailMax.setText("Available " + section.getAvailableSeat() + "/" + section.getMaxSeat());

                } else {
                    String crn = section.getCrn();
                    String sectionId = section.getSectionId();
                    int availableSeat = Integer.parseInt(section.getAvailableSeat()) + 1;
                    int currentSeat = Integer.parseInt(section.getCurrentSeat()) - 1;
                    section.setAvailableSeat(String.valueOf(availableSeat));
                    section.setCurrentSeat(String.valueOf(currentSeat));

                    Map<String, Object> newSectionValues = section.toMap();
                    newSectionValues.put("course_name", courseName);
                    newSectionValues.put("faculty", faculty);
                    Map<String, Object> childUpdates = new HashMap<>();

                    childUpdates.put("/UserInfo/" + userUid + "/registered_courses/" + crn, null);
                    childUpdates.put("/" + faculty + "/" + courseName + "/" + sectionId, newSectionValues);

                    database.updateChildren(childUpdates);
                    holder.textViewAvailMax.setText("Available " + section.getAvailableSeat() + "/" + section.getMaxSeat());

                }
            }
        });


        // //check if the user registered the course or not
        database.child("UserInfo").child(userUid).child("registered_courses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot registeredSection : dataSnapshot.getChildren()) {
                            String crn = String.valueOf(registeredSection.getKey());
                            Log.w("SectionAdapter", crn + " - " + section.getCrn());

                            if (crn.equals(section.getCrn()))
                                holder.toggleButtonRegister.setChecked(true);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewProfessor;
        TextView textViewAvailMax;
        TextView textViewWaitList;
        TextView textViewSectionCode;
        TextView textViewTimes;
        TextView textViewLocation;
        ToggleButton toggleButtonRegister;


        public SectionViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewProfessor = itemView.findViewById(R.id.textViewProfessor);
            textViewAvailMax = itemView.findViewById(R.id.textViewAvailMax);
            textViewWaitList = itemView.findViewById(R.id.textViewWaitList);
            textViewSectionCode = itemView.findViewById(R.id.textViewSectionCode);
            textViewTimes = itemView.findViewById(R.id.textViewTimes);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            toggleButtonRegister = itemView.findViewById(R.id.toggleButtonRegister);
        }
    }
}

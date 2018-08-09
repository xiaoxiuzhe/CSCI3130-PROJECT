package com.example.t.groupproject.View;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Not finished yet
 */

public class Contact implements Serializable {

    public String uid;
    public String name, address, phone, major;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String name, String address, String phone, String major){
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.major = major;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("address", address);
        result.put("phone", phone);
        result.put("major", major);

        return result;
    }
}

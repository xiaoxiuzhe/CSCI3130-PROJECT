package com.example.t.groupproject;

public class User {
    private String uid;
    private String email;
    private String name;
    private String address;
    private String major;
    private String phone;

    public User(String uid, String email, String name, String address, String major, String phone) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.address = address;
        this.major = major;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

package com.e.loginregister.Model;

public class Book {

    private String fullname;
    private String date;
    private String time;
    private String phonenumber;
    private String admins;
    private String address;

    public Book(String fullname, String date, String time, String phonenumber, String admins, String address) {
        this.fullname = fullname;
        this.date = date;
        this.time = time;
        this.phonenumber = phonenumber;
        this.admins = admins;
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

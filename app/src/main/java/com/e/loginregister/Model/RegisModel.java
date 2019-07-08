package com.e.loginregister.Model;

public class RegisModel {
    private String _id, fullname, email, password, phonenumber, address, usertype;


    public RegisModel(String _id, String fullname, String email, String password, String phonenumber, String address, String usertype) {
        this._id = _id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.address = address;
        this.usertype = usertype;
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }


}

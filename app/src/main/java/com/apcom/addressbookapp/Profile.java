package com.apcom.addressbookapp;

/**
 * Created by apcom on 20.10.2015.
 */
public class Profile {
    private long id;
    private String first_name;
    private String last_name;
    private String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Это нужно для формирования ArrayAdapter
    @Override
    public String toString() {
        return getFullName();
    }
}

package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */


import android.arch.persistence.room.ColumnInfo;

public class Address {
    private String street;
    private String city;
    private String state;
    @ColumnInfo(name = "post_code")
    private String postCode;

    @Override
    public boolean equals(Object obj) {
        Address address = (Address) obj;
        if (!this.street.equals(address.street)) {
            return false;
        }
        if (!this.city.equals(address.city)) {
            return false;
        }
        if (!this.state.equals(address.state)) {
            return false;
        }
        if (!this.postCode.equals(address.postCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return street + "," + city + "," + state + " " + postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

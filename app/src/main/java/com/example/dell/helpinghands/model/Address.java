package com.example.dell.helpinghands.model;

/**
 * Created by DELL on 07-04-2018.
 */

public class Address {

    public String adrsline1;
    public String adrsline2;
    public String adrsline3;
    public String city;
    public String state;
    public String zipcode;

    public Address(){

    }

    public Address(String adrsline1, String adrsline2, String adrsline3, String city, String state, String zipcode) {
        this.adrsline1 = adrsline1;
        this.adrsline2 = adrsline2;
        this.adrsline3 = adrsline3;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
}

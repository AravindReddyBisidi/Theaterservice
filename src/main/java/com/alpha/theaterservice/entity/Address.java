package com.alpha.theaterservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;

    private String state;

    private String city;

    // Default Constructor
    public Address() {
    }

    // Parameterized Constructor
    public Address( String country, String state, String city) {
       
        this.country = country;
        this.state = state;
        this.city = city;
    }

    // Getters and Setters

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
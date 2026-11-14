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
    
    private String distric;
    private int pincode;
    
    public Address( String country, String state, String city, String distric, int pincode) {
		super();
		
		this.country = country;
		this.state = state;
		this.city = city;
		this.distric = distric;
		this.pincode = pincode;
	}

	public Address() {
		super();
	}

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

	public String getDistric() {
		return distric;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

    
    
   
}
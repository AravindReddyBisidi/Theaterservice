package com.alpha.theaterservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    private String city;

    private long contact;

    private String mailid;

    private int totalscreens;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany
    @JsonIgnore
    private List<Show> allplayingshows;

    // Default Constructor
    public Theater() {
    }

    // Parameterized Constructor
    public Theater( String name, String description,
                   String city, long contact, String mailid,
                   int totalscreens, String status, Address address,
                   List<Show> allplayingshows) {

        
        this.name = name;
        this.description = description;
        this.city = city;
        this.contact = contact;
        this.mailid = mailid;
        this.totalscreens = totalscreens;
        this.status = status;
        this.address = address;
        this.allplayingshows = allplayingshows;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public int getTotalscreens() {
        return totalscreens;
    }

    public void setTotalscreens(int totalscreens) {
        this.totalscreens = totalscreens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Show> getAllplayingshows() {
        return allplayingshows;
    }

    public void setAllplayingshows(List<Show> allplayingshows) {
        this.allplayingshows = allplayingshows;
    }

	@Override
	public String toString() {
		return "Theater [id=" + id + ", name=" + name + ", description=" + description + ", city=" + city
				+ ", contact=" + contact + ", mailid=" + mailid + ", totalscreens=" + totalscreens
				+ ", status=" + status + ", address=" + address + ", allplayingshows=" + allplayingshows + "]";
	}
    
    
}
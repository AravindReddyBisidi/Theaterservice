package com.alpha.theaterservice.dto;

import com.alpha.theaterservice.entity.Address;

public class UpdateTheaterDto {

    private String name;
    private String description;
    private String city;
    private long contact;
    private String mailid;
    private int totalscreens;
    private String status;
    private Address address;

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
}
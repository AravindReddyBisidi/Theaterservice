package com.alpha.theaterservice.dto;

import com.alpha.theaterservice.entity.Address;

public class CreateTheaterDto {

    private String name;

    private String description;

    private long contact;
    private String city;

    private String mail;

    private int totalScreen;

    private Address address;

	public CreateTheaterDto(String name, String description, long contact, String city, String mail, int totalScreen,
			Address address) {
		super();
		this.name = name;
		this.description = description;
		this.contact = contact;
		this.city = city;
		this.mail = mail;
		this.totalScreen = totalScreen;
		this.address = address;
	}

	public CreateTheaterDto() {
		super();
	}

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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getTotalScreen() {
		return totalScreen;
	}

	public void setTotalScreen(int totalScreen) {
		this.totalScreen = totalScreen;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CreateTheaterDto [name=" + name + ", description=" + description + ", contact=" + contact + ", city="
				+ city + ", mail=" + mail + ", totalScreen=" + totalScreen + ", address=" + address + "]";
	}

    
}
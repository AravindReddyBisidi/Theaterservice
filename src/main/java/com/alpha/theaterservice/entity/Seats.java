package com.alpha.theaterservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seats {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String seatno;
	private String category;
	private String status;
	private double price;
	@ManyToOne(cascade = CascadeType.ALL)
	private Show show;

	public Seats( String seatno, String category, String status, Show show,double price) {
		super();
		
		this.seatno = seatno;
		this.category = category;
		this.status = status;
		this.show = show;
		this.price=price;
	}

	public Seats() {
		super();
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatno() {
		return seatno;
	}

	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Seats [id=" + id + ", seatno=" + seatno + ", category=" + category + ", status=" + status + ", price="
				+ price + ", show=" + show + "]";
	}
}

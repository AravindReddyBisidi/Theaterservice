package com.alpha.theaterservice.dto;


public class SeatResponseDto {

    private long id;
    private String seatno;
    private String category;
    private String status;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
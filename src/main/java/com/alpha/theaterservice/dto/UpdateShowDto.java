package com.alpha.theaterservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateShowDto {

    private long id;

    private LocalDate showDate;

    private LocalTime showTime;

    private int screenno;
    
    
    

    public UpdateShowDto(long id, LocalDate showDate, LocalTime showTime, int screenno) {
    	super();
    	this.id = id;
    	this.showDate = showDate;
    	this.showTime = showTime;
    	this.screenno = screenno;
    }
   
    
    public UpdateShowDto() {
		super();
	}


	// Getters and Setters
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public int getScreenno() {
        return screenno;
    }

    public void setScreenno(int screenno) {
        this.screenno = screenno;
    }


	@Override
	public String toString() {
		return "UpdateShowDto [id=" + id + ", showDate=" + showDate + ", showTime=" + showTime + ", screenno="
				+ screenno + "]";
	}
    
    
}
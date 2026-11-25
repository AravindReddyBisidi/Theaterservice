package com.alpha.theaterservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateShowDto {

    private String title;

    private String description;

    private double duration;

    private String language;

    private int screenno;

    private LocalDate showDate;

    private LocalTime showTime;

    private long movieId;

//    private long theaterId;
    
    private int capacitySeats;
    


    public CreateShowDto(String title, String description, double duration, String language, int screenno,
			LocalDate showDate, LocalTime showTime, long movieId,int capacitySeats) {
		super();
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.screenno = screenno;
		this.showDate = showDate;
		this.showTime = showTime;
		this.movieId = movieId;
//		this.theaterId = theaterId;
		this.capacitySeats=capacitySeats;
	}
    
    

    public CreateShowDto() {
		super();
	}



	// Getters and Setters
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getScreenno() {
        return screenno;
    }

    public void setScreenno(int screenno) {
        this.screenno = screenno;
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

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

//    public long getTheaterId() {
//        return theaterId;
//    }
//
//    public void setTheaterId(long theaterId) {
//        this.theaterId = theaterId;
//    }
    
	public int getCapacitySeats() {
		return capacitySeats;
	}

	public void setCapacitySeats(int capacitySeats) {
		this.capacitySeats = capacitySeats;
	}
	
    @Override
	public String toString() {
		return "CreateShowDto [title=" + title + ", description=" + description + ", duration=" + duration
				+ ", language=" + language + ", screenno=" + screenno + ", showDate=" + showDate + ", showTime="
				+ showTime + ", movieId=" + movieId + ", capacitySeats=" + capacitySeats
				+ "]";
	}

}
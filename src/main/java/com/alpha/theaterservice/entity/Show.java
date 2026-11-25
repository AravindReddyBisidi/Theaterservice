package com.alpha.theaterservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private double duration;

    private String language;

    private int screenno;

    private LocalDate showDate;

    private LocalTime showTime;
    
    
    @Column(name = "capacityseats")
    private int capacitySeats;
    
    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theater theater;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seats> seats;
    
    

    // Default Constructor
    public Show() {
    }

    // Parameterized Constructor
    

    // Getters and Setters

    public long getId() {
        return id;
    }

    public Show(long id, String title, String description, double duration, String language, int screenno,
			LocalDate showDate, LocalTime showTime, int capacitySeats, Movie movie, Theater theater,
			List<Seats> seats) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.screenno = screenno;
		this.showDate = showDate;
		this.showTime = showTime;
		this.capacitySeats = capacitySeats;
		this.movie = movie;
		this.theater = theater;
		this.seats = seats;
	}

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

	public int getCapacitySeats() {
		return capacitySeats;
	}

	public void setCapacitySeats(int capacitySeats) {
		this.capacitySeats = capacitySeats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Seats> getSeats() {
		return seats;
	}

	public void setSeats(List<Seats> seats) {
		this.seats = seats;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
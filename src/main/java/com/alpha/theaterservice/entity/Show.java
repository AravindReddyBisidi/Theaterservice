package com.alpha.theaterservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Theater theater;

    // Default Constructor
    public Show() {
    }

    // Parameterized Constructor
    public Show( String title, String description, double duration,
                String language, int screenno, Movie movie, Theater theater) {
       
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.language = language;
        this.screenno = screenno;
        this.movie = movie;
        this.theater = theater;
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
}
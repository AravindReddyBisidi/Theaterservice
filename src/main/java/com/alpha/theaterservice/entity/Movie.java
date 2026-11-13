package com.alpha.theaterservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private String caste;

    private double duration;

    private String language;

    // Default Constructor
    public Movie() {
    }

    // Parameterized Constructor
    public Movie( String title, String description, String caste,
                 double duration, String language) {

       
        this.title = title;
        this.description = description;
        this.caste = caste;
        this.duration = duration;
        this.language = language;
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

    public String getcaste() {
        return caste;
    }

    public void setcaste(String caste) {
        this.caste = caste;
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
}
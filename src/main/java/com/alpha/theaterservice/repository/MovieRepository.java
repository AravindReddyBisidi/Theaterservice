package com.alpha.theaterservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterservice.dto.UpdateMovieDto;
import com.alpha.theaterservice.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  public Optional<Movie> findByTitleIgnoreCase(String title);

  public Movie findByTitleIgnoreCase(UpdateMovieDto updateMovieDto);

  public List<Movie> findByTitleContainingIgnoreCase(String title);

  public List<Movie> findByTitleContainsIgnoreCase(String title);
}
package com.alpha.theaterservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.theaterservice.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  public Optional<Movie> findByTitleIgnoreCase(String title);
}
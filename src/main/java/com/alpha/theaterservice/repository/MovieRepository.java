package com.alpha.theaterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.theaterservice.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
package com.alpha.theaterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterservice.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
package com.alpha.theaterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.theaterservice.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

}
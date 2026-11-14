package com.alpha.theaterservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterservice.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

	public Optional<Theater> findByContact(long contact);
	
//	public Optional<Theater> findByMailId(String mailid);
}
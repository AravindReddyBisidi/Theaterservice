package com.alpha.theaterservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterservice.entity.Seats;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Integer> {

 public 	List<Seats>  findByShow_IdAndStatus(long showid, String status);

 public Optional<List<Seats>> findByShow_Id(long showid);



 public List<Seats> findByShow_IdAndIdIn(long showId, List<Integer> seatIds);

}

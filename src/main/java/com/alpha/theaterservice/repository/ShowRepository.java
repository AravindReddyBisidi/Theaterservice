package com.alpha.theaterservice.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alpha.theaterservice.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

	public  Optional<Show> findByTitle(String title);

//	public Optional<Show> findbyShowDateAndShowTimeAndScreennoAndTheater(LocalDate showDate, LocalTime showTime,
//			int screenno, long theaterId);

//	public Optional<Show> findByShowDateAndShowTimeAndScreennoAndTheater(LocalDate showDate, LocalTime showTime,
//			int screenno, long theaterId);

	public Optional<Show> findByShowDateAndShowTimeAndScreennoAndTheater_Id(LocalDate showDate, LocalTime showTime,
			int screenno, long theaterId);

	
//	public boolean findByShowDateAndShowTimeAndScreenno(LocalDate showDate, LocalTime showTime, int screenno);

//	public boolean existsByShowDateAndShowTimeAndScreenno(LocalDate showDate, LocalTime showTime, int screenno);

	public boolean existsByShowDateAndShowTimeAndScreennoAndIdNot(LocalDate showDate, LocalTime showTime, int screenno,
			long id);
	
	
}
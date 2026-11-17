package com.alpha.theaterservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.CreateShowDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.exception.MovieNotFoundWithIdExpection;
import com.alpha.theaterservice.exception.ShowAlreadyExistException;
import com.alpha.theaterservice.exception.TheaterNOtFoundWithIdException;
import com.alpha.theaterservice.repository.MovieRepository;
import com.alpha.theaterservice.repository.ShowRepository;
import com.alpha.theaterservice.repository.TheaterRepository;

@Service
public class ShowService {
	
	@Autowired
	ShowRepository showrepository;
	
	@Autowired
	TheaterRepository theaterrepository;
	
	@Autowired
	MovieRepository movierepository;

	public  ResponseEntity<ResponseStructure<Show>> createShow(CreateShowDto createShowDto) {
		Optional<Theater> theateroptional=theaterrepository.findById(createShowDto.getTheaterId());
		if(theateroptional.isEmpty()) {
			throw new TheaterNOtFoundWithIdException();
		}
		Theater theater=theateroptional.get();
		
		Optional<Movie> movieOptional=movierepository.findById(createShowDto.getMovieId());
		if(movieOptional.isEmpty()) {
			throw new MovieNotFoundWithIdExpection();
		}
		Movie movie=movieOptional.get();
		
		Optional<Show> showOptional=showrepository.findByShowDateAndShowTimeAndScreennoAndTheater_Id(createShowDto.getShowDate(),createShowDto.getShowTime(),createShowDto.getScreenno(),createShowDto.getTheaterId());
		if(showOptional.isPresent()) {
			throw new ShowAlreadyExistException();
		}
		Show newshow=new Show();
		newshow.setTitle(createShowDto.getTitle());
		newshow.setTheater(theater);
		newshow.setDescription(createShowDto.getDescription());
		newshow.setDuration(createShowDto.getDuration());
		newshow.setLanguage(createShowDto.getLanguage());
		newshow.setMovie(movie);
		newshow.setScreenno(createShowDto.getScreenno());
		newshow.setShowDate(createShowDto.getShowDate());
		newshow.setShowTime(createShowDto.getShowTime());
		showrepository.save(newshow);
		theater.getAllplayingshows().add(newshow);
		theaterrepository.save(theater);
		movierepository.save(movie);
		ResponseStructure<Show>responseStructure=new ResponseStructure<Show>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Show created ");
		responseStructure.setData(newshow);
		
		return new ResponseEntity<ResponseStructure<Show>>(responseStructure,HttpStatus.CREATED);
		
	}

}

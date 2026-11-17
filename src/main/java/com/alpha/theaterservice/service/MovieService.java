package com.alpha.theaterservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.exception.MovieAlreadyExistWithTitelException;
import com.alpha.theaterservice.exception.MovieNotFoundWithIdExpection;
import com.alpha.theaterservice.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movierepository;

	public ResponseEntity<ResponseStructure<Movie>> createMovie(CreateMovieDto createmoviedto) {
		Optional<Movie> optionalmovie=movierepository.findByTitleIgnoreCase(createmoviedto.getTitle());
		if(optionalmovie.isPresent()) {
			throw new MovieAlreadyExistWithTitelException();
		}
		Movie newmovie=new Movie();
		newmovie.setTitle(createmoviedto.getTitle());
		newmovie.setcaste(createmoviedto.getCaste());
		newmovie.setDescription(createmoviedto.getDescription());
		newmovie.setLanguage(createmoviedto.getLanguage());
		newmovie.setDuration(createmoviedto.getDuration());
		
		Movie savemovie=movierepository.save(newmovie);
		
		ResponseStructure<Movie> responseStructure=new ResponseStructure<Movie>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Movie succefully register");
		responseStructure.setData(savemovie);
		
		return new ResponseEntity<ResponseStructure<Movie>>(responseStructure,HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<Movie>> findMovie(long id) {
		Movie getmovie=movierepository.findById(id).orElseThrow(()->new MovieNotFoundWithIdExpection());
		ResponseStructure<Movie> responseStructure=new ResponseStructure<Movie>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("movie found");
		responseStructure.setData(getmovie);
		return new ResponseEntity<ResponseStructure<Movie>>(responseStructure,HttpStatus.FOUND);
		
	}

	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(long id) {
		Movie deletealmovie=movierepository.findById(id).orElseThrow(()->new MovieNotFoundWithIdExpection());
		movierepository.delete(deletealmovie);
		ResponseStructure<Movie> responseStructure=new ResponseStructure<Movie>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("movie found");
		responseStructure.setData(deletealmovie);
		return new ResponseEntity<ResponseStructure<Movie>>(responseStructure,HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovies() {
		List<Movie> movie=movierepository.findAll();
		ResponseStructure<List<Movie>> responseStructure=new ResponseStructure<List<Movie>>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("All movies get successfully");
		responseStructure.setData(movie);
		
		return new ResponseEntity<ResponseStructure<List<Movie>>>(responseStructure,HttpStatus.FOUND);
	}

}

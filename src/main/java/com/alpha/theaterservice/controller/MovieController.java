package com.alpha.theaterservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	MovieService movieservice;

	@PostMapping("/movie/createmovie")
	public ResponseEntity<ResponseStructure<Movie>> createMovie(@RequestBody CreateMovieDto createmoviedto) {
		return movieservice.createMovie(createmoviedto);
	}
	
	@GetMapping("/findmovie")
	public ResponseEntity<ResponseStructure<Movie>> findMovie(@RequestParam long id) {
		System.out.println("finding movie by id: "+id);
		return movieservice.findMovie(id);
	}
	
	@DeleteMapping("/deletemovie")
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(long id) {
		System.out.println("Deleting movie by id:"+id);
		return movieservice.deleteMovie(id);
	}
	
	@GetMapping("/movie/getallmovies")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovies() {
		System.out.println("getting all movies");
		return movieservice.getAllMovies();
		
	}
}

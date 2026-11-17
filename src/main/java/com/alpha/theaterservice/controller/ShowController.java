package com.alpha.theaterservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.CreateShowDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.service.ShowService;

@RestController
public class ShowController {
	
	@Autowired
	ShowService showservice;

	@PostMapping("/createshow")
	public ResponseEntity<ResponseStructure<Show>> createShow(@RequestBody CreateShowDto createShowDto) {
		System.out.println(createShowDto);
		return showservice.createShow(createShowDto);
	}
	
}

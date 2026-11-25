package com.alpha.theaterservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterservice.dto.BookSeatsRequestDto;
import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.CreateShowDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.dto.SeatResponseDto;
import com.alpha.theaterservice.entity.Seats;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.service.ShowService;

@RestController
public class ShowController {
	
	@Autowired
	ShowService showservice;
//-------------------------------------------------------------------------------
	@PostMapping("/createshow")
	public Object createShow(@RequestBody CreateShowDto createShowDto,@RequestParam long theatreid) {
		System.out.println(createShowDto);
		return showservice. createshow(createShowDto,theatreid);
	}
//--------------------------------------------------------------------------------------
	@GetMapping("/selectseats")
	public ResponseStructure<List<SeatResponseDto>> getAvailableSeats(@RequestParam long id) {
		return showservice.getAvailableSeats(id);
	}
//-------------------------------------------------------------------------------------------
	@PostMapping("/bookseat")
	public ResponseStructure<List<SeatResponseDto>> bookseats(@RequestBody BookSeatsRequestDto bookSeatsRequestDto) {
		return showservice.bookseats(bookSeatsRequestDto);
	}
}

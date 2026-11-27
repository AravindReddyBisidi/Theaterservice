package com.alpha.theaterservice.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterservice.dto.CreateTheaterDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.dto.UpdateShowDto;
import com.alpha.theaterservice.dto.UpdateTheaterDto;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.service.TheaterService;

@RestController
public class TheaterController {
	
	@Autowired
	TheaterService theaterservice;
//-----------------------------------------------------------------------------------------------
	@PostMapping("/theater/createaccount")
	public ResponseEntity<ResponseStructure<Theater>> createTheaterAccount(@RequestBody CreateTheaterDto createTheaterDto) {
		
		return theaterservice.createTheaterAccount(createTheaterDto);
	}
//-------------------------------------------------------------------------------------------------	
	@GetMapping("/findtheater")
	public ResponseEntity<ResponseStructure<Theater>> findtheater(@RequestParam long id) {
		
		return theaterservice.findtheater(id);
	}
//------------------------------------------------------------------------------	
	@DeleteMapping("/detetetheater")
	public ResponseEntity<ResponseStructure<Theater>> DeleteTheater(@RequestParam long id) {
		return theaterservice.DeleteTheater(id);
	}
//------------------------------------------------------------------------------------------------
	@GetMapping("/theater/findallplayingshows")
	public List<Show> findAllPlayingShow(@RequestParam long id) {
		return theaterservice.findAllPlayingShow(id);
	}
//----------------------------------------------------------------------------------------------	
	@GetMapping("/theater/findshow")
	public ResponseEntity<ResponseStructure<Show>> findShow(@RequestParam long id) {
		System.out.println("finding show by id: "+id);
		return theaterservice.findShow(id);
	}
//--------------------------------------------------------------------------------------------	
	@PatchMapping("/theater/updateshow")
	public ResponseEntity<ResponseStructure<Show>> updateShow(@RequestBody UpdateShowDto updateShowDto) {
		return theaterservice.updateShow(updateShowDto);
	}
//---------------------------------------------------------------------------------------------------	
	@GetMapping("/findmovie")
	public ResponseEntity<ResponseStructure<Movie>> findmovie(@RequestParam String title) {
		System.out.println("finding movie by title: "+title);
		return theaterservice.findmovie(title);
	}
//------------------------------------------------------------------------------------	
	@GetMapping("/buyticket")
	public ResponseStructure<Optional<List<Show>>> findTheaterByMovie(@RequestParam String title,@RequestParam String language, @RequestParam LocalDate showdate) {
		return theaterservice.findingTheatersByMovieName(title,language,showdate);
	} 
 
	@PutMapping("/theater/updateaccount")
	public ResponseStructure<Theater> updateTheaterAccount(@RequestParam long id,@RequestBody UpdateTheaterDto updateTheaterDto) {
		return theaterservice.updateTheaterAccount(id,updateTheaterDto);
	}
//----------------------------------------------------------------------------------------------------
	@DeleteMapping("/theater/show/delete")
	public ResponseStructure<Show> deleteshow(@RequestParam long theaterid,@RequestParam long showid) {
		
	return	theaterservice.deleteshow(theaterid,showid);
	}
//-------------------------------------------------------------------------------------------------------
	@GetMapping("/theater/show/findbydate")
	public ResponseStructure<List<Show>> findByshowWithDate(@RequestParam long theaterid ,@RequestParam LocalDate showdate) {
		return  theaterservice.findByshowWithDate(theaterid,showdate);
	}
//-------------------------------------------------------------------------------------------------------------
	@GetMapping("/theater/show/findbymovie")
	public ResponseStructure<List<Show>> findShowByTheaterId(@RequestParam long theaterid) {
		return theaterservice.findShowByTheaterId(theaterid); 
	}
//----------------------------------------------------------------------------------------------------------
	@GetMapping("/theater/show/findbyscreenno")
  public ResponseStructure<List<Show>> findByScreenno(@RequestParam long theaterid,int screenno ) {
	return theaterservice.findByScreenno(theaterid,screenno);  
  }

	@GetMapping("/theater/show/getshowdetails")
	public ResponseStructure<Show> getShowDetails(@RequestParam long theaterid,@RequestParam long showid) {
		return  theaterservice.getShowDetails(theaterid,showid);
	}
}

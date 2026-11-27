package com.alpha.theaterservice.service;


import java.security.Key;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterservice.dto.BookSeatsRequestDto;
import com.alpha.theaterservice.dto.CreateMovieDto;
import com.alpha.theaterservice.dto.CreateShowDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.dto.SeatResponseDto;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.entity.Seats;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.exception.MovieNotFoundWithIdExpection;
import com.alpha.theaterservice.exception.SeatsAlreadyBookedException;
import com.alpha.theaterservice.exception.SeatsNotFoundException;
import com.alpha.theaterservice.exception.ShowAlreadyExistException;
import com.alpha.theaterservice.exception.ShowNotFoundByIdException;
import com.alpha.theaterservice.exception.TheaterNOtFoundWithIdException;
import com.alpha.theaterservice.repository.MovieRepository;
import com.alpha.theaterservice.repository.SeatRepository;
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
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	
//----------------------------------------------------------------------------------------------	
public Object createshow(CreateShowDto show,long theatreid) {
		
		Theater theatre = theaterrepository.findById(theatreid).orElseThrow(() -> new TheaterNOtFoundWithIdException());
		boolean exist = showrepository.existsByShowDateAndShowTimeAndScreenno(show.getShowDate(), show.getShowTime(), show.getScreenno());
		if(exist) {
			throw new ShowAlreadyExistException();
		}
		Show newshow = new Show();
		newshow.setTitle(show.getTitle());
		newshow.setDescription(show.getDescription());
		newshow.setShowDate(show.getShowDate());
		newshow.setShowTime(show.getShowTime());
		newshow.setDuration(show.getDuration());
		newshow.setScreenno(show.getScreenno());
		newshow.setLanguage(show.getLanguage());
		newshow.setCapacitySeats(show.getCapacitySeats());
		
		Movie movie = movierepository.findById(show.getMovieId()).orElseThrow(() -> new MovieNotFoundWithIdExpection());
		newshow.setMovie(movie);
		newshow.setTheater(theatre);
		
		showrepository.save(newshow);
		
		//   Arranging Seats 
		
		 int totalSeats = show.getCapacitySeats();
		    int seatsPerRow = 10;

		    char row = 'A';

		    List<Seats> seats = new ArrayList<>();
		    

		    int totalRows = (int) Math.ceil((double) totalSeats / seatsPerRow);

		    for (int i = 1; i <= totalRows; i++) {

		        String category;
		        double price;

		        if (i <= 3) {
		            category = "SILVER";
		            price=150.0;
		        }
		        else if (i <= 6) { 
		            category = "GOLD";
		            price=200.0;
		        }
		        else {
		            category = "PLATINUM";
		            price=250.0;
		        }

		        for (int j = 1; j <= seatsPerRow && seats.size() < totalSeats; j++) {

		            int seatNumber = ((i - 1) % 3) * seatsPerRow + j;

		            Seats seat = new Seats();

		            seat.setSeatno(row + "" + seatNumber);
		            seat.setCategory(category);
		            seat.setPrice(price);
		            seat.setStatus("AVAILABLE");
		            seat.setShow(newshow);
		            

		            seats.add(seat);
		        }

		        row++;
		    }

		    seatRepository.saveAll(seats);
		    newshow.setSeats(seats);
		theatre.getAllplayingshows().add(newshow);
		theaterrepository.save(theatre);
		movierepository.save(movie);
		ResponseStructure<Show> rs = new ResponseStructure<Show>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setMessage("Show Created Successfully 👍");
		rs.setData(newshow);
		return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.CREATED);
	}
//------------------------------------------------------------------------------------------
public ResponseStructure<List<SeatResponseDto>> getAvailableSeats(long showId) {
    List<Seats> seats = seatRepository.findByShow_IdAndStatus(showId, "AVAILABLE");
    List<SeatResponseDto> availableSeats = new ArrayList<>();
    for (Seats seat : seats) {
        String key = "lockseat:" + showId + ":" + seat.getSeatno();
        Boolean isLocked = redisTemplate.hasKey(key);
        
        if (!Boolean.TRUE.equals(isLocked)) {

            SeatResponseDto dto = new SeatResponseDto();

            dto.setId(seat.getId());
            dto.setSeatno(seat.getSeatno());
            dto.setCategory(seat.getCategory());
            dto.setPrice(seat.getPrice());
            dto.setStatus("AVAILABLE");

            availableSeats.add(dto);
        }
    }
    ResponseStructure<List<SeatResponseDto>> response = new ResponseStructure<>();
    response.setStatuscode(HttpStatus.OK.value());
    response.setMessage("Available seats fetched successfully");
    response.setData(availableSeats);

    return response;
}
//-------------------------------------------------------------------------------
public ResponseStructure<List<SeatResponseDto>> bookseats(BookSeatsRequestDto bookSeatsRequestDto) {
    Show getshow = showrepository.findById(bookSeatsRequestDto.getShowId()).orElseThrow(() -> new ShowNotFoundByIdException());
    List<Seats> getseat = seatRepository.findByShow_IdAndIdIn(bookSeatsRequestDto.getShowId(),bookSeatsRequestDto.getSeatIds());
    if (getseat.size() != bookSeatsRequestDto.getSeatIds().size()){
        throw new SeatsNotFoundException();
    }
    List<SeatResponseDto> responseList = new ArrayList<>();
    for (Seats seat : getseat) {
        if (!"AVAILABLE".equalsIgnoreCase(seat.getStatus())) {
            throw new SeatsAlreadyBookedException();
        }
        String key = "lockseat:" + bookSeatsRequestDto.getShowId()+ ":" + seat.getSeatno();
        Boolean locked = redisTemplate.opsForValue().setIfAbsent(key,String.valueOf(bookSeatsRequestDto.getCustomerId()),Duration.ofSeconds(300));
        if (!Boolean.TRUE.equals(locked)) {
            throw new SeatsAlreadyBookedException();
        }
        SeatResponseDto dto = new SeatResponseDto();
        dto.setId(seat.getId());
        dto.setSeatno(seat.getSeatno());
        dto.setCategory(seat.getCategory());
        dto.setPrice(seat.getPrice());
        dto.setStatus("FREEZE");
        responseList.add(dto);
    }
    ResponseStructure<List<SeatResponseDto>> responseStructure =new ResponseStructure<>();
    responseStructure.setStatuscode(HttpStatus.OK.value());
    responseStructure.setMessage("Seats FREEZE successfully");
    responseStructure.setData(responseList);
    return responseStructure;
}


}
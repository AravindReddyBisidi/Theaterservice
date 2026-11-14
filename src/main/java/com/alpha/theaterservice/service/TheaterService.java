package com.alpha.theaterservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterservice.dto.CreateTheaterDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.exception.TheaterAlreadyExistWithContactException;
import com.alpha.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	TheaterRepository theaterrepository;

	
	public ResponseEntity<ResponseStructure<Theater>> createTheaterAccount(CreateTheaterDto createTheaterDto) {
		Optional<Theater> theateroptional =theaterrepository.findByContact(createTheaterDto.getContact());
		if(theateroptional.isPresent()) {
			throw new TheaterAlreadyExistWithContactException();
		}
		Theater newTheater=new Theater();
		newTheater.setName(createTheaterDto.getName());
		newTheater.setAddress(createTheaterDto.getAddress());
		newTheater.setCity(createTheaterDto.getCity());
		newTheater.setContact(createTheaterDto.getContact());
		newTheater.setDescription(createTheaterDto.getDescription());
		newTheater.setTotalscreens(createTheaterDto.getTotalScreen());
		newTheater.setStatus("Closed");
		newTheater.setMailid(createTheaterDto.getMail());
		Theater savedTheater =theaterrepository.save(newTheater);
		ResponseStructure<Theater> responseStructure=new ResponseStructure<Theater>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("New Theater Created Sucessfully");
		responseStructure.setData(savedTheater);
		
		return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.CREATED);
		
		
	}
	
    
}

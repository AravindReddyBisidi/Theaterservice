package com.alpha.theaterservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.theaterservice.dto.CreateTheaterDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.dto.UpdateShowDto;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.exception.ShowAlreadyExistException;
import com.alpha.theaterservice.exception.TheaterAlreadyExistWithContactException;
import com.alpha.theaterservice.exception.TheaterNOtFoundWithIdException;
import com.alpha.theaterservice.exception.showNotFoundByIdException;
import com.alpha.theaterservice.repository.ShowRepository;
import com.alpha.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	TheaterRepository theaterrepository;

	@Autowired
	ShowRepository showrepository;
	
	
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
		responseStructure.setMessage("New Theater Created Successfully");
		responseStructure.setData(savedTheater);
		
		return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.CREATED);
		
		
	}


//	public ResponseEntity<ResponseStructure<Theater>> findtheater(long id) {
//		Optional<Theater> theateroptional=theaterrepository.findById(id);
//		ResponseStructure<Theater> responseStructure=new ResponseStructure<Theater>();
//		if(theateroptional.isPresent()) {
//			responseStructure.setStatuscode(HttpStatus.FOUND.value());
//			responseStructure.setMessage("theater found");
//			responseStructure.setData(null);
//			return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.FOUND);
//		}
//		else {
//			responseStructure.setStatuscode(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("theater Not found");
//			responseStructure.setData(null);
//			return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.NOT_FOUND);
//		}
//	}
	public ResponseEntity<ResponseStructure<Theater>> findtheater(long id) {
		Theater gettheater=theaterrepository.findById(id).orElseThrow(()->new TheaterNOtFoundWithIdException());
		ResponseStructure<Theater> responseStructure=new ResponseStructure<Theater>();
		
			responseStructure.setStatuscode(HttpStatus.FOUND.value());
			responseStructure.setMessage("theater found");
			responseStructure.setData(gettheater);
			return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.FOUND);
		}


	public ResponseEntity<ResponseStructure<Theater>> DeleteTheater(long id) {
		Theater deletetheater=theaterrepository.findById(id).orElseThrow(()->new TheaterNOtFoundWithIdException());
		theaterrepository.delete(deletetheater);
		ResponseStructure<Theater>responseStructure=new ResponseStructure<Theater>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Theater found");
//		responseStructure.setData(deletetheater);
		return new ResponseEntity<ResponseStructure<Theater>>(responseStructure,HttpStatus.FOUND);
		
	}

	public List<Show> findAllPlayingShow(long id) {
		Theater foundtheater=theaterrepository.findById(id).orElseThrow(()->new TheaterNOtFoundWithIdException());
		      List<Show> findallplayingshows=  foundtheater.getAllplayingshows();
//		      ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
//		      responseStructure.setStatuscode(HttpStatus.FOUND.value());
//		      responseStructure.setMessage("found list of show");
//		      responseStructure.setData(null);
//		      
//		      return new ResponseEntity<ResponseStructure<List<Show>>>(HttpStatus.FOUND);
		      return findallplayingshows;
	}


	public ResponseEntity<ResponseStructure<Show>> findShow(long id) {
		Show getshow=showrepository.findById(id).orElseThrow(()->new showNotFoundByIdException());
		ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("show found");
		responseStructure.setData(getshow);
		return new ResponseEntity<ResponseStructure<Show>>(responseStructure,HttpStatus.FOUND);
		
	}


	public ResponseEntity<ResponseStructure<Show>> updateShow(UpdateShowDto updateShowDto) {
		Show updateShow=showrepository.findById(updateShowDto.getId()).orElseThrow(()-> new showNotFoundByIdException());
		boolean exist = showrepository.existsByShowDateAndShowTimeAndScreennoAndIdNot(updateShowDto.getShowDate(),updateShowDto.getShowTime(),updateShowDto.getScreenno(),updateShowDto.getId());
			if (exist) {
			    throw new ShowAlreadyExistException();
			}
		updateShow.setShowDate(updateShowDto.getShowDate());
		updateShow.setShowTime(updateShowDto.getShowTime());
		updateShow.setScreenno(updateShowDto.getScreenno());
		showrepository.save(updateShow);
		ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
		responseStructure.setStatuscode(HttpStatus.ACCEPTED.value());
		responseStructure.setMessage("update show time and date Successfully");
		responseStructure.setData(updateShow);
		return new ResponseEntity<ResponseStructure<Show>>(responseStructure,HttpStatus.ACCEPTED);
		
	}
	
	
}
	
    


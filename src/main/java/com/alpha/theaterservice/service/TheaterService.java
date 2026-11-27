package com.alpha.theaterservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.theaterservice.dto.CreateTheaterDto;
import com.alpha.theaterservice.dto.ResponseStructure;
import com.alpha.theaterservice.dto.UpdateShowDto;
import com.alpha.theaterservice.dto.UpdateTheaterDto;
import com.alpha.theaterservice.entity.Movie;
import com.alpha.theaterservice.entity.Show;
import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.exception.MovieNotFoundWithIdExpection;
import com.alpha.theaterservice.exception.MoviesNotAvailableException;
import com.alpha.theaterservice.exception.ShowAlreadyExistException;
import com.alpha.theaterservice.exception.ShowNotFoundByIdException;
import com.alpha.theaterservice.exception.TheaterAlreadyExistWithContactException;
import com.alpha.theaterservice.exception.TheaterNOtFoundWithIdException;
import com.alpha.theaterservice.repository.MovieRepository;
import com.alpha.theaterservice.repository.ShowRepository;
import com.alpha.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	TheaterRepository theaterrepository;

	@Autowired
	ShowRepository showrepository;
	
	@Autowired
	MovieRepository movieRepository;
//--------------------------------------------------------------------------------------------------------------
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
	
//----------------------------------------------------------------------------------------------------------
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
//----------------------------------------------------------------------------------------------------------------
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
//--------------------------------------------------------------------------------------------------------
	public ResponseEntity<ResponseStructure<Show>> findShow(long id) {
		Show getshow=showrepository.findById(id).orElseThrow(()->new ShowNotFoundByIdException());
		ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("show found");
		responseStructure.setData(getshow);
		return new ResponseEntity<ResponseStructure<Show>>(responseStructure,HttpStatus.FOUND);
		
	}


	public ResponseEntity<ResponseStructure<Show>> updateShow(UpdateShowDto updateShowDto) {
		Show updateShow=showrepository.findById(updateShowDto.getId()).orElseThrow(()-> new ShowNotFoundByIdException());
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


	public ResponseEntity<ResponseStructure<Movie>> findmovie(String title) { 

	    Movie getmovie = movieRepository.findByTitleIgnoreCase(title).orElseThrow(() -> new MoviesNotAvailableException());
	    System.out.println(getmovie.getTitle());
	    ResponseStructure<Movie> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatuscode(HttpStatus.OK.value());
	    responseStructure.setMessage("Movie found");
	    responseStructure.setData(getmovie);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
//--------------------------------------------------------------------------------------------------------------
	public ResponseStructure<Optional<List<Show>>> findingTheatersByMovieName(String title, String language, LocalDate showdate) {
		List<Show> s = showrepository.findByTitleContainingIgnoreCase(title);		
		List<Show> getShows = new ArrayList<>();
		boolean IsLangPresent=false;
		boolean IsMoviePresentWithDate=false;
		ResponseStructure<Optional<List<Show>>> rs = new ResponseStructure<>();
		for(Show movie: s) {
			String lang=movie.getLanguage();
			LocalDate Ldate = movie.getShowDate();
			IsLangPresent=lang.equalsIgnoreCase(language);
			IsMoviePresentWithDate=Ldate.isEqual(showdate);
			if(IsLangPresent&&IsMoviePresentWithDate) {
				getShows.add(movie);
			}
		}
		if(s.isEmpty()) {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Movie Not Found");
			rs.setData(null);
		}
		else if(getShows.isEmpty() && !IsLangPresent) {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Movie Not Found By Language");
			rs.setData(null);
		}else if (getShows.isEmpty()&&!IsMoviePresentWithDate) {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Movie Not Found By Date");
			rs.setData(null);
		}else {
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Shows found with the Movie name, Language and Date");
			rs.setData(Optional.of(getShows));
		}
		return rs;
	}
//----------------------------------------------------------------------------------------------------------------
	public ResponseStructure<Theater> updateTheaterAccount(long id, UpdateTheaterDto updateTheaterDto) {
		Theater theater=theaterrepository.findById(id).orElseThrow(()-> new TheaterNOtFoundWithIdException());
		theater.setName(updateTheaterDto.getName());
		theater.setAddress(updateTheaterDto.getAddress());
		theater.setCity(updateTheaterDto.getCity());
		theater.setContact(updateTheaterDto.getContact());
	    theater.setDescription(updateTheaterDto.getDescription());
		theater.setMailid(updateTheaterDto.getMailid());
		theater.setStatus(updateTheaterDto.getStatus());
		theater.setTotalscreens(updateTheaterDto.getTotalscreens());
		Theater updateTheater=theaterrepository.save(theater);
		ResponseStructure<Theater> responseStructure=new ResponseStructure<Theater>();
		responseStructure.setStatuscode(HttpStatus.FOUND.value());
		responseStructure.setMessage("Theate update succefully by id: "+id);
		responseStructure.setData(updateTheater);
		return responseStructure;
	}
//------------------------------------------------------------------------------------------------------------
@Transactional
	public ResponseStructure<Show> deleteshow(long theaterid, long showid) {
   Theater getTheater=theaterrepository.findById(theaterid).orElseThrow(()->new TheaterNOtFoundWithIdException());
	Show getshow=showrepository.findById(showid).orElseThrow(()->new ShowNotFoundByIdException());
	
	if(getshow.getTheater()==null || getshow.getTheater().getId()!=theaterid) {
		throw new ShowNotFoundByIdException();
	}
	 showrepository.deleteShowReference(showid);
          showrepository.delete(getshow);
          
     ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
     responseStructure.setStatuscode(HttpStatus.FOUND.value());
     responseStructure.setMessage("Show ID: " + showid + " deleted successfully");
     responseStructure.setData(getshow);
     return responseStructure;	
	}


public ResponseStructure<List<Show>> findByshowWithDate(long theaterid, LocalDate showdate) {
	Theater getTheater=theaterrepository.findById(theaterid).orElseThrow(()->new TheaterNOtFoundWithIdException());
	 List<Show> shows = showrepository.findByTheater_IdAndShowDate(theaterid, showdate);
	 if(shows.isEmpty()) {
		 throw new ShowNotFoundByIdException();
	 }
	 ResponseStructure<List<Show>> responseStructure=new ResponseStructure<List<Show>>();
	 responseStructure.setStatuscode(HttpStatus.FOUND.value());
	 responseStructure.setMessage("Shows fetched successfully");
	 responseStructure.setData(shows);
	return responseStructure;
}
//-------------------------------------------------------------------------------------------------------------
public ResponseStructure<List<Show>> findShowByTheaterId(long theaterid) {
 Theater getTheater=theaterrepository.findById(theaterid).orElseThrow(()->new TheaterNOtFoundWithIdException());
       List<Show> show =showrepository.findByTheater_Id(theaterid);
       if(show.isEmpty()) {
    	   throw new ShowNotFoundByIdException();
       }
       ResponseStructure<List<Show>> responseStructure=new ResponseStructure<List<Show>>();
       responseStructure.setStatuscode(HttpStatus.FOUND.value());
       responseStructure.setMessage("Shows and movie details fetched successfully");
       responseStructure.setData(show);
       return responseStructure;	               
   }
//-------------------------------------------------------------------------------------------------------------
public ResponseStructure<List<Show>> findByScreenno(long theaterid,int screenno) {
	Theater getTheater=theaterrepository.findById(theaterid).orElseThrow(()->new TheaterNOtFoundWithIdException());
	List<Show> getShows=showrepository.findByTheater_IdAndScreenno(theaterid,screenno);
	if(getShows.isEmpty()) {
		throw new ShowNotFoundByIdException();
	}
	ResponseStructure<List<Show>> responseStructure=new ResponseStructure<List<Show>>();
	responseStructure.setStatuscode(HttpStatus.FOUND.value());
	responseStructure.setMessage("Show get successfully by screenno: "+screenno);
	responseStructure.setData(getShows);
	return responseStructure; 	
}
//---------------------------------------------------------------------------------------------
public ResponseStructure<Show> getShowDetails(long theaterid, long showid) {
   Theater getTheater=theaterrepository.findById(theaterid).orElseThrow(()->new TheaterNOtFoundWithIdException());
    Show show=showrepository.findById(showid).orElseThrow(()->new ShowNotFoundByIdException());
    ResponseStructure<Show> responseStructure=new ResponseStructure<Show>();
    responseStructure.setStatuscode(HttpStatus.FOUND.value());
    responseStructure.setMessage("show get complete details");
    responseStructure.setData(show);
    return responseStructure;
}	

}
	
    


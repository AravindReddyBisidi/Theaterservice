package com.alpha.theaterservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	TheaterRepository theaterrepository;

	public Theater createtheater(Theater t) {
		System.out.println("i am inside service layer");
		System.out.println(t);
		Theater savetheater=theaterrepository.save(t);
		return savetheater;
	}

}

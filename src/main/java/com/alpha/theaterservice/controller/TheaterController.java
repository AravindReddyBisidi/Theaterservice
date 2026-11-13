package com.alpha.theaterservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterservice.entity.Theater;
import com.alpha.theaterservice.service.TheaterService;

@RestController
public class TheaterController {
	
	@Autowired
	TheaterService theaterservice;

	@PostMapping("/theater/createaccount")
	public Theater createtheater(@RequestBody Theater t) {
		System.out.println(t);
		return theaterservice.createtheater(t);
	}
}

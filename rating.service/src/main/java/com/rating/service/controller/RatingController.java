package com.rating.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.service.entity.Rating;
import com.rating.service.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return new ResponseEntity<Rating>(ratingService.create(rating),HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatings(){
		return new ResponseEntity<List<Rating>>(ratingService.getRating(),HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId){
		return new ResponseEntity<List<Rating>>(ratingService.getRatingByUserId(userId),HttpStatus.OK);
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId){
		return new ResponseEntity<List<Rating>>(ratingService.getRatingByHotelId(hotelId),HttpStatus.OK);
	}
	
}

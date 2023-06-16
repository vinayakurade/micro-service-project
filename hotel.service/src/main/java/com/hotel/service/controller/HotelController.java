package com.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.service.entity.Hotel;
import com.hotel.service.service.HotelService;



@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel Hotel) {
		return new ResponseEntity<Hotel>(hotelService.creatHotel(Hotel), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
		return new ResponseEntity<Hotel>(hotelService.getHotelById(id), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel() {
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotel(), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
		return new ResponseEntity<Hotel>(hotelService.updateHotel(hotel, id), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public void deleteHotel(@PathVariable Long id) {
		hotelService.delete(id);
	}
}



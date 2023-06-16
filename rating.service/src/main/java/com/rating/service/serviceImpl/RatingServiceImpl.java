package com.rating.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entity.Rating;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository repository;

	@Override
	public Rating create(Rating rating) {
		return repository.save(rating);
	}

	@Override
	public List<Rating> getRating() {
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(Long userId) {
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(Long hotelId) {

		return repository.findByHotelId(hotelId);
	}

}

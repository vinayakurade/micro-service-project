package com.rating.service.service;

import java.util.List;

import com.rating.service.entity.Rating;

public interface RatingService {
	
	Rating create(Rating rating);
	List<Rating> getRating();
	List<Rating> getRatingByUserId(Long userId);
	List<Rating> getRatingByHotelId(Long hotelId);
	

}

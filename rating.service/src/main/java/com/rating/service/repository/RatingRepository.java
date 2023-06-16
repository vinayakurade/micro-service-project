package com.rating.service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.service.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, Long> {
	
	//custom finder method
	List<Rating> findByUserId(Long userId);
	List<Rating> findByHotelId(Long hotelId);


}

package com.user.service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {
	
	private String ratingId;
	private Long userId;
	private Long hotelId;
	private int rating;
	private String feedback;
	private Hotel hotel;
	

}

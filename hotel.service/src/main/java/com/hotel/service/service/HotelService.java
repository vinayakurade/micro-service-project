package com.hotel.service.service;

import java.util.List;

import com.hotel.service.entity.Hotel;

public interface HotelService {

	Hotel creatHotel(Hotel hotel);

	Hotel getHotelById(Long hotelId);

	List<Hotel> getAllHotel();

	Hotel updateHotel(Hotel hotel, Long id);

	void delete(Long id);

}

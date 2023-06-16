package com.hotel.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exception.ResourseNotFoundException;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel creatHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(Long id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Hotel with given Id not found " + id));
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel updateHotel(Hotel hotel, Long id) {
		Hotel existinghotel = getHotelById(id);
		existinghotel.setName(hotel.getName());
		existinghotel.setLocation(hotel.getLocation());
		existinghotel.setAbout(hotel.getAbout());

		hotelRepository.save(existinghotel);

		return existinghotel;
	}

	@Override
	public void delete(Long id) {
		Hotel hotel = getHotelById(id);
		hotelRepository.delete(hotel);
	}
}

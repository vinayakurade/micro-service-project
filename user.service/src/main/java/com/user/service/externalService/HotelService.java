package com.user.service.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable Long hotelId);

}

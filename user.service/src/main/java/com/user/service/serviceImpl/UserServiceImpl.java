package com.user.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalService.HotelService;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplete;
	
	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// implement rating service call: using rest template
		return userRepository.findAll();
	}

	@Override
	public User findById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User given Id is not found on server !! :  " + userId));

		// fetch rating of the above user from rating service
		// http://localhost:8083/ratings/users/4

		Rating[] ratingsOfUser = restTemplete.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);
		logger.info("{}", ratingsOfUser);

		List<Rating> ratings = Arrays.asList(ratingsOfUser);

		List<Rating> ratingList = ratings.stream().map(rating -> {
	//		http://localhost:8082/hotels/2
//			ResponseEntity<Hotel> forEntity = restTemplete
//					.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//			Hotel hotel = forEntity.getBody();
//			logger.info("response status code:{}", forEntity.getStatusCode());
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;

		}).collect(Collectors.toList());
		logger.info("Hotel List {} " , ratingList);
		user.setRatings(ratingList);
		
		return user;

	}

	@Override
	public void delete(Long userId) {
		User user = findById(userId);
		userRepository.delete(user);

	}

	@Override
	public User updateUser(User user, Long id) {
		User existinguser = findById(id);
		existinguser.setName(user.getName());
		existinguser.setEmail(user.getEmail());
		existinguser.setAbout(user.getAbout());

		userRepository.save(existinguser);
		return existinguser;

	}

}

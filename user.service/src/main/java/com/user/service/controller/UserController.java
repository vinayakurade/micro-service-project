package com.user.service.controller;

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

import com.user.service.entity.User;
import com.user.service.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	int retrycount = 1;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping("{id}")
//	@CircuitBreaker(name= "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@Retry(name= "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
		log.info("Get single user handler: User controller");
		log.info("Retry Count:{}", retrycount++);
		return new ResponseEntity<User>(userService.findById(userId), HttpStatus.OK);

	}

	// creating fall back method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(Long userId, Exception ex) {
		// log.info("FallBack is executed because service is down : {}",
		// ex.getMessage());
		User user = new User();
		user.setEmail("Dummy.gmail.com");
		user.setName("Dummy");
		user.setAbout("this user created dummy because some services is down.");
		user.setUserId(1423l);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}

	@PutMapping("{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.delete(id);
	}
}

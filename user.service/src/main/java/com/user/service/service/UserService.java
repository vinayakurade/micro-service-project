package com.user.service.service;

import java.util.List;

import com.user.service.entity.User;

public interface UserService {

	User save(User user);

	List<User> getAllUser();

	User findById(Long userId);

	User updateUser(User user, Long id);

	void delete(Long userId);

}

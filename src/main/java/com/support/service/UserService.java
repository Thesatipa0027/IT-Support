package com.support.service;

import java.util.List;
import java.util.Optional;

import com.support.entity.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> userList();
	
	Optional<User> getUserById(long id);

	void deleteUser(long id);
}

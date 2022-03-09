package com.support.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.support.entity.User;
import com.support.repository.UserRepository;
import com.support.service.UserService;

@Service
public class UserServiceIMpl implements UserService{
	
	private final UserRepository repository;

	public UserServiceIMpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> userList() {
		return repository.findAll();
	}

	@Override
	public Optional<User> getUserById(long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteUser(long id) {
		repository.deleteById(id);
	}

	
	
}

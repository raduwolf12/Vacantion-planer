package com.baeldung.springsecuritythymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.springsecuritythymeleaf.model.User;
import com.baeldung.springsecuritythymeleaf.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllAccounts() {
		List<User> a = userRepository.findAll();
		return a;
	}

	public Optional<User> getAccountById(long id) {
		Optional<User> a = userRepository.findById(id);
		return a;
	}
}

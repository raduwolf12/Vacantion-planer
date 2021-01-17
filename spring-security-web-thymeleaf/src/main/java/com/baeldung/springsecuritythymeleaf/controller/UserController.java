package com.baeldung.springsecuritythymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.springsecuritythymeleaf.model.User;
import com.baeldung.springsecuritythymeleaf.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/getAllUsers")
	public List<User> getAllAccounts() {

		return userService.getAllAccounts();
	}

	@GetMapping(value = "/findAccountById/{id}")
	public Optional<User> getAccountById(long id) {

		return userService.getAccountById(id);
	}
}

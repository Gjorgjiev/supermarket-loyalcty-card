package com.springframework.boot.supermarketloyaltycard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.services.UserService;
	
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public User user(@RequestParam String name, @RequestParam String surname, @RequestParam String mobileNumber, 
			@RequestParam String idCardNumber) {
		return userService.saveUser(name, surname, mobileNumber, idCardNumber);
	}

	@GetMapping("/users")
	public User getUser(@RequestParam String mobileNumber, @RequestParam String idCardNumber) {
		return userService.findUser(mobileNumber, idCardNumber);
	}

}

package com.springframework.boot.supermarketloyaltycard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.services.UserService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
	
@Tag(name = "Sql-Controller", description = "Endpoints for creating and retreaving user")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Creating user and fetching user from DB", content = {
        @Content(mediaType = "application/json") }),
    @ApiResponse(responseCode = "500", description = "Internal server error: Unable to acquire connection. The server encountered an unexpected condition that prevented it from fullfilling the request.") })
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

package com.springframework.boot.supermarketloyaltycard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.boot.supermarketloyaltycard.models.Transactions;
import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.services.PointsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "Sql-Controller", description = "Endpoints for adding points to user account and option for a discount or a packet of water")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Retrieve data in JSON format from the specified database", content = {
        @Content(mediaType = "application/json") }),
    @ApiResponse(responseCode = "500", description = "Internal server error: Unable to acquire connection. The server encountered an unexpected condition that prevented it from fullfilling the request.") })
@RestController
public class PointsController {

	@Autowired
	private PointsService pointsService;

	@PostMapping("/add-points")
	public User addPoints(@RequestParam String cashierId, @RequestParam String mobileNumber, 
			@RequestParam String cardNumber, @RequestParam float euros) {
		return pointsService.addPoints(cashierId, mobileNumber, cardNumber, euros);
	}

	@PutMapping("/redeem")
	public Transactions redeemPoints(@RequestParam String cashierId, 
			@RequestParam String mobileNumber,
			@RequestParam String cardNumber,
			@RequestParam String option,
			@RequestParam int points) {
		return pointsService.redeemPoints(cashierId, mobileNumber, cardNumber, option, points);
	}


}

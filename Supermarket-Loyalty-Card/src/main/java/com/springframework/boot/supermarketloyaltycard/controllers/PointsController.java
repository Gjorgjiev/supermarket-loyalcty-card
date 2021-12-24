package com.springframework.boot.supermarketloyaltycard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.boot.supermarketloyaltycard.models.Transactions;
import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.services.PointsService;

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

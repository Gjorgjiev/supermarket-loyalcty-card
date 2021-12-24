package com.springframework.boot.supermarketloyaltycard.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.boot.supermarketloyaltycard.models.Transactions;
import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.repository.TransactionsRepository;
import com.springframework.boot.supermarketloyaltycard.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PointsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TransactionsRepository transactionsRepository;

	public User addPoints(String cashierId, String mobileNumber, String cardNumber, float euros) {
		return points(cashierId, mobileNumber, cardNumber, euros);
	}

	public Transactions redeemPoints(String cashierId, String mobileNumber, String cardNumber, String option, int points) {
		return usePoints(cashierId, mobileNumber, cardNumber, option, points);
	}

	private User points(String cashierId, String mobileNumber, String cardNumber, float euros) {
		int points = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.now();
		String time = formatter.format(dateTime);
		User userFromDb = userRepository.findByMobileNumberAndIdCardNumber(mobileNumber, cardNumber);
		if(userFromDb != null) {
			Transactions transactions = new Transactions();
			transactions.setCashierId(cashierId);
			transactions.setEuros(euros);
			transactions.setMobileNumber(userFromDb.getMobileNumber());
			transactions.setUserCardNumber(userFromDb.getIdCardNumber());
			transactions.setTimestamp(time);
			if(euros == 50) {
				points += 10;
				transactions.setPurchasePoints(points);
			}else {
				float purchasePoints = euros / 50;
				int totalPurchasePoints = (int)purchasePoints * 10;
				transactions.setPurchasePoints(totalPurchasePoints);
			}
			transactions.setUser(userFromDb);
			transactionsRepository.save(transactions);
		}
		return userFromDb;
	}

	private Transactions usePoints(String cashierId, String mobileNumber, String cardNumber, String option, int points) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.now();
		String time = formatter.format(dateTime);
		User userFromDb = userRepository.findByMobileNumberAndIdCardNumber(mobileNumber, cardNumber);
		Transactions transactionsFromDb = transactionsRepository.findUser(userFromDb.getId());
		int userPoints = transactionsFromDb.getPurchasePoints();
		if(userPoints < 0) {
			log.info("You dont have enough points");
			transactionsFromDb.setPurchasePoints(0);
			transactionsRepository.save(transactionsFromDb);
		}
		if( points % 100 == 0) {
			if(option.equalsIgnoreCase("packet of water")) {
				int pointsToDb = userPoints - points;
				transactionsFromDb.setPurchasePoints(pointsToDb);
				transactionsRepository.save(transactionsFromDb);
			}else if(option.equalsIgnoreCase("discount")) {
				transactionsRepository.findLastTransaction(time);
				float moneyFromDb = transactionsFromDb.getEuros();
				int discount = points / 100;
				float moneyToDb = moneyFromDb - discount;
				int pointsToDb = userPoints - points;
				transactionsFromDb.setPurchasePoints(pointsToDb);
				transactionsFromDb.setEuros(moneyToDb);
				transactionsRepository.save(transactionsFromDb);
			}
		}
		return transactionsFromDb;
	}

}

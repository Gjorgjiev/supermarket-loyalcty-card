package com.springframework.boot.supermarketloyaltycard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.boot.supermarketloyaltycard.models.User;
import com.springframework.boot.supermarketloyaltycard.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(String name, String surname, String mobileNumber, String idCardNumber) {
		return user(name, surname, mobileNumber, idCardNumber);
	}

	public User findUser(String mobileNumber, String idCardNumber) {
		return userRepository.findByMobileNumberAndIdCardNumber(mobileNumber, idCardNumber);
	}

	private User user(String name, String surname, String mobileNumber, String idCardNumber) {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setMobileNumber(mobileNumber);
		if( idCardNumber.length() >= 13 && idCardNumber.length() <= 15) {
			user.setIdCardNumber(idCardNumber);
			userRepository.save(user);
		}else {
			log.error("Your id card number must be between 13 and 15 digits.");
		}
		return user;
	}

}

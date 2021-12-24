package com.springframework.boot.supermarketloyaltycard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.boot.supermarketloyaltycard.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM loyalty_card.user where mobile_number =:mobileNumber AND id_card_number =:idCardNumber", nativeQuery = true)
	User findByMobileNumberAndIdCardNumber(String mobileNumber, String idCardNumber);
}

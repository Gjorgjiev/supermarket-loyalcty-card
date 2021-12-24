package com.springframework.boot.supermarketloyaltycard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.boot.supermarketloyaltycard.models.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{

	@Query(value = "SELECT SUM (purchase_points) AS total_purchase_points "
			+ "FROM loyalty_card.transactions "
			+ "WHERE user_id =:userId", nativeQuery = true)
	Transactions getTotalPoints(Long userId);

	@Query(value = "SELECT * FROM loyalty_card.TRANSACTIONS WHERE user_id=:userId "
			+ "ORDER BY timestamp desc "
			+ "limit 1", nativeQuery = true)
	Transactions findUser(Long userId);

	@Query(value = "SELECT * FROM loyalty_card.transactions where timestamp =:timestamp ORDER BY timestamp DESC LIMIT 1", 
			nativeQuery = true)
	String findLastTransaction(String timestamp);


}

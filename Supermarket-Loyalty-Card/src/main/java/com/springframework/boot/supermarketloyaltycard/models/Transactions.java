package com.springframework.boot.supermarketloyaltycard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String cashierId;
	@Column(name = "user_mobile_number")
	private String mobileNumber;
	@Column(name = "user_card_number")
	private String userCardNumber;
	private float euros;
	@Column(name = "purchase_points")
	private int purchasePoints;
	private String timestamp;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;


}

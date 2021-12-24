package com.springframework.boot.supermarketloyaltycard.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;	
	private String name;
	private String surname;
	@Column(name = "mobile_number")
	private String mobileNumber;
	@Column(name = "id_card_number")
	private String idCardNumber;

	@OneToMany(mappedBy = "user")
	private List<Transactions> transactions;

	public User() {}

	public User(Long id, String name, String surname, String mobileNumber, String idCardNumber) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mobileNumber = mobileNumber;
		this.idCardNumber = idCardNumber;
	}


}

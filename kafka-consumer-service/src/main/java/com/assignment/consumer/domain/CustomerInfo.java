package com.assignment.consumer.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CustomerInfo {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerNumber = null;

	private String firstName = null;

	private String lastName = null;

	private String birthdate = null;

	private String country = null;

	private String countryCode = null;

	private BigDecimal mobileNumber = null;

	private String email = null;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	private String customerStatus;

	private String completeJsonRequest;

}

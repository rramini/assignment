package com.assignment.publisher.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerInfoEntity {

	private Integer customerNumber = null;

	private String firstName = null;

	private String lastName = null;

	private String birthdate = null;

	private String country = null;

	private String countryCode = null;

	private BigDecimal mobileNumber = null;

	private String email = null;
	
	//ToDO: add pending fields.
}

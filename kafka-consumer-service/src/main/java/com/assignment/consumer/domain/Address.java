package com.assignment.consumer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Address {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long address_id;
	
	private String addressLine1;
	private String addressLine2;
	private String street;
	private String postalCode;

}

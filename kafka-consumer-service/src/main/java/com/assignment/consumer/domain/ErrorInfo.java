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
public class ErrorInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer errorId = null;

	private String errorType = null;

	private String errorDescription = null;

	private String payload;
}

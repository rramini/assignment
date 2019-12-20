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
public class AuditInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer auditId = null;

	private Integer customerNumber = null;

	private String payload;
}

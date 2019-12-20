package com.assignment.publisher.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class UserInfo {

	@Column
	@Id
	private String username;

	@JsonIgnore
	@Column
	private String password;

	@Column
	private String role;

}

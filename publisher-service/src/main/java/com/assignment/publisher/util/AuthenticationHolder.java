package com.assignment.publisher.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.assignment.publisher.models.exceptions.InvalidUserSessionException;

@Component
public class AuthenticationHolder {

	public String getUser() {
		try {
			return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		} catch (Exception e) {
			throw new InvalidUserSessionException();
		}
	}

}

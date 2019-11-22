package com.assignment.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.assignment.publisher.jwt.JwtTokenUtil;
import com.assignment.publisher.models.JwtRequest;
import com.assignment.publisher.models.JwtResponse;
import com.assignment.publisher.models.UserInfo;
import com.assignment.publisher.service.impl.JwtUserDetailsServiceImpl;
import com.assignment.publisher.service.impl.UserInfoServiceImpl;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl ;
	
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/authenticate/add")
	public ResponseEntity<UserInfo> createUser(@RequestBody JwtRequest authenticationRequest) {

		UserInfo userInfo = userInfoServiceImpl.addUser(authenticationRequest);

		return ResponseEntity.ok(userInfo);
	}

	

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS", e);
		}
	}

}

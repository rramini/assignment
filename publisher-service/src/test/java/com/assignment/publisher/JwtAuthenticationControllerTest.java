package com.assignment.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import com.assignment.publisher.controller.JwtAuthenticationController;
import com.assignment.publisher.jwt.JwtTokenUtil;
import com.assignment.publisher.models.JwtRequest;
import com.assignment.publisher.service.impl.JwtUserDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationControllerTest {

	@InjectMocks
	private JwtAuthenticationController jwtAuthenticationController;

	@Spy
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	private JwtUserDetailsServiceImpl userDetailsService;

	@Mock
	private JwtRequest authenticationRequest;

	@Mock
	private UserDetails userDetails;

	@Test
	void createAuthenticationTokenSuccess() {
		when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);

		when(userDetailsService.loadUserByUsername(Mockito.any())).thenReturn(userDetails);
		when(jwtTokenUtil.generateToken(userDetails)).thenReturn("abc");

		assertEquals(HttpStatus.OK,
				jwtAuthenticationController.createAuthenticationToken(authenticationRequest).getStatusCode());
	}

	@Test
	void createAuthenticationExpectTokenLoginException() {

		when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> jwtAuthenticationController.createAuthenticationToken(authenticationRequest));

		assertTrue(Objects.requireNonNull(ex.getMessage()).contains("INVALID_CREDENTIALS"));
	}

	@Test
	void createAuthenticateExceptDisabledException() {

		when(authenticationManager.authenticate(Mockito.any())).thenThrow(DisabledException.class);

		ResponseStatusException ex = assertThrows(ResponseStatusException.class,
				() -> jwtAuthenticationController.createAuthenticationToken(authenticationRequest));

		assertTrue(Objects.requireNonNull(ex.getMessage()).contains("USER_DISABLED"));
	}
}

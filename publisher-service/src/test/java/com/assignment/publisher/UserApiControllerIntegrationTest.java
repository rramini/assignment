package com.assignment.publisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.publisher.models.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = { UserServiceImplTest.HELLOWORLD_TOPIC })
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserApiControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private HttpHeaders httpHeaders;

	private String applicaitonId = "test_case";

	private String validJSONPayload = "{\n" + "  \"customerNumber\": \"01234\",\n"
			+ "  \"firstName\": \"12345678901234\",\n" + "  \"lastName\": \"12345678901234\",\n"
			+ "  \"birthdate\": \"08-05-1993\",\n" + "  \"country\": \"India\",\n" + "  \"countryCode\": \"IN\",\n"
			+ "  \"mobileNumber\": \"9876543210\",\n" + "  \"email\": \"someone@example.com\",\n"
			+ "  \"customerStatus\": \"Open\",\n" + "  \"address\": {\n" + "    \"addressLine1\": \"string\",\n"
			+ "    \"addressLine2\": \"string\",\n" + "    \"street\": \"string\",\n"
			+ "    \"postalCode\": \"12345\"\n" + "  }\n" + "}";

	private String invalidJSONPayload = "{\n" + "  \"customerNumber\": \"0134\",\n" + "  \"firstName\": \"1234\",\n"
			+ "  \"lastName\": \"1234\",\n" + "  \"birthdate\": \"08-993\",\n" + "  \"country\": \"\",\n"
			+ "  \"countryCode\": \"\",\n" + "  \"mobileNumber\": \"9210\",\n" + "  \"email\": \"someoneom\",\n"
			+ "  \"customerStatus\": \"Opn\",\n" + "  \"address\": {\n" + "    \"addressLine1\": \"\",\n"
			+ "    \"addressLine2\": \"string\",\n" + "    \"street\": \"string\",\n"
			+ "    \"postalCode\": \"12345\"\n" + "  }\n" + "}";

	private String validLoginPayloaf = "{\n" + "\"username\": \"user\",\n" + "\"password\": \"password\"\t\n" + "}";

	private String authToken;

	@BeforeEach
	void setup() throws Exception {
		httpHeaders = new HttpHeaders();

		httpHeaders.add("activity", "mobile");
		httpHeaders.add("application_id", applicaitonId);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/authenticate").content(validLoginPayloaf)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String authorizationToken = result.getResponse().getContentAsString();

		JwtResponse jwtResponse = objectMapper.readValue(authorizationToken, JwtResponse.class);
		authToken = "Bearer " + jwtResponse.getToken();
	}

	@Test
	void createUserUnauthorized() throws Exception {
		httpHeaders.add("Authorization", "");

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(validJSONPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	void createUserAuthorized() throws Exception {
		httpHeaders.add("Authorization", authToken);

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(validJSONPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void createUserWithErrorInBody() throws Exception {
		httpHeaders.add("Authorization", authToken);

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(invalidJSONPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void createUserExpiredToken() throws Exception {
		httpHeaders.add("Authorization",
				"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTczNjU5NjAyLCJpYXQiOjE1NzM2NDE2MDJ9.Yd67ZM3eNyfyanO_N6LX6Ky-BkXETwNogDAf6R6XkOrW5U_I7HTPorjR7nAAB03PYflKBnEHObBPDPmBtuMUqw");

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(invalidJSONPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	void createUserInvalidToken() throws Exception {
		httpHeaders.add("Authorization",
				"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxM2NDE2MDJ9.Yd67ZM3eNyfyanO_N6LX6Ky-BkXETwNogDAf6R6XkOrW5U_I7HTPorjR7nAAB03PYflKBnEHObBPDPmBtuMUqw");

		mockMvc.perform(MockMvcRequestBuilders.post("/users").content(invalidJSONPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
}

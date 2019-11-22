package com.assignment.consumer.kafka.config;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.assignment.consumer.domain.CustomerInfo;
import com.assignment.consumer.repository.CustomerInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerConfig {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@KafkaListener(topics = "customerinfo", groupId = "customer-group-listener")
	@Transactional
	public void consume(String message) throws JsonMappingException, JsonProcessingException {

		logger.info("$$ -> Consumed Message -> {}", message);

		CustomerInfo customerInfo = mapper.readValue(message, CustomerInfo.class);

		try {
			customerInfo.setCompleteJsonRequest(message);
			customerInfoRepository.save(customerInfo);
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}

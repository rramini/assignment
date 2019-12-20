package com.assignment.consumer.kafka.service;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.assignment.consumer.domain.AuditInfo;
import com.assignment.consumer.domain.CustomerInfo;
import com.assignment.consumer.domain.ErrorInfo;
import com.assignment.consumer.repository.AuditInfoRepository;
import com.assignment.consumer.repository.CustomerInfoRepository;
import com.assignment.consumer.repository.ErrorInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private AuditInfoRepository auditInfoRepository;

	@Autowired
	private ErrorInfoRepository errorInfoRepository;

	@KafkaListener(topics = "customerinfo", groupId = "customer-group-listener")
	@Transactional
	public void consume(String message) throws JsonProcessingException {

		final long startTime = Calendar.getInstance().getTimeInMillis();

		logger.info("$$ -> Consumed Message -> {}", message);

		try {
			// Process and save the message published
			CustomerInfo customerInfo = mapper.readValue(message, CustomerInfo.class);
			customerInfo.setCompleteJsonRequest(message);
			customerInfo = customerInfoRepository.save(customerInfo);

			// create and save the AuditInfo
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setCustomerNumber(customerInfo.getCustomerNumber());
			auditInfo.setPayload(message);
			auditInfoRepository.save(auditInfo);

			final long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
			logger.info("Consumed message Successfully and saved : {}", elapsed);

		} catch (JsonProcessingException e) {
			logger.error("Failure in Proccessing  the published message", e.getMessage());
			// saving the error in data base
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorDescription(e.getMessage());
			errorInfo.setErrorType("JSON Parse Error");
			errorInfo.setPayload(message);
			errorInfoRepository.save(errorInfo);

			throw e;
		} catch (DataAccessException e) {
			logger.error("Failure in saving the published message", e.getMessage());
			throw e;
		} finally {

			final long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
			logger.info("Consumed message:: total response time: {}", elapsed);
		}
	}
}

package com.assignment.publisher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.publisher.kafka.KafkaProducerConfig;
import com.assignment.publisher.models.PublisherRequest;
import com.assignment.publisher.service.PublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private KafkaProducerConfig kafkaProducerConfig;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void publishToKafka(PublisherRequest publisherRequest) throws JsonProcessingException {

		// Java object to JSON string
		String jsonString = mapper.writeValueAsString(publisherRequest);
		kafkaProducerConfig.sendMessage(jsonString);

	}

}

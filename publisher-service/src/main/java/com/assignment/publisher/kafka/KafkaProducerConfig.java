package com.assignment.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerConfig {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfig.class);

	private static final String TOPIC = "customerinfo";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {

		logger.info("$$ -> Producing message --> {}", message);
		this.kafkaTemplate.send(TOPIC, message).addCallback(success -> logger.info("Message send successfully"),
				throwable -> logger.error("Message send Fail"));

	}

}

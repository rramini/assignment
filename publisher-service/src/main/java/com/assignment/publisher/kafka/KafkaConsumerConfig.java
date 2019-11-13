package com.assignment.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerConfig {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	@KafkaListener(topics = "customerinfo", groupId = "customer-group-listener")
	public void consume(String message) {
		logger.info("$$ -> Consumed Message -> {}", message);

	}
}

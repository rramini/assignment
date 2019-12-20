package com.assignment.publisher.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assignment.publisher.util.AuthenticationHolder;

@Service
public class KafkaProducerConfig {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfig.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private AuthenticationHolder authHoder;

	@Value(value = "${spring.kafka.publish.topic}")
	private String messageTopic;

	@Value(value = "${jwt.user-header}")
	private String userHeader;

	public String sendMessage(String message) {
		logger.debug("Sending message to kafka queue");
		Message<String> userMessage = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, messageTopic)
				.setHeader(userHeader, authHoder.getUser()).build();

		kafkaTemplate.send(userMessage).addCallback(this::onMessageSendSuccess, this::onException);

		return "Sending message to queue.";
	}

	private void onMessageSendSuccess(SendResult<String, String> sendResult) {
		logger.info("Message Sent successfully to topic: {}", sendResult);
	}

	private void onException(Throwable throwable) {
		logger.error("Error in sending message: {}", throwable, throwable);
		throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Unable to send message.", throwable);
	}

}

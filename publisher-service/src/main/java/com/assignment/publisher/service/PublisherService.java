package com.assignment.publisher.service;

import org.springframework.stereotype.Service;

import com.assignment.publisher.domain.CustomerInfoEntity;
import com.assignment.publisher.models.PublisherRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface PublisherService {

	public CustomerInfoEntity publishToKafka(PublisherRequest publisherRequest) throws JsonProcessingException;
}

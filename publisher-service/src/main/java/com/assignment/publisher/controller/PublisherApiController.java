package com.assignment.publisher.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.assignment.publisher.api.PublisherApi;
import com.assignment.publisher.models.PublisherRequest;
import com.assignment.publisher.models.PublisherResponse;
import com.assignment.publisher.models.exceptions.ApiException;
import com.assignment.publisher.service.PublisherService;
import com.assignment.publisher.utility.Util;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T08:52:16.551Z")

@Controller
public class PublisherApiController implements PublisherApi {

	@Autowired
	private PublisherService publisherService;

	private static final Logger logger = LoggerFactory.getLogger(PublisherApiController.class);

	public ResponseEntity<?> publish(
			@ApiParam(value = "This is interactionId or correlationId equivalent that is required to passed to MW/Backend. If request is from app, they also pass this to a web-view request as X-B3-TraceId header.", required = true) @RequestHeader(value = "X-B3-TraceId", required = true) String xB3TraceId,
			@ApiParam(value = "App need to pass X-B3-SpanId in header.", required = true) @RequestHeader(value = "X-B3-SpanId", required = true) String xB3SpanId,
			@ApiParam(value = "DESKTOP, MOBILE", required = true) @RequestHeader(value = "activity_id", required = true) String activityId,
			@ApiParam(value = "Access token that is received from IAM after authentication.", required = true) @RequestHeader(value = "Authorization", required = true) String authorization,
			@ApiParam(value = "", required = true) @RequestHeader(value = "application_id", required = true) String applicationId,
			@ApiParam(value = "", required = true) @Valid @RequestBody PublisherRequest request) throws ApiException {

		final long startTime = Calendar.getInstance().getTimeInMillis();
		logger.info("publish");

		try {

			PublisherResponse publisherResponse = new PublisherResponse();
			publisherService.publishToKafka(request);

			logger.info("publishAPI:StatusCode: {}", HttpStatus.OK);
			publisherResponse.setStatusCode(HttpStatus.OK.toString());
			return new ResponseEntity<>(publisherResponse, HttpStatus.OK);

		} catch (ApiException e) {
			logger.error("publishAPI:StatusCode: Failure:{}", e.getMessage());
			com.assignment.publisher.models.Error error = Util.buildErrorResponse(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			logger.error("publishAPI:StatusCode: Failure:{}", e.getMessage());
			com.assignment.publisher.models.Error error = Util.buildErrorResponse(e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		finally {
			final long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
			logger.info("publishAPI:StatusCode: {}", elapsed);
		}

	}

}

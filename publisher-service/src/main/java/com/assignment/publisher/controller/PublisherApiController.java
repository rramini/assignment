package com.assignment.publisher.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.assignment.publisher.api.PublisherApi;
import com.assignment.publisher.models.PublisherRequest;
import com.assignment.publisher.models.PublisherResponse;
import com.assignment.publisher.models.exceptions.ApiException;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T08:52:16.551Z")

@Controller
public class PublisherApiController implements PublisherApi {

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

			PublisherResponse publisherResponse = null;
			// logger.info("getSubscriberLinesAPI:StatusCode: {}", HttpStatus.OK);
			return new ResponseEntity<>(new PublisherResponse(), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("getSubscriberLinesAPI:StatusCode: Failure:{}", e.getMessage());
			// Error error = Util.buildErrorResponse(e.getMessage());
			return new ResponseEntity<>(new Error(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		finally {
			final long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
			logger.info("getSubscriberLinesAPI:StatusCode: {}", elapsed);
		}

	}

}

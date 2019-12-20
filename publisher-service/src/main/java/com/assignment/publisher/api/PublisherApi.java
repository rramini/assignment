package com.assignment.publisher.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assignment.publisher.models.PublisherRequest;
import com.assignment.publisher.models.PublisherResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T08:52:16.551Z")

@Api(value = "publisher", description = "the publisher API")
public interface PublisherApi {

	@ApiOperation(value = "Publishes Request to kafka producer", nickname = "publish", notes = "Publisher service", response = PublisherResponse.class, tags = {
			"publish", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PublisherResponse.class),
			@ApiResponse(code = 400, message = "Bad request", response = Error.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
			@ApiResponse(code = 403, message = "Forbidden", response = Error.class),
			@ApiResponse(code = 404, message = "Not Found", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
	@RequestMapping(value = "/publisher", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<PublisherResponse> publish(
			@ApiParam(value = "This is interactionId or correlationId equivalent that is required to passed to MW/Backend. If request is from app, they also pass this to a web-view request as X-B3-TraceId header.", required = true) @RequestHeader(value = "X-B3-TraceId", required = true) String xB3TraceId,
			@ApiParam(value = "App need to pass X-B3-SpanId in header.", required = true) @RequestHeader(value = "X-B3-SpanId", required = true) String xB3SpanId,
			@ApiParam(value = "DESKTOP, MOBILE", required = true) @RequestHeader(value = "activity_id", required = true) String activityId,
			@ApiParam(value = "Access token that is received from IAM after authentication.", required = true) @RequestHeader(value = "Authorization", required = true) String authorization,
			@ApiParam(value = "", required = true) @RequestHeader(value = "application_id", required = true) String applicationId,
			@ApiParam(value = "", required = true) @Valid @RequestBody PublisherRequest request);

}

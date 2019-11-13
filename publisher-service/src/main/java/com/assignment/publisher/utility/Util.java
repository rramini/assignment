package com.assignment.publisher.utility;

import static com.assignment.publisher.common.Constants.STATUS_CODE_ERROR;
import static com.assignment.publisher.common.Constants.STATUS_MESSAGE_ERROR;

public class Util {
	private Util() {
		// Do nothing default Constructer

	}

	public static com.assignment.publisher.models.Error buildErrorResponse(String message) {
		com.assignment.publisher.models.Error errorResponse = new com.assignment.publisher.models.Error();

		errorResponse.setCode(STATUS_CODE_ERROR);
		if (null != message) {
			errorResponse.setMessage(message);
		} else {
			errorResponse.setMessage(STATUS_MESSAGE_ERROR);
		}
		return errorResponse;
	}

}

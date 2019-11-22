package com.assignment.publisher;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.splunk.logging.SplunkCimLogEvent;

public class SplunkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Logger logger = LogManager.getLogger(SplunkTest.class);
		Date date = new Date();
		String jsonMsg = String.format("{event:'CancerCenterTest'}");
		logger.error("CancerCenterTest");

		logger.info(new SplunkCimLogEvent("Event name", "event-id") {
			{
				addField("name", "value");
				setAuthAction("deny");
			}
		});
	}

}

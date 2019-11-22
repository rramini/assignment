package com.assignment.publisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import com.assignment.publisher.kafka.KafkaProducerConfig;
import com.assignment.publisher.kafka.Receiver;
import com.assignment.publisher.models.PublisherRequest;
import com.assignment.publisher.service.PublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { UserServiceImplTest.HELLOWORLD_TOPIC })
public class UserServiceImplTest {

	public static final String HELLOWORLD_TOPIC = "user_queue";

	@Value(value = "${kafka.publish.topic}")
	private String messageTopic;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private Receiver receiver;

	@Mock
	private KafkaProducerConfig publisherServiceImpl;

	@Test
	void kafkaSetup_withTopic_ensureSendMessageIsReceived() throws JsonProcessingException, InterruptedException {
		publisherService.publishToKafka(new PublisherRequest());

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	@Test
	void testError() {
		doNothing().when(publisherServiceImpl).onException(new Throwable());
	}

}

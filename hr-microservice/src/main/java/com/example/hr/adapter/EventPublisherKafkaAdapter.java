package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Adapter(port=EventPublisher.class)
public class EventPublisherKafkaAdapter implements EventPublisher<HrEvent> {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	private final String hrEventsTopicName;
	
	public EventPublisherKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate, 
			@Value("${hrEventsTopicName}") String hrEventsTopicName, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.hrEventsTopicName = hrEventsTopicName;
	}

	@Override
	public void publish(HrEvent event) {
		try {
			var eventAsString = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(hrEventsTopicName, eventAsString );
		} catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}

}

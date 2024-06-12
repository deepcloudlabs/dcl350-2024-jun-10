package com.example.seacuritycard.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventListenerService {

	@KafkaListener(topics = "${hrEventsTopicName}", groupId = "security-card")
	public void listenHrEvents(String event) {
		System.err.println("[Kafka][HrEventListenerService] New hr event has arrived: %s".formatted(event));
	}
}

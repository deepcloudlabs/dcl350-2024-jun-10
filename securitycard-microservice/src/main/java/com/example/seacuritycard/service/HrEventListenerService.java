package com.example.seacuritycard.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventListenerService {

	@KafkaListener(topics = "${hrEventsTopicName}", groupId = "security-card")
	public void listenHrEvents(String event) {
		System.err.println("[Kafka][HrEventListenerService] New hr event has arrived: %s".formatted(event));
	}


	@RabbitListener(queues = "hrque")
	public void listenRandomEvents(String event) {
		System.err.println("[RabbitMQ][listenRandomEvents] New random event has arrived: %s".formatted(event));
	}
	
	@KafkaListener(topics = "crm-events", groupId = "security-card")
	public void listenCrmEvents(String event) {
		System.err.println("[Kafka][CrmEventListenerService] New crm event has arrived: %s".formatted(event));
	}
}

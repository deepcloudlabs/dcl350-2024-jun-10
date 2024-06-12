package com.example.hr.adapter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;

@Service
@Adapter(port = EventPublisher.class)
@ConditionalOnProperty(name = "edaStrategy", havingValue = "kafka-websocket")
public class EventPublisherSpringPublisherAdapter implements EventPublisher<HrEvent> {

	private final ApplicationEventPublisher applicationEventPublisher;
	
	public EventPublisherSpringPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void publish(HrEvent event) {
		applicationEventPublisher.publishEvent(event);
	}

}

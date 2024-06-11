package com.example.hr.infrastructure.messaging;

public interface EventPublisher<Event> {
	public void publish(Event event);
}

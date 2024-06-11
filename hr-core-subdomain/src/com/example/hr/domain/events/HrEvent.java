package com.example.hr.domain.events;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.ddd.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public abstract class HrEvent {
	private final static AtomicInteger counter = new AtomicInteger(1);
	private final TcKimlikNo identity;
	private final String id = UUID.randomUUID().toString();
	private final HrEventType eventType;
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int order = counter.getAndIncrement();

	public HrEvent(TcKimlikNo identity, HrEventType eventType) {
		this.identity = identity;
		this.eventType = eventType;
	}

	public String getId() {
		return id;
	}

	public HrEventType getEventType() {
		return eventType;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getOrder() {
		return order;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}

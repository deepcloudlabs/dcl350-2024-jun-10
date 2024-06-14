package com.example.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private final PreciousService preciousService;

	public ClientService(PreciousService preciousService) {
		this.preciousService = preciousService;
	}

	@Scheduled(fixedRate = 10_000)
	public void callPreciousService() {
		preciousService.callLongRunningFunction()
		               .thenAcceptAsync(result -> System.err.println("Result is %d".formatted(result)));
	}
}

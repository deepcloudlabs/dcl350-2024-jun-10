package com.example.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class PreciousService {

	@Retry(name = "precious",fallbackMethod = "callServiceFallback")
	public int callService() {
		System.err.println("Calling a remote service...");
		if(ThreadLocalRandom.current().nextInt(10)>3)
			throw new IllegalArgumentException("Something is wrong!");
		return 42;
	}
	
	public int callServiceFallback(Throwable t) {
		System.err.println("Running callServiceFallback(): %s: %s".formatted(t.getClass().getName(),t.getMessage()));
		return 108;
	}
	
	@RateLimiter(name="service-b")
	public int callAnotherService() {
		System.err.println("Calling another service...");
		return 549;
	}
	
	@TimeLimiter(name="service-c", fallbackMethod = "callLongRunningFunctionFallback")
	public CompletableFuture<Integer> callLongRunningFunction(){
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("callLongRunningFunction: %s".formatted(LocalDateTime.now()));
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5, 12));}catch(Exception e) {}
			System.err.println("callLongRunningFunction: %s".formatted(LocalDateTime.now()));
			return 3615;
		});
	}
	
	public CompletableFuture<Integer> callLongRunningFunctionFallback(Throwable t){
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("callLongRunningFunctionFallback: %s".formatted(LocalDateTime.now()));
			return 4629;
		});
	}
}

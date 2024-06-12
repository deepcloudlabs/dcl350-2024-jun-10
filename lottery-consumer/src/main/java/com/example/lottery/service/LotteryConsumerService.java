package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class LotteryConsumerService {
	private static final String LOTTERY_RS_URL = "http://%s:%d/api/v1/numbers?column=%d";
	private static final AtomicInteger counter = new AtomicInteger();
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;

	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	@Scheduled(fixedRate = 10_000)
	public void init() {
		instances = discoveryClient.getInstances("lottery");
	}

	public String getLotteryNumbers(int column) {
		var restTemplate = new RestTemplate();
		var instance = getNextInstance();
		var host = instance.getHost();
		var port = instance.getPort();
		var url = LOTTERY_RS_URL.formatted(host, port, column);
		try {
			return restTemplate.getForEntity(url, String.class).getBody();
		} catch (Exception e) {
			init();
			throw e;
		}
	}

	private ServiceInstance getNextInstance() {
		return instances.get(counter.getAndIncrement() % instances.size());
	}
}

package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="clbStrategy", havingValue = "custom")
public class LotteryClientService {
	private final LotteryConsumerService lotteryConsumerService;

	public LotteryClientService(LotteryConsumerService lotteryConsumerService) {
		this.lotteryConsumerService = lotteryConsumerService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.out.println(lotteryConsumerService.getLotteryNumbers(3));
	}
}

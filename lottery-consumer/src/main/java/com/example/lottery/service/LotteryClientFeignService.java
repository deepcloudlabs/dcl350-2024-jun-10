package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="clbStrategy", havingValue = "feign")
public class LotteryClientFeignService {
	private final LotteryService lotteryService;

	public LotteryClientFeignService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		System.out.println("Through feign: %s".formatted(lotteryService.getLotteryNumbers(3)));
	}
}

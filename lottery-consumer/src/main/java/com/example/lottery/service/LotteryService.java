package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lottery.dto.response.LotteryModel;

@FeignClient(value = "lottery", path = "/api/v1")
public interface LotteryService {
	@GetMapping("/numbers")
	public List<LotteryModel> getLotteryNumbers(@RequestParam int column);
}

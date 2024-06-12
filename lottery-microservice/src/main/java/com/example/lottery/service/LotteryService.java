package com.example.lottery.service;

import java.util.List;

import com.example.lottery.dto.response.LotteryModel;

public interface LotteryService {
	LotteryModel draw();

	List<LotteryModel> draw(int column);

}

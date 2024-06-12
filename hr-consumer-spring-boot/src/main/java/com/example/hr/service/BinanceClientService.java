package com.example.hr.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceClientService {
	private static final List<String> SYMBOLS = List.of("LTCBTC", "BTCUSDT", 
			"BNTETH", "ZRXBTC","EOSBTC","ZECBTC", "MTLETH", "KMDETH",
			"HSRETH", "ICNBTC");
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=%s";

	private final RestTemplate restTemplate;
	
	
	public BinanceClientService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	@Scheduled(fixedRate = 5_000)
	public void callBinanceRestApi() {
		for (var symbol : SYMBOLS) {
			var response = restTemplate.getForEntity(BINANCE_REST_API.formatted(symbol), String.class)
			            .getBody();
			System.out.println(response);
		}
	}
}

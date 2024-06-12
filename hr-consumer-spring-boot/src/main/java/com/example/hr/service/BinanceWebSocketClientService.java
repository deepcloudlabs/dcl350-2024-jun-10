package com.example.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import jakarta.annotation.PostConstruct;

@Service
public class BinanceWebSocketClientService implements WebSocketHandler {
	private static final String BINANCE_WS_API = "wss://stream.binance.com:9443/ws/btcusdt@trade";

	private final WebSocketClient webSocketClient;
	
	public BinanceWebSocketClientService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.execute(this,BINANCE_WS_API)
		               .thenAcceptAsync(session -> {
		            	    System.out.println("Successfully connected to the binance websocket server: %s".formatted(session.getId())); 
		               });
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Websocket connection is open: %s".formatted(session.getId())); 		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println(message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.out.println("An error has occured: %s at session [%s]".formatted(e.getMessage(),session.getId())); 		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("Connection is closed for session [%s]".formatted(session.getId())); 				
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}

package com.example.hr.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.HrWebSocketService;

@Configuration
@EnableWebSocket
@ConditionalOnProperty(name = "edaStrategy", havingValue = "kafka-websocket")
public class WebSocketConfig implements WebSocketConfigurer{
	private final HrWebSocketService hrWebSocketService;
	
	public WebSocketConfig(HrWebSocketService hrWebSocketService) {
		this.hrWebSocketService = hrWebSocketService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(hrWebSocketService, "/hr-events")
		        .setAllowedOrigins("*");
	}

	
}

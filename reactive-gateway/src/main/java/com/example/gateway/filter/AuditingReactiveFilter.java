package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Service
public class AuditingReactiveFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.err.println("Path: %s".formatted(request.getPath()));
		System.err.println("Local Address: %s".formatted(request.getLocalAddress()));
		System.err.println("Method: %s".formatted(request.getMethod()));
		System.err.println("URI: %s".formatted(request.getURI()));
		request.getHeaders().forEach((name,value)-> System.err.println("%16s: %s".formatted(name,value)));
		System.err.println("URI: %s".formatted(request.getURI()));
		request.getBody().subscribe(System.err::println);
		var response = exchange.getResponse();
		response.getHeaders().forEach((name,value)-> System.err.println("%16s: %s".formatted(name,value)));
		System.err.println("Response code: %s".formatted(response.getStatusCode()));
		return chain.filter(exchange);
	}

}

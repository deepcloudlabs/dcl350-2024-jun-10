package com.example.exercises;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Exercise01 {
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request1 = HttpRequest.newBuilder(URI.create("http://localhost:7100/hr/api/v1/employees/11111111110")).build();
		var request2 = HttpRequest.newBuilder(URI.create("http://localhost:7100/hr/api/v1/employees/11111111110/photo")).build();
		var employee = httpClient.send(request1, BodyHandlers.ofString()).body();
		System.out.println(employee);
		var photo = httpClient.send(request2, BodyHandlers.ofString()).body();
		System.out.println(photo);
	}
}

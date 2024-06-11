package com.example.hr.exercises;

import com.example.hr.domain.FullName;

public class Exercise03 {

	public static void main(String[] args) {
		var jack1 = new FullName("jack", "bauer");
		var jack2 = new FullName("jack", "bauer");
		System.out.println("jack1 == jack2 ? "+(jack1 == jack2));
		System.out.println(jack1);
		var firtName = jack1.firstName().toUpperCase();
		System.out.println(jack1);
		System.out.println(firtName);
	}

}

package com.example.hr.exercises;

import com.example.hr.domain.JobStyle;

public class Exercise01 {

	public static void main(String[] args) {
		var fullTime = JobStyle.FULL_TIME;
		System.out.println("%12s: %d".formatted(fullTime.name(),fullTime.ordinal()));			
		for (var style : JobStyle.values()) {
			System.out.println("%12s: %d".formatted(style.name(),style.ordinal()));			
		}
		var partTime = JobStyle.valueOf("PART_TIME");
		System.out.println("%12s: %d".formatted(partTime.name(),partTime.ordinal()));			

	}

}

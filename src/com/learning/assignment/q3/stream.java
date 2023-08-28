package com.learning.assignment.q3;

import java.util.Arrays;

public class stream {
	public static void main(String[] args) {
		Integer[] numbers = {1, 2, 3, 4, 5};

		int sum = Arrays.stream(numbers)
				.filter(n -> n % 2 != 0) // Filter odd numbers
				.map(n -> n * n)         // Square each number
				.reduce(0, Integer::sum); // Calculate the sum

		System.out.println("SUM:"+sum);
		}
}
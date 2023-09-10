package lpnu.denysoliinyk.oi2023.pp.lab1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main executable class that provide lab task logic
 * The goal is to user pass desired N for generate N Fibonacci numbers
 * and print this number
 */
public class Main {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		NumberService numberService = new NumberService();
		int n;

		System.out.println();
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
			System.out.printf("You have passed %d Fibonacci number as argument.%n", n);
		} else {
			System.out.print("Enter N Fibonacci number: ");
			n = scanner.nextInt();
		}

		numberService.generateFibonacciNumbers(n);

		System.out.println("\nFibonacci numbers are: " + Arrays.toString(numberService.getFibonacciNumbers()));
		System.out.printf("%nYour %d Fibonacci number is %d.%n", n, numberService.getLastNumber());
	}
}
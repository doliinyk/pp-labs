package lpnu.denysoliinyk.oi2023.pp.lab1;

/**
 * Service class that handling operations with Fibonacci numbers
 */
public class NumberService {
	private int[] fibonacciNumbers;

	/**
	 * Method that generates Fibonacci numbers limited by given number
	 * @param n Number of Fibonacci sequence
	 */
	public void generateFibonacciNumbers(int n) {
		fibonacciNumbers = new int[n];

		fibonacciNumbers[0] = 0;

		if (n > 1) {
			fibonacciNumbers[1] = 1;
		}

		for (int i = 2; i < n; i++) {
			fibonacciNumbers[i] = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
		}
	}

	/**
	 * Getter for Fibonacci numbers in service
	 * @return Array with Fibonacci numbers
	 */
	public int[] getFibonacciNumbers() {
		return fibonacciNumbers;
	}

	/**
	 * Getter for last generated number in Fibonacci numbers
	 * @return Last generated number
	 */
	public int getLastNumber() {
		return fibonacciNumbers[fibonacciNumbers.length - 1];
	}
}

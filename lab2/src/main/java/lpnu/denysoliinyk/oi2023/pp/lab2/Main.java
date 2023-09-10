package lpnu.denysoliinyk.oi2023.pp.lab2;

import lpnu.denysoliinyk.oi2023.pp.lab2.product.Product;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Product[] products = createProducts();

	public static void main(String[] args) {
		System.out.println("Actual products:");
		Arrays.stream(products).forEach(System.out::println);
		System.out.println();

		System.out.print("Enter name you want to filter products by: ");
		String name = scanner.nextLine();

		System.out.print("Enter price you want to filter products by with name: ");
		double price = scanner.nextDouble();

		System.out.print("Enter storing term in months you want to filter products by: ");
		int storingTerm = scanner.nextInt();
		System.out.println();

		System.out.printf("Products which names contains \"%s\":%n", name);
		filterProductsByName(name).forEach(System.out::println);
		System.out.println();

		System.out.printf("Products which names contains \"%s\" and price less than $%.2f:%n", name, price);
		filterProductsByNameAndPriceLess(name, price).forEach(System.out::println);
		System.out.println();

		System.out.printf("Products which storing term more than %d months:%n", storingTerm);
		filterProductsByStoringTermMore(storingTerm).forEach(System.out::println);
		System.out.println();
	}

	private static Product[] createProducts() {
		return new Product[] {
				new Product("Athlon", "AMD", 20, 20, 50),
				new Product("Ryzen", "AMD", 15, 15, 200),
				new Product("Core i7", "Intel", 25, 30, 100),
				new Product("Pentium", "Intel", 10, 10, 150),
				new Product("Xeon", "Intel", 30, 35, 30),
				new Product("Snapdragon", "Qualcomm", 18, 20, 80),
				new Product("Exynos", "Samsung", 12, 12, 60),
				new Product("A14 Bionic", "Apple", 20, 25, 70),
				new Product("Ryzen Threadripper", "AMD", 35, 40, 10),
				new Product("Celeron", "Intel", 8, 8, 120),
				new Product("Ryzen 9", "AMD", 28, 32, 15),
				new Product("Atom", "Intel", 5, 5, 180)
		};
	}

	private static Stream<Product> filterProductsByName(String name) {
		return Arrays.stream(products).filter(product -> product.getName().contains(name));
	}

	private static Stream<Product> filterProductsByNameAndPriceLess(String name, double price) {
		return Arrays.stream(products).filter(product -> product.getName().contains(name) && product.getPrice() <= price);
	}

	private static Stream<Product> filterProductsByStoringTermMore(int storingTerm) {
		return Arrays.stream(products).filter(product -> product.getStoringTerm() > storingTerm);
	}
}
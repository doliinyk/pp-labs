package lpnu.denysoliinyk.oi2023.pp.lab2.product;

import java.util.Date;
import java.util.UUID;

public class Product {
	private final UUID id;
	private String name;
	private String manufacturer;
	private double price;
	private int storingTerm;
	private int amount;

	public Product(String name, String manufacturer, double price, int storingTerm, int amount) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
		this.storingTerm = storingTerm;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format("%s %s, %d units, $%.2f up to %d months [%s]", manufacturer, name, amount, price, storingTerm, id);
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStoringTerm() {
		return storingTerm;
	}

	public void setStoringTerm(int storingTerm) {
		this.storingTerm = storingTerm;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

package com.techelevator;

public class Product {
	
	private String slot;
	private String name;
	private int price;
	private int count;
	
	public Product(String slot, String name, int price, int count) {
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
/**/public Product(String slot){
		
	}
	
	public String getProductInfo() {
		String info = slot + " | " + name + " | " + price/100d +" | " + count;
		return info;
	}

	public String getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}
	
	public boolean dispense() {
		if (count >= 1) {
			count--;
			return true;
		}
		return false;
	}
}

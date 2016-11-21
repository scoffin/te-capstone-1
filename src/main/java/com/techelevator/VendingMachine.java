package com.techelevator;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class VendingMachine {
	
	private LinkedHashMap<String, Product> inventory;
	private int transactionBalance;
	List<String> runningLog;
	int stockQuantity;
	
	public VendingMachine() {
		inventory = new LinkedHashMap<>();
		transactionBalance = 0;
		runningLog = new ArrayList<>();
	}
	
	public void stockProduct(String productInfo, int stockQuantity) {
		String[] properties = productInfo.split("\\|");
		String slot = properties[0];
		String name = properties[1];
		int price = (int)(Double.parseDouble(properties[2]) * 100);
		this.stockQuantity = stockQuantity;
		Product product = new Product(slot, name, price, stockQuantity);
		inventory.put(slot, product);
	}
	
	public String getAllInventory() {
		//returns a block of text of all product info (iterates through inventory collection, with line break insertion)
		//called by user from Inventory option of main menu
		StringBuffer allInventoryItems = new StringBuffer("[Slot] [Name] [Price] [Stock]\n");
		for (Product product : inventory.values()) {
			allInventoryItems.append(product.getProductInfo()).append("\n");
		}
		return allInventoryItems.toString();
	}
	
	public Product findProduct(String slot) {
		return inventory.get(slot);
	}
	
	public String buyItem(String slot) {
		//check if item is available (false if not)
		Product productToBuy = inventory.get(slot);
		if (productToBuy == null){
			return "This is not a slot... \n";
		}
		if (productToBuy.getCount() == 0) {
			return "Sorry, all sold out!";
		}
		if (transactionBalance < productToBuy.getPrice()) {
			return "Insert more money";
		}
		//deduct from balance
		productToBuy.dispense();
		transactionBalance -= productToBuy.getPrice();
		logSale(productToBuy);
		return "KaThunk! (A pristine " + productToBuy.getName() + " appears)";
	}
	
	public void logSale(Product productPurchased){
		String transactionLine;
		String transactionDateTime = LocalDateTime.now().toString();
		String logName = productPurchased.getName();
		String logSlot = productPurchased.getSlot();
		DecimalFormat format = new DecimalFormat("$0.00");
		String logAmountAccepted = format.format(transactionBalance + productPurchased.getPrice());
		String logChangeReturned = getBalance();
		transactionLine = transactionDateTime + " " + logName + " " + logSlot + " " + logAmountAccepted + " " + logChangeReturned;
		runningLog.add(transactionLine);
	}
	
	//money stuff
	//gets deposit (int) and purchase info (string) from vendo and returns balance and change info (strings)
	public String inputDollars(int dollars) {
		transactionBalance += dollars*100;
		return getBalance();
	}
	public String finishTransaction() {
		Change change = new Change(transactionBalance);
		String balanceInChange = "Your change is: " + change.getNumberOfQuarters() + " Quarters, " + change.getNumberOfDimes() + " Dimes, & " + change.getNumberOfNickels() + " Nickels.";
		transactionBalance = 0;
		return balanceInChange;
	}
	public String getBalance() {
		DecimalFormat formatter = new DecimalFormat("$0.00");
		return formatter.format(transactionBalance/100d);
	}
	
	//logs - returns string content to vendo for file IO
	public List<String> getTransactionLog() {
		return runningLog;
	}
	
	public String getSalesReport() {
		StringBuffer salesReportInfo = new StringBuffer("[Name] [Sold]\n");
		int grossSales = 0;
		int totalItemsSold = 0;
		for (Product product : inventory.values()) {
			salesReportInfo.append(product.getName()).append(": ");
			int amountSold = stockQuantity - product.getCount();
			grossSales += product.getPrice() * amountSold;
			salesReportInfo.append(amountSold).append("\n");
		}
		DecimalFormat df = new DecimalFormat("$0.00");
		salesReportInfo.append("Total Sales: " + df.format(grossSales));
		return salesReportInfo.toString();
	}
}
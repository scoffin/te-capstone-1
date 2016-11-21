package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Scanner;

public class VendoMaticTwoBillion {

	private static VendingMachine vendingMachine;

	public static void main(String[] args) {
		vendingMachine = new VendingMachine();
		// inventory stocking
		File inventoryFile = new File("vendingmachine.csv");
		if (!inventoryFile.exists()) {
			System.out.println("no inv file");
		}
		try (FileReader fr = new FileReader(inventoryFile); BufferedReader br = new BufferedReader(fr)) {
			String productInfo;
			while ((productInfo = br.readLine()) != null) {
				vendingMachine.stockProduct(productInfo, 5);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		// user interface

		// display menus branches
		// main menu will allow product display and immediately return to itself
		// handles input
		Scanner scan = new Scanner(System.in);
		boolean startedTransaction = false;
		while (!startedTransaction) {
			displayMainMenu();
			// 1 for inventory, 2 for purchases
			String mainMenuSelection = scan.nextLine();
			System.out.println("");
			if (mainMenuSelection.equals("2")) {
				startedTransaction = true;
			}
			while (mainMenuSelection.equals("1")) {
				System.out.println(vendingMachine.getAllInventory());
				System.out.println("---------------------------------------");
				System.out.println("Press (1) to return to main menu or (2) to move to the purchase menu. \n");
				String navFromProductsMenu = scan.nextLine();
				System.out.println("");
				if (navFromProductsMenu.equals("1")) {
					mainMenuSelection = "0";
					/**/ System.out.println("");
					continue;
				} else if (navFromProductsMenu.equals("2")) {
					startedTransaction = true;
					break;
				} else {
					System.out.println("I'm sorry... I don't understand. Please try again. \n");
				}
			}
		}
		// all products menu operations (go to, return to main, and move to
		// purchase menu) work!
		boolean finished = false;
		while (!finished) {
			displayPurchaseMenu();
			String purchaseMenuSelection = scan.nextLine();
			System.out.println("");
			if (purchaseMenuSelection.equals("1")) {
				System.out.print("★ HOW MANY DOLLARS? ★ \n");
				System.out.println("");
				try {
					int dollars = Integer.parseInt(scan.nextLine());
					System.out.println("");
					vendingMachine.inputDollars(dollars);
				} catch (NumberFormatException exception) {
					System.out.println("Invalid currency. Please try again. \n");
					continue;
				}
				// both "try" and "catch" scenarios from purchase menus work
			} else if (purchaseMenuSelection.equals("2")) {
				System.out.println("★ WHICH SLOT? ★ (Enter a letter A-D and a number 1-4.) \n");
				// works up to scan-line for slot selection
				String slotSelected = scan.nextLine();
				System.out.println("");
				System.out.println(vendingMachine.buyItem(slotSelected.toUpperCase()) + "\n");
			} else if (purchaseMenuSelection.equals("3")) {
				System.out.println(vendingMachine.finishTransaction() + "\n");
				System.out.println("HAVE A NICE DAY! ☺");
				finished = true;
			} else if (purchaseMenuSelection.equals("0")) {
				File transactionLogFile = new File("Vendo-Matic-Sales" + LocalDateTime.now() + ".csv");
				try (FileWriter fw = new FileWriter(transactionLogFile, true)) {
					// true mens do not overwrite the document (just append
					// onto)
					fw.write(vendingMachine.getSalesReport());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		vendingMachine.getTransactionLog();
		File transactionLogFile = new File("TransactionLog.txt");
		try (FileWriter fw = new FileWriter(transactionLogFile, true)) {
			// true mens do not overwrite the document (just append onto)
			fw.write("[DATE]   [TIME]   [PRODUCT]   [SLOT]   [AMOUNT ACCEPTED]   [CHANGE GIVEN] \n");
			for (String tl : vendingMachine.getTransactionLog()) {
				fw.write(tl + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void displayMainMenu() {
		System.out.println(" ★★★ Hello, I'm a VendoMatic 5000000000 ★★★ ");
		System.out.println("");
		System.out.println("★ SELECT A NUMBER ★");
		System.out.println("---------------------------------------");
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase \n");
	}

	static void displayPurchaseMenu() {
		System.out.println("★ SELECT A NUMBER ★");
		System.out.println("---------------------------------------");
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println("---------------------------------------");
		System.out.println("Current Money Provided: " + vendingMachine.getBalance() + "\n");
	}

	// ALL FUNCTIONS UP TO HERE WORK!!!! WE'RE AMAZING PROGRAMMERS!!!!

}

package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

public class VendingMachineTest {
	
	@Test
	public void stockProduct_properly_populates_hashmap_with_an_item() {
		VendingMachine machine = new VendingMachine();
		machine.stockProduct("A4|Cloud Popcorn|3.50");
		Product product = machine.findProduct("A4");
		assertEquals("A4", product.getSlot());
		assertEquals("Cloud Popcorn", product.getName());
		assertEquals(5, product.getCount());
		assertEquals(350, product.getPrice());
	}
	@Test
	public void inputDollars_increases_the_user_transaction_balance_on_the_machine() {
		VendingMachine machine = new VendingMachine();
		machine.inputDollars(50);
		assertEquals("$50.00", machine.getBalance());
	}
	@Test
	public void buyItem_fails_when_out_of_stock() {
		VendingMachine machine = new VendingMachine();
		machine.stockProduct("A4|Cloud Popcorn|3.50");
		machine.inputDollars(50);
		for (int i=0; i<5; i++) {
			machine.buyItem("A4");
		}
		assertEquals("Sorry, all sold out!", machine.buyItem("A4"));
		assertEquals(0, machine.findProduct("A4").getCount());
	}
	@Test
	public void buyItem_fails_when_out_of_money() {
		VendingMachine machine = new VendingMachine();
		machine.stockProduct("A4|Cloud Popcorn|3.50");
		machine.inputDollars(2);
		assertEquals("Insert more money", machine.buyItem("A4"));
	}
	@Test
	public void buyItem_succeeds_when_sufficient_stock_and_money_and_deducts_an_item_count() {
		VendingMachine machine = new VendingMachine();
		machine.stockProduct("A4|Cloud Popcorn|3.50");
		machine.inputDollars(20);
		assertEquals("KaThunk!", machine.buyItem("A4").substring(0, 8));
		assertEquals(4, machine.findProduct("A4").getCount());
	}
	
	@Test
	public void main_menu_selection_1_displays_all_item_info() {
	
	}
}

package com.techelevator;

import org.junit.Test;

import junit.framework.Assert;

public class ChangeTest {
	
//	public Change ( int changeToSort ) {
//	numberOfQuarters = changeToSort / 25;
//	int changeRemaining = changeToSort % 25;
//	numberOfDimes = changeRemaining / 10;
//	changeRemaining = changeRemaining % 10;
//	numberOfNickels = changeRemaining / 5;
//}
	@Test 
	public void change_returns_correct_change_in_fewest_coins100() {
		Change change = new Change( 100 );
		Assert.assertEquals(4, change.getNumberOfQuarters());
		Assert.assertEquals(0, change.getNumberOfDimes());
		Assert.assertEquals(0, change.getNumberOfNickels());
	}
	
	@Test 
	public void change_returns_correct_change_in_fewest_coins35() {
		Change change = new Change( 40 );
		Assert.assertEquals(1, change.getNumberOfQuarters());
		Assert.assertEquals(1, change.getNumberOfDimes());
		Assert.assertEquals(1, change.getNumberOfNickels());
	}
	
	@Test 
	public void change_returns_correct_change_in_fewest_coins5() {
		Change change = new Change( 5 );
		Assert.assertEquals(0, change.getNumberOfQuarters());
		Assert.assertEquals(0, change.getNumberOfDimes());
		Assert.assertEquals(1, change.getNumberOfNickels());
	}

}

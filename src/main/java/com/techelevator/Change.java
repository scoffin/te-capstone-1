package com.techelevator;

public class Change {

	private int numberOfQuarters;
	private int numberOfDimes;
	private int numberOfNickels;
	
	public Change ( int changeToSort ) {
		numberOfQuarters = changeToSort / 25;
		int changeRemaining = changeToSort % 25;
		numberOfDimes = changeRemaining / 10;
		changeRemaining = changeRemaining % 10;
		numberOfNickels = changeRemaining / 5;
	}

	public int getNumberOfQuarters() {
		return numberOfQuarters;
	}

	public int getNumberOfDimes() {
		return numberOfDimes;
	}

	public int getNumberOfNickels() {
		return numberOfNickels;
	}
		
}

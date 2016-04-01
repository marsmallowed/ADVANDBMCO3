package model;

public class Transaction {
	private Lock lockType;

	public Transaction(Lock lockType) {
		this.lockType = lockType;
	}
	
}

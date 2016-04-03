package model;

public class Transaction {
	private Lock lockType;
	private String query;
	private String senderIP, serverIP;

	public Transaction(Lock lockType, String query, String senderIP) {
		this.lockType = lockType;
		this.query = query;
		this.senderIP = senderIP;
	}

	public Lock getLockType() {
		return lockType;
	}

	public void setLockType(Lock lockType) {
		this.lockType = lockType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSenderIP() {
		return senderIP;
	}

	public void setSenderIP(String senderIP) {
		this.senderIP = senderIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	
	// TODO: execution and modification of query or whatevs
}

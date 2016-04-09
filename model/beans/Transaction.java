package model.beans;

import java.util.ArrayList;

public class Transaction {
	private Lock lockType;
	private ArrayList<String> queries;
	private String dbName;
	// sender -> the one requesting
	// receive -> the one who will be responding to the request
	private String senderNode, receiverNode;

	public Transaction(Lock lockType, String dbName, ArrayList<String> queries) {
		this.lockType = lockType;
		this.dbName = dbName;
		this.queries = queries;
	}
	
//	public Transaction(Lock lockType, String query, String senderIP) {
//		this.lockType = lockType;
//		this.query = query;
//		this.senderIP = senderIP;
//	}

	public Lock getLockType() {
		return lockType;
	}

	public void setLockType(Lock lockType) {
		this.lockType = lockType;
	}

	public ArrayList<String> getQueries() {
		return queries;
	}
	
	public void addQuery(String query) {
		this.queries.add(query);
	}

	public String getSenderNode() {
		return senderNode;
	}

	public void setSenderNode(String senderNode) {
		this.senderNode = senderNode;
	}

	public String getreceiverNode() {
		return receiverNode;
	}

	public void setReceiverNode(String receiverNode) {
		this.receiverNode = receiverNode;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}

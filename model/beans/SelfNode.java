package model.beans;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.client.TransactionClient;
import controller.db.Controller;
import controller.server.LogServer;
import controller.server.TransactionServer;

public class SelfNode extends Node {
	private Controller controller;
	private ArrayList<Transaction> transactions;
	private TransactionServer transactServer;
	
	private ArrayList<Node> fellowNodes;
	
	public SelfNode(String nodeName, String IPaddress, Controller controller) {
		super(nodeName, IPaddress);
		this.controller = controller;
		fellowNodes = new ArrayList<Node>();
		
		// server for receiving all live transactions
		transactServer = new TransactionServer(this);
		transactServer.start();
		
		// server for receiving log files of updating transactions done offline
//		LogServer logServer = new LogServer();
//		logServer.run(); // TODO: implement log server
	}
	
	public ArrayList<ResultSet> transactLocal(Transaction t) throws IOException {
		ArrayList<ResultSet> rs = new ArrayList<ResultSet>();
		for (String q : t.getQueries()) {
			rs.add(controller.getResultSet(q));
		}
		return rs;
	}
	
	public ArrayList<ResultSet> transactGlobal(Transaction t, String serverIP) throws IOException {
		TransactionClient client = new TransactionClient(t, serverIP);
		ArrayList<ResultSet> rs = new ArrayList<ResultSet>();
		for (String q : t.getQueries()) {
			client.run();
			if (!client.isAlive())
				rs.add(controller.getResultSet(q));
		}
		return rs;
	}
	
	public void implementLog(ArrayList<Transaction> transactions, String serverIP) throws IOException {
		TransactionClient client;
		for (Transaction t : transactions) {
			client = new TransactionClient(t, serverIP);
			client.run();
		}
	}
	
	public String getConnectionStatus() {
		if (transactServer.isAlive())
			return "Online";
		else return "Offline";
	}
	
	public ArrayList<Node> getFellowNodes() {
		return fellowNodes;
	}

	public void addFellowNode(Node fellowNode) {
		fellowNodes.add(fellowNode);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
}

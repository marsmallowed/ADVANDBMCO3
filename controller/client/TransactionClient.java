package controller.client;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import model.beans.Transaction;

public class TransactionClient extends Thread {
	private Transaction t;
	private String serverIP;
	private ArrayList<ResultSet> rs;
	
	public TransactionClient(Transaction t, String serverIP) {
		this.t = t;
		this.serverIP = serverIP;
	}
	
    public void run() {
        Socket clientSocket;
		try {
			clientSocket = new Socket(serverIP, 4);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
	        // pass transaction to server
	        outToServer.writeObject(t);
	        
	        ObjectInputStream inFromServer = 
	            new ObjectInputStream(clientSocket.getInputStream());
	        
	        // get result set from server
	        ArrayList<CachedRowSet> crsList = (ArrayList<CachedRowSet>) inFromServer.readObject();
	        rs = new ArrayList<ResultSet>();
	        ResultSet a;
	        for (CachedRowSet crs : crsList) {
	        	try {
					rs.add(crs.getOriginal());
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        
	        clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public ResultSet getResultSet(int i) {
    	return rs.get(i);
    }
}

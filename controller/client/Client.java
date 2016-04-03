package controller.client;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;

import model.Transaction;

public class Client extends Thread {
	private Transaction t;
	private String serverIP;
	
	public Client(Transaction t, String serverIP) {
		this.t = t;
		this.serverIP = serverIP;
	}
	
    public void run() {
        Socket clientSocket;
		try {
			clientSocket = new Socket(serverIP, 1234);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
	        // pass transaction to server
	        outToServer.writeObject(t);
	        
	        ObjectInputStream inFromServer = 
	            new ObjectInputStream(clientSocket.getInputStream());
	        
	        // get result set from server
	        ResultSet rs = (ResultSet) inFromServer.readObject();
	        clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

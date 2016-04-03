package controller.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

import model.Node;
import model.Transaction;

public class Server extends Thread {
	private Transaction clientTransaction;
	
	public void run() {
		try {
			ServerSocket welcomeSocket = new ServerSocket(1234);
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();
//				BufferedReader fromClient =
//						new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				
				// read and store the transaction object from client
				ObjectInputStream inFromClient =
						new ObjectInputStream(connectionSocket.getInputStream());
				// TODO: add checker if transaction granted (consider current locks)
				// TODO: add real time for lockout
				Transaction t = (Transaction) inFromClient.readObject();

				// TODO: call the connection or execution of query and put 
				ResultSet rs = null;
				// return result set to client
				ObjectOutputStream outToClient =
						new ObjectOutputStream(connectionSocket.getOutputStream());
				outToClient.writeObject(rs);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

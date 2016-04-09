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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import controller.db.Controller;
import model.beans.SelfNode;
import model.beans.Transaction;

public class TransactionServer extends Thread {
	private SelfNode self;
	
	public TransactionServer(SelfNode self) {
		this.self = self;
	}
	
	public void run() {
		try {
			ServerSocket welcomeSocket = new ServerSocket(4);
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();

				// read and store the transaction object from client
				ObjectInputStream inFromClient =
						new ObjectInputStream(connectionSocket.getInputStream());
				// TODO: add checker if transaction granted (consider current locks)
				// TODO: add real time for lock timeout
				Transaction t = (Transaction) inFromClient.readObject();

				// TODO: call the connection or execution of query and put RS
				ArrayList<ResultSet> rs = self.transactLocal(t);
				CachedRowSet crs;
				
				ArrayList<CachedRowSet> crsList = new ArrayList<CachedRowSet>();
				
				for (ResultSet a : rs) {
					try {
						crs = new CachedRowSetImpl();
						crs.populate(a);
						crsList.add(crs);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				// return result set to client
				ObjectOutputStream outToClient =
						new ObjectOutputStream(connectionSocket.getOutputStream());
				outToClient.writeObject(crsList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

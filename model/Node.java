package model;

import java.io.*;
import java.net.*;

import controller.client.Client;
import controller.server.Server;

public class Node {
	private String IPaddress;
	
	public void startServer() {
		Server server = new Server();
		server.run();
	}
	
	public void transactGlobal(Transaction t, String serverIP) throws IOException {
		Client client = new Client(t, serverIP);
		client.run();
	}
}

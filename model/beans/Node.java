package model.beans;

import java.util.ArrayList;

import controller.db.Controller;
import controller.server.LogServer;
import controller.server.TransactionServer;

public class Node {
	private String nodeName;
	private String IPaddress;
	
	public Node(String nodeName, String IPaddress) {
		this.nodeName = nodeName;
		this.IPaddress = IPaddress;
	}
	
	public String getNodeName() {
		return nodeName;
	}

	public String getIPaddress() {
		return IPaddress;
	}
}

package controller;

import model.Lock;
import model.Node;

public class Driver {
	public static void main(String[] args) {
		Node self = new Node();
		self.startServer();
	}
}

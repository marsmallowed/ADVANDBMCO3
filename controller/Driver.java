package controller;

import java.net.Socket;

import controller.db.Controller;
import model.beans.Lock;
import model.beans.Node;
import model.beans.SelfNode;
import view.MainFrame;

public class Driver {
	public static void main(String[] args) {
		Controller controller = new Controller();
		
		// self => central
		// TODO: get IP address of this PC and instantiate in self
		SelfNode self = new SelfNode("CENTRAL", "192.168.1.102", controller);
		
		// TODO: change IP addresses
		Node mar = new Node("MARINDUQUE", "localhost");
		Node pal = new Node("PALAWAN", "localhost");
		
		// add nodes to fellowNodes of self
		self.addFellowNode(mar);
		self.addFellowNode(pal);
		
		MainFrame mf = new MainFrame(controller, self);
		mf.setVisible(true);
	}
}

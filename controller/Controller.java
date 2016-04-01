package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Controller {
	
	private Connector connect = null;
	private String query = null;
	
	public Controller() { }

	public ResultSet getResultSet(ArrayList<String> constraint) {
		
		//from view kung ano yung constraint nita lagay sa arraylist at tama dapat sunod sunod niya base sa query
		return connect.executeQuery(query, constraint);
	}
	
}

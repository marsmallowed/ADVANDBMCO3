package controller.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.db.QueryStatement;
import view.MainFrame;


public class Controller {
	
	private Connector connect = null;
	private MainFrame mf;
	private ArrayList<QueryStatement> queryList;
	
	public Controller() {
		connect = new Connector("jdbc:mysql://localhost:3306/", "db_hpq",  "com.mysql.jdbc.Driver", "root", "p@ssword");
		queryList = new ArrayList<QueryStatement>();
	}
	
	public ResultSet getResultSet(String q) {
		return connect.executeQuery(q);
	}
	
	public long recordTime(String q) {
    	long startTime = System.currentTimeMillis();
    	getResultSet(q);
    	
    	return (System.currentTimeMillis() - startTime);
    }
	
	public Long[] getHpqID() {
		ResultSet rs = getResultSet("SELECT DISTINCT hpq_hh_id FROM hpq_crop");
		Long[] hpq_id = null;
		
		try {
			rs.last();
			int numIDs = rs.getRow();
			rs.beforeFirst();
			
			hpq_id = new Long[numIDs];
			int ctr = 0;
			
			while (rs.next()) {
				hpq_id[ctr] = Long.parseLong(rs.getString(1));
				ctr++;
			}
			
			return hpq_id;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return hpq_id;
		}
	}
}

package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connector {

	private static String url;
    private static String dbName;
    private static String driver;
    private static String userName;
    private static String pass;
	
    public Connector(String url, String dbName, String driver, String userName, String pass){
        this.url = url;             //"jdbc:mysql://localhost:3306/"; 
        this.dbName = dbName;       //"CkeckUp";
        this.driver = driver;       //"com.mysql.jdbc.Driver"; 
        this.userName = userName;   //"root"; 
        this.pass = pass;           //"root";
    }
    
    public Connection getConnection(){	 
        Connection conn = null;
	try {
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(url + dbName, userName, pass);
	} catch (Exception e) {
            e.printStackTrace();
	}
	return conn; 
    }
    
    public void executeStatement(String statement){
        Connection conn = this.getConnection();
    	ResultSet res = null;
    	try {
                Statement st = conn.createStatement();
                st.execute(statement);
                conn.close();
    	} catch (SQLException e) {
                e.printStackTrace();
                System.out.println("DB error");
    	}
    }
    
    public ResultSet executeQuery(String query, ArrayList<String> st)
    {
//        try {
//            Statement st = conn.createStatement();
//            result = st.executeQuery(query);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
    	ResultSet rs = null;
    	Connection conn = this.getConnection();
    try
	{
		PreparedStatement statement;
		
		statement = conn.prepareStatement(query);
		if(!st.isEmpty() && !st.get(0).matches(""))
		{
			for(int a = 0; a < st.size(); a++)
			{
				statement.setString(a+1, st.get(a));
			}
		}
		rs = statement.executeQuery();
	}
	catch (SQLException e)
	{
		Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, e);
	}
    
    return rs;
    }
}

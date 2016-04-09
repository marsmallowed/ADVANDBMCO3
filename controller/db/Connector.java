package controller.db;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
    
    public ResultSet executeQuery(String query)
    {
            ResultSet result = null;
            Connection conn = this.getConnection();
        try {
            Statement st = conn.createStatement();
            result = st.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ResultSet callSP(String statement)
    {
    	Connection conn = this.getConnection();
    	ResultSet rs = null;
	    	
	    	// call SP
	    	if(statement.contains("{")) {
	    		CallableStatement callableStatement = null;
		        try {
		        	callableStatement = conn.prepareCall(statement);
		        	rs = callableStatement.executeQuery();
		        	
		        	conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		            System.out.println("DB error");
		        }
	    	}
	    	
	    return rs;
	    	
    }

    public void executeSP(String statement){
    	
    	Connection conn = this.getConnection();
    	
    	// create SP
    	if(!statement.contains("{")){
	        Statement stmt = null;
	        try {
	        	stmt = conn.createStatement();
	        	stmt.execute(statement);
		    	conn.close();
		    }
	        catch(SQLException e) {
	        	System.err.println("SQLException: " + e.getMessage());
		    }
    	}
    }
}

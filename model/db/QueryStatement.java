package model.db;

public class QueryStatement {

	private String title;
	private String query;
	private boolean createdStoredPro;
	private String createStoredPro;
	private String storedPro;
	private boolean createdIndex;
	
	public QueryStatement(String t, String q, String s, String s1)
	{
		title = t;
		query = q;
		createdStoredPro = false;
		createStoredPro = s;
		storedPro = s1;
		createdIndex = false;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getQuery()
	{
		return query;
	}
	
	public boolean getCreatedStoredPro()
	{
		return createdStoredPro;
	}
	
	public String getCreateStoredPro()
	{
		return createStoredPro;
	}
	
	public void setCreatedStoredPro(boolean b)
	{
		createdStoredPro = b;
	}
	
	public String getStoredPro()
	{
		return storedPro;
	}
	
	public boolean getCreatedIndex()
	{
		return createdIndex;
	}
	
	public void setCreatedIndex(boolean b)
	{
		createdIndex = b;
	}
}

package instagram;
import java.sql.*;
import java.util.ArrayList;

import instagram.Commands;  
public class Sql2 {
	public  Connection con;
	public static  Statement stmt;  
	public  int  usersnum;
    public static String table1;
	public static void main(String[] args) throws SQLException {
		
			Sql2 s=new Sql2("worldusers");
			s.dopli();
			
			 
	}
		
	

public Sql2(String table) throws SQLException
{      
	
	  
	            table1=table;
	            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dirot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
	            		,"root","1234");  
				stmt=con.createStatement();  
				String sql="SELECT count(*) FROM roey.bla";
				sql=sql.replaceAll("bla", table1);
				ResultSet rs=stmt.executeQuery(sql); 
				
				 
				while(rs.next())
				{
					usersnum=Integer.valueOf(rs.getString(1));
					System.out.println(usersnum);
				}
	
}

public void tenlist (int set) throws SQLException
{
	
	String sql="select count(user) from (SELECT user FROM roey.worldusers limit "+set+" ,10 )as v ";
	sql=sql.replaceAll("worldusers", table1);
	
		ResultSet rs=stmt.executeQuery(sql);
		
		  if(!rs.next())
		  {
			  System.out.println("null");return;
		  }
		  
		    Newcomment.users=new ArrayList<String>();
			sql="SELECT * FROM roey.worldusers limit "+set+",10 ";
			sql=sql.replaceAll("worldusers", table1);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				Newcomment.users.add(rs.getString(2));
			}
		
		  
	
	
	
}


public void dopli() throws SQLException
{
	Sql2 bla=new Sql2("worldusers");
	int i=102740;
	while(i<bla.usersnum)
	{
		///---create list of 10 users from the i rows
		bla.tenlist(i);
		//------------------
		int b=Commands.users.size();
		int counter=0;
		//------comment
		String nomi="";
		if(b!=0)
		{
		
			
				  i=i+1000;
				  for(int x=0;x<b;x++)
				  {
					  insertname(Commands.users.get(x).toString());
					 
				  }
				  System.out.println(i);
			 
		}
		//-----comment
		 
		Commands.users=new ArrayList<>();
		
		
	}
    
}
/////--------Pull sql part----------
public void insertname(String uname) 
{
	String sql="INSERT INTO roey.worldusers1  VALUES (current_timestamp(),\"blom\") ";
	
	sql=sql.replaceAll("blom", uname);
	
	 try {
		stmt.execute(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("######--Already exsit--######");
	}
		
}
public void insertid(String uname) 
{
	String sql="INSERT INTO roey.usersid  VALUES (\"fv\") ";
	
	sql=sql.replaceAll("fv\"", uname+"\"");
	
	 try {
		stmt.execute(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("######--Already exsit2--######");
	}
		
}
public static void Status() throws SQLException
{  
	
	String sql="select count(*) from roey.worldusers ";
	sql=sql.replaceAll("worldusers", table1);
	
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
	System.out.println("======================STATUS:"+rs.getString(1)+"========================");
		}
}
public static int logtablesize() throws SQLException
{
	String sql="SELECT count(*) FROM roey.logs where username  not in (select username from roey.usedlogs) ";
	ResultSet rs=stmt.executeQuery(sql); 
	
	 
	while(rs.next())
	{
		return Integer.valueOf(rs.getString(1));
		
	}
  return 0;
}
public static int gettablesize(String table) throws SQLException
{
	String sql="SELECT count(*) FROM roey.logs ";
	sql=sql.replaceAll("logs", table);
	ResultSet rs=stmt.executeQuery(sql); 
	
	 
	while(rs.next())
	{
		return Integer.valueOf(rs.getString(1));
		
	}
  return 0;
}
public static void createloglist(String startlog,String jump) throws SQLException

{
	
	String sqlx="SELECT * FROM roey.logs where username  not in (select username from roey.usedlogs) order by mydate limit "+startlog+","+jump;
	ResultSet rs=stmt.executeQuery(sqlx);
	while(rs.next())
	{
		Comment.logusers.add(rs.getString(2)+","+rs.getString(3));
	}


}

}
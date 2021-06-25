package instagram;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import http.Httpreq;

public class Commands {
	/*
	 -DproxySet=true

-DproxyHost=127.0.0.1

-DproxyPort=8888

-Djavax.net.ssl.trustStore=C:\Users\roeysdomi\Desktop\tempsave\FiddlerKeystore

-Djavax.net.ssl.trustStorePassword=12345678
	 */
	
	
	public static String myip="";
	public static String lastip="";
	public static int limit=5;
	public static int counter=1;

	//-------------------------------------
	public static Httpinsta hpr;
	public static ArrayList users; 
	public static int speed=8;
	
	    public static void main(String args[]) throws Exception {
	    	
	    	
			  
		 Commands co=new Commands("worldusers1");
		 if(myip.equals("no")) {return;}
		 co.Login("roeysdomiii", "21232125");
		 
		co.startcomment("1784481816399175523");
		
		
		
		 
		 
		
	   

	  }
	
	public Commands(String table) throws SQLException, IOException, InterruptedException
	
	{
		
		 myip=theipnow();
		 lastip=theipnow();
		 System.out.println("YOUR IP: "+myip);
		   setprop();
		hpr=new Httpinsta(table);
		
	}
    public static void setprop()
    {
    	System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\roeysdomi\\Desktop\\tempsave\\FiddlerKeystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
    }
	public void Login(String user,String pass) throws IOException, InterruptedException
	
	{
		String web="instagram.com";
		hpr.getreq(web);
		web="instagram.com/accounts/login/ajax";
		hpr.postreq(web,"username="+user+"&password="+pass+"&queryParams=%7B%7D");
		 web="instagram.com";
		hpr.getreq(web);
		
		
	}
    public void comment(String id,String text) throws IOException
    {
    	String z="instagram.com/web/comments/"+id+"/add";
    	hpr.postcomment(z, "comment_text="+text);
    }
    public void startcomment(String idpic) throws IOException, SQLException, InterruptedException
    {
    	Sql2 bla=new Sql2("worldusers1");
    	
    	int i=Integer.valueOf(checklastpic(idpic));
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
			nomi=" #heyyyyyyyyyyy "+" @"+
			Commands.users.get(0)+" @"+
			Commands.users.get(1)+" @"+
			Commands.users.get(2)+" @"+
			Commands.users.get(3)+" @"+
			Commands.users.get(4)+" @"+
			Commands.users.get(5)+" @"+
			Commands.users.get(6);
			
			}
			//-----comment
			  if(!nomi.equals("")&okbyip())
			  { 
				  i=i+10;
				  counter++;
				comment(idpic, nomi);
                 updatelastpic(i,idpic);
               
			  }
			Commands.users=new ArrayList<>();
			 hpr.randomtime();
			
		}
	    
    }
  
    public static String theipnow() throws InterruptedException 
    {
    	URL url=null;
		try {
			url = new URL("http://checkip.amazonaws.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in the ip now func");
			TimeUnit.SECONDS.sleep(1);
			return "no";
			
		}
    	BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error in the ip now func");
			TimeUnit.SECONDS.sleep(1);
			return "no";
			
		}
    	try {
			System.out.println(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error in the ip now func");
			TimeUnit.SECONDS.sleep(1);
			return "no";
		}
    	
    	InputStream output=null;
		try {
			output = url.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error in the ip now func");
			TimeUnit.SECONDS.sleep(1);
			return "no";
		}
	    Scanner s = new Scanner(output).useDelimiter("\\A");
	    String result =".";
	    while (s.hasNext())
	    {
	    	
	    		result=s.next();	    		
	    }
	    result=result.replaceAll("\\n","");
	  return result;
    }
    public static boolean okbyip() throws InterruptedException 
    {
    	TimeUnit.SECONDS.sleep(1);
    	String current=lastip;
		current = theipnow();
		if(current.equals("no")) {return false;}   ///אם אין אינטרנט
    	
		/* שונה מהאייפי המקורי
		 * יש אינטרנט
		 * ומתחת להגבלה
		 * */
		if(!current.equals(myip)&counter<limit)
    	{
			System.out.println("ifnum1  myip:"+myip+" lastip:"+lastip+" current :"+current);
			
    		lastip=current;
    		return true;
    	}
		/* יש אינטרנט
		 * כנראה האייפי המקורי
		 * או שהגיע להגבלה
		 * */
    	else if(!current.equals(lastip))
    	{
    		System.out.println("ifnum2  myip:"+myip+" lastip:"+lastip+" current :"+current);
    		counter=1;
    		return true;
    	}
    	else
    	{
    		System.out.println("ifnum3  myip:"+myip+" lastip:"+lastip+" current :"+current);
    		return false;
    	}
    }
    public static void updatelastpic(int i,String idpic)
    {
    	String sql="update roey.statuspic set value1='2'  where key1='lastpic'";
    	sql=sql.replaceAll("2", String.valueOf(i)).replaceAll("lastpic", idpic);
    	try {
			hpr.sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("cant update lastpic");
			e.printStackTrace();
		}
    	
    }
    public static String checklastpic(String idpic) throws SQLException, IOException, InterruptedException
    {
    	String sql="insert into  roey.statuspic values(\"pic1\",\"0\" )";
    	sql=sql.replaceAll("pic1", idpic);
    	try {
			hpr.sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("picalready exsist");
			e.printStackTrace();
		}
    	////-----------check the latest result--------
    	String sqlox="SELECT value1 FROM roey.statuspic where key1=\"thei\"";
 	   sqlox=sqlox.replaceAll("thei", idpic);
 	   ResultSet rs=null;
		try {
			rs = hpr.sq.stmt.executeQuery(sqlox);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
 		
 		 
 		if(rs.next())
 		{
 			System.out.println(rs.getString(1));
 			return rs.getString(1);
 		}
 	  
    	
    	return "0";
   }
}

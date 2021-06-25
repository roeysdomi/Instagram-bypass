package instagram;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import http.Httpreq;

public class Httpinsta extends Httpreq {
	public CookieManager ckman = new CookieManager();  
	public static String token ="";
	public static String hash ="";
	public static String nextpage ="";
	public static String id="";
	
	public static Sql2 sq;

	public ArrayList users=new ArrayList<String>();
	
	
	public Httpinsta(String table) throws SQLException
	{
		 Commands.setprop();
		CookieHandler.setDefault(ckman);
		 sq=new Sql2(table);
	}
	
	public   void getreq(String enterurl) throws IOException
	  {
		    String https_url ="https://www."+enterurl+"/";
		    
		    URL url = new URL(https_url);
		    
		    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		    
		    headers(connection, https_url,"GET");
		  
		    connection.getContent();
		
		    //-----cookies
		    cookies();
		    
		    
		    //-----getresponse------
		    
            respondmaker(connection);
		   
		   
		    //------------------
		 }
	public   void postreq(String enterurl,String body) throws IOException
	  {
		
		   String https_url ="https://www."+enterurl+"/";
		   
	        URL url = new URL(https_url);
		    
		    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		  //-----headers------
            headers(connection,https_url,"POST");
  		  //-----headers------
		    connection.addRequestProperty("X-CSRFToken",token);
		    
		    
            //-----body------
		    body(connection, body);
            //-----body--------
		    
		    connection.getContent();
		    
		    
		    //-----cookies   
		    cookies();
		   //-----cookies
		    
		    
		    

		 }
    public   void postcomment(String enterurl,String body) 
    {
    	String https_url ="https://www."+enterurl+"/";
	    
        URL url=null;
		try {
			url = new URL(https_url);
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
	    HttpsURLConnection connection=null;
		try {
			connection = (HttpsURLConnection)url.openConnection();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    headers(connection,https_url,"POST");

	    connection.addRequestProperty("X-CSRFToken",token);

	    try {
			body(connection, body);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    try {
			connection.getContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    

	   
    }
    public   void pullids_followers() throws IOException, InterruptedException, SQLException
    {
      try {
    	 
    	    String https_url="";
    	    
    	   hash="7dd9a7e2160524fd85f50317462cff9f";
    	    if(!nextpage.equals(""))
    	    {
    	        https_url="https://www.instagram.com/graphql/query/?query_hash=mako&variables={\"id\":\"theid\",\"include_reel\":false,\"first\":12,\"after\":\"nextp\"}" ;
			    https_url=https_url.replaceAll("theid", id);
			    https_url=https_url.replaceAll("mako", hash);
			    https_url=https_url.replaceAll("nextp", nextpage);
    	    }
    	    else
    	    {
    	    	https_url="https://www.instagram.com/graphql/query/?query_hash=mako&variables={\"id\":\"theid\",\"include_reel\":false,\"first\":1000}" ;
    	    	https_url=https_url.replaceAll("theid", id);
			    https_url=https_url.replaceAll("mako", hash);
    	    }
		    URL url = new URL(https_url);
		    
		    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		    
		    headers(connection, https_url, "GET");
		  
		    connection.getContent();
		    //-----cookies
		    
		    cookies();
		    //-----getresponse------
		    String result=respondmaker(connection);
		     ///----------------
		    
            userfinder(result);
            Commands.counter++;
            
            ///-----start over after random time
		   
		    randomtime();
		    
		   
         }
    	///------in case there is an error continue after 20 sec.
        catch(IOException e)
         {
        	System.out.println(e);
        	TimeUnit.SECONDS.sleep(4);
		   
         }
    	
		    //------------------
    	
    }
///////////------------new version------------
    public void headers(HttpsURLConnection connection,String https_url,String type)
    {
    	    try {
				connection.setRequestMethod(type);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				System.out.println("problem in the headers");
			}
		   // connection.addRequestProperty(" origin", https_url);	
    	    System.setProperty("http.KeepAlive", "true");
		    connection.addRequestProperty("Upgrade-Insecure-Requests", "1");	
		    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");	
		    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
		    connection.addRequestProperty("Referer", https_url);	
		    connection.addRequestProperty("Accept-Encoding", "");	
		    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");
		    //connection.addRequestProperty("Cookie",cook);	
		    connection.addRequestProperty("Connection","keep-alive");	

    }
    public void cookies()
    {
        CookieStore ckStore = ckman.getCookieStore();
	    List<URI> uriList=ckStore.getURIs();
	    
	    List<HttpCookie> cks = ckStore.getCookies();
	   
	    for (HttpCookie ck : cks) {
	    	if(ck.toString().contains("csrftoken=")||ck.toString().contains("id=")) 
	    	{  
	    		if(ck.toString().contains("csrftoken="))
	    		{
	    		token=ck.toString();
	    		token=token.replaceAll("csrftoken=", "");
	    		}
	    		
	    	}
	    	
	    }
	    
    }
    public String respondmaker(HttpsURLConnection connection) throws IOException
    {
    	 InputStream output = connection.getInputStream();
		    Scanner s = new Scanner(output).useDelimiter("\\A");
		    String result =".";
		    while (s.hasNext())
		    {
		    	
		    		result=s.next();	    		
		    }
		  return result;
    	
    	    	
    	
    }
    public void body(HttpsURLConnection connection,String body) throws IOException
    {
    	connection.setDoOutput(true);
	    String str =  body;
	    byte[] outputInBytes = str.getBytes("UTF-8");
	    OutputStream os = connection.getOutputStream();
	    os.write(outputInBytes);    
	    os.close();

    }
    public void userfinder(String result) throws SQLException
    {
    	   if(!result.equals(""))
		   {     
			     if(result.contains("has_next_page\":false")) {System.out.println();}
			     if(result.contains("true,\"end_cursor"))
			    	 
		        {
			    	 nextpage=result.substring(result.indexOf("end_cursor"), result.indexOf("\"}"));
			    	 nextpage=nextpage.replaceAll("end_cursor\":\"", "");
			    	 String sqlex="UPDATE roey.follow SET token = \"ltok\" WHERE (`idfollow` = \"theid\") ;";
			    	 sqlex=sqlex.replaceAll("theid", id);
			    	 sqlex=sqlex.replaceAll("ltok", nextpage);

			    	 sq.stmt.executeUpdate(sqlex);
			    	 System.out.println("this is next page="+nextpage);
			    }
			     
			      int i= result.indexOf("username");
			      while(result.indexOf("username",i)>0&i<result.length()&result.indexOf(",",i)>0)
			        {
			      	   int remember=result.indexOf("username",i);
			      	   int other =result.indexOf(",",i);
			      	  
			      	   String clear=result.substring(result.indexOf("username\":\"",i),result.indexOf(",",i));
			      	   clear=clear.replace("username\":\"", "");
			      	   clear=clear.replace("\"", "");
			      	   clear=clear.replace("}", "");
			      	   System.out.println(clear);
			      	   ///---insert to db---
			      	   sq.insertname(clear); 
			      	   ///---insert to db---
			      	  i=result.indexOf("username",i+1);
			      	  int other2=result.indexOf(",",i);
			      	  if(!(other<other2)) {break;}
			      	  if(i<remember) {break;}
			      	

			      	  
			        }
			      Sql2.Status();
		    
		   }
    }
    
    public static void randomtime() throws InterruptedException
    {
    	 Random rn = new Random();
		    int time =rn.nextInt(Commands.speed - 2 + 1) + 2;
		    TimeUnit.SECONDS.sleep(time);
    }


}

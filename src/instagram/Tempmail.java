package instagram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class Tempmail {
	public static CookieManager ckman1 = new CookieManager(); 
	 public static String  res="";
	 public static Sql2 sq;
	 public String firstcookie="";
	 public String currentuser="";
	 public String cookie4sql="";
 	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
        Tempmail t=new Tempmail();
        while(true)
        {
         t.confirmails();
         TimeUnit.SECONDS.sleep(3);
         System.out.println("again");
        }
	}

	
	
	public Tempmail() throws SQLException
	{
		CookieHandler.setDefault(ckman1);
		setprop();
		sq=new Sql2("worldusers1");
	}
	public Tempmail(String emailname) throws SQLException
	{
		CookieHandler.setDefault(ckman1);
		setprop();
		sq=new Sql2("worldusers1");
		try {
			getreq("https://tempmail.ws/user.php?user="+emailname+"%40tempmail.ws");
	
		
		
		String sql="insert into roey.email2confirm values (\"bla\")";
		
		sql=sql.replaceAll("bla", emailname);
		sq.stmt.executeUpdate(sql);
		}
		catch (Exception e) {
			System.out.println("didnt create mail");
			return;
		}
	}
	public  void  confirmails()
	{
	   	String sqlox="SELECT * FROM roey.email2confirm ";
	 	  
	 	   ResultSet rs=null;
			try {
				rs = sq.stmt.executeQuery(sqlox);
			
	 		
	 		 
		 		while(rs.next())
		 		{
		 			System.out.println(rs.getString(1));
		 			
		 			rs=confirm1mail(rs.getString(1), rs);
		 			
		 			
		 		}
		 		
             } catch (SQLException e) {
				
				System.out.println("error inc confirmmails");
				
			} 
	 		
	}

	
	 public  ResultSet  confirm1mail(String mail,ResultSet d) throws SQLException
	{
		 try {
		
		currentuser=mail;
		getreq("https://tempmail.ws/user.php?user="+mail+"%40tempmail.ws");
		
		getreq("https://tempmail.ws/mail.php");
		
		
		if(res.contains("instagram"))
		   {
			   
			   
			   getfirstcookie(mail);
			int start=res.indexOf("https://instagram.com/accounts/confirm_email/");
			int end=res.indexOf("?app_redirect=False",start);
			System.out.println(res.substring(start,end));
			getreq(res.substring(start,end)+"?app_redirect=False");
			///-----------deletefrom sql
			String sql="delete  FROM roey.email2confirm where mail like '%monkey%'";
			
			sql=sql.replaceAll("monkey",mail);
			Sql2 sqi=new Sql2("worldusers1");
			sqi.stmt.executeUpdate(sql);
			
			 firstcookie="";
			 String sqlox1="SELECT * FROM roey.email2confirm ";
			 d = sq.stmt.executeQuery(sqlox1);
			 ckman1 = new CookieManager(); 
	 		 CookieHandler.setDefault(ckman1);
			
		  }
		
		 }catch (Exception e) {
			System.out.println("didnt confirm mail:"+mail);
			return d;
		}
		return d;
		
	}
	//------------basic-----------
	 public    void getreq(String enterurl) 
	  {
   	try {
		     String https_url =enterurl;
		    
			    if(enterurl.contains("temp"))
			    {   https_url =enterurl; }
			    URL url=null;
				
					url = new URL(https_url);
		    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();			
		    headers(connection, https_url,"GET");		  		   
				connection.getContent();	
		    //-----cookies
		   cookies();
		    //-----getresponse------
		    
           String result=null;
	    	result = respondmaker(connection);
		    res=result;
		   
        
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error get");
			
			return;
		}
		   //------------------
	 }


	////////////----------------------------------------------------------
	
	  public  String cookies()
	  {
	      CookieStore ckStore = ckman1.getCookieStore();
		    List<URI> uriList=ckStore.getURIs();
		   
		    List<HttpCookie> cks = ckStore.getCookies();
		    String cr="";
		    
		      
		    for (HttpCookie ck : cks) {
		    	if(ck.toString().contains("csrftoken=")||ck.toString().contains("id=")) 
		    	{  
		    		
		    	}
		    	cr=cr+";"+ck.toString();
		    	
		    	
		    }
		   return cr;
		    
		    
	  }
	  public  String respondmaker(HttpURLConnection connection) 
	  {
	  	 InputStream output=null;
		try {
			output = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			    Scanner s = new Scanner(output).useDelimiter("\\A");
			    String result =".";
			    while (s.hasNext())
			    {
			    	
			    		result=s.next();	    		
			    }
			  return result;
	  	
	  	    	
	  	
	  }
	  public void body(HttpURLConnection connection,String body) 
	  {
	  	connection.setDoOutput(true);
		    String str =  body;
		    byte[] outputInBytes=null;
			try {
				outputInBytes = str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    OutputStream os=null;
			try {
				os = connection.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				os.write(outputInBytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		    try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	  }
	 //////////----------------
	  public static void setprop()
	  {
	  	System.setProperty("http.proxyHost", "127.0.0.1");
	      System.setProperty("https.proxyHost", "127.0.0.1");
	      System.setProperty("http.proxyPort", "8888");
	      System.setProperty("https.proxyPort", "8888");
	      System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\roeysdomi\\Desktop\\tempsave\\FiddlerKeystore");
	      System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
	  }
	 
	
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
			    if(!firstcookie.equals(""))
			    {
			    	
			       connection.addRequestProperty("Cookie",firstcookie);	
			    }
			    //connection.addRequestProperty("Connection","close");
			    connection.setConnectTimeout(1);

	  }
      
       public void getfirstcookie(String user) throws SQLException
       {
    	   String sqlox="SELECT cookies FROM roey.logs where username=\"thei\"";
     	   sqlox=sqlox.replaceAll("thei", user);
     	   ResultSet rs=null;
    		try {
    			rs = sq.stmt.executeQuery(sqlox);
    		
     		 
     		if(rs.next())
     		{
     			
     			if(rs.getString(1).contains("ds_user_id="))
     			{firstcookie= rs.getString(1);}
     		}
     		
             } catch (SQLException e) {
    			
    			e.printStackTrace();
    		} 
     		
     	  
        	
       }
       public static String checklastcookies(String username,String cookies) throws SQLException,  InterruptedException
       {
       	String sql="insert into  roey.email2confirm values(\"tsuriel\",\"sdomi\" )";
       	sql=sql.replaceAll("tsuriel", username).replaceAll("sdomi", cookies);
       	try {
   			sq.stmt.executeUpdate(sql);
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			System.out.println("has already cookies");
   			
   		}
       	////-----------check the latest result--------
       	String sqlox="SELECT cookies FROM roey.email2confirm where mail=\"thei\"";
    	   sqlox=sqlox.replaceAll("thei", username);
    	   ResultSet rs=null;
   		try {
   			rs = sq.stmt.executeQuery(sqlox);
   		} catch (SQLException e) {
   			
   			e.printStackTrace();
   		} 
    		
    		 
    		if(rs.next())
    		{
    			
    			
    			{return rs.getString(1);}
    		}
    	  
       	
       	return "";
      }
       public static void updatelastcookie(String cok,String use) throws SQLException
       {
       	
       	String sql="update roey.email2confirm set cookies='sdomi'  where mail='lastpic'";
       	sql=sql.replaceAll("sdomi", cok).replaceAll("lastpic", use);
       	try {
   			sq.stmt.executeUpdate(sql);
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			System.out.println("cant update mail");
   			e.printStackTrace();
   		}
       	
       }

}

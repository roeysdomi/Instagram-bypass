package instagram;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import lock.MultipartUtility;

public class Create {
	public static CookieManager ckman = new CookieManager(); 
	public static long timest =0;
	public static String token ="";
	public static String ajax ="";
	public static String myip="";
	public static String lastip="";
	public static int limit=2;

	public static int counter=1;	
	public static ArrayList users; 
	public static int speed=8;
	public static String user4cookies="";
	public static Sql2 sq;
	public static boolean created=false;
    public static String  res="";
    public static String  cookiecreate="";
	//---------------------
   
	
 	public static void main(String[] args) throws SQLException,  InterruptedException, IOException {
 		
 		
 		Create co=new Create();
 		//co.selen();
 		co.getreq("tempmail.ws/mail.php?unseen=2");
	   /*
		Create co=null;
		try {
			co = new Create("bla");
		
		
		while(true) {
			 if(okbyip())
			 {  ckman  = new CookieManager(); 
				 CookieHandler.setDefault(ckman);
			     co.createuser(co);
			     co.Status();
			 }
		    }
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
	 
		
	}
	
	
public Create() throws SQLException, IOException, InterruptedException
	
	{
	  CookieHandler.setDefault(ckman);
	  setprop();
			
	}
	

	
	public Create(String table) throws SQLException, IOException, InterruptedException
	
	{
		CookieHandler.setDefault(ckman);
		setprop();
		 myip=theipnow();
		 lastip=theipnow();
		 System.out.println("YOUR IP: "+myip);
		
		
		sq=new Sql2("worldusers1");
		
		
	}
/////-------------actions--------
	public void createuser(Create co) throws SQLException, InterruptedException
	{
		try {
		
		     
		      
		      String []fo=randomname();
		
		String pass=randompassw(8);
		System.out.println(fo[0]);
		getreq("instagram.com");
		selen();
		System.out.println("====STARTATTAMPT====");
		
	     fakeatamp(fo, pass);
	     
		co.postreq("instagram.com/accounts/web_create_ajax",
				
				"email="+fo[0]+"%40gmail.com&"
				+ "password="+pass+"&"
				+ "username="+fo[0]+"&"
				+ "first_name="+fo[1]+"&"
				+ "seamless_login_enabled=1&tos_version=row&opt_into_one_tap=false");
		co.getreq("instagram.com");
		
		
		followrandom();
		System.out.println("====FLOLLOWED====");
		if(created) 
		{
			
			String newmail=fo[0];

			
			co.postreq("instagram.com/accounts/edit",
					"first_name="+fo[1]
					+ "&email="+fo[0].toLowerCase()+"%40owlymail.com"
					+ "&username="+fo[0].toLowerCase()
					+ "&phone_number=&gender=3&biography=&external_url=&chaining_enabled=on");
			System.out.println("====EDITED====");
			Tempmail t=new Tempmail(newmail);
			
		  checklastcookies(fo[0], pass, cookiecreate);
		  created=false;
		  
		 
			

		}
		
		counter++;
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
///------------------UPDATE AND CHECK SQL----------------------
   
    public static void updatelastcookie(String cok,String use) throws SQLException
    {
    	
    	String sql="update roey.logs set cookies='2'  where username='lastpic'";
    	sql=sql.replaceAll("2", cok).replaceAll("lastpic", use);
    	try {
			sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("cant update lastpic");
			e.printStackTrace();
		}
    	
    }
    
    public static String checklastcookies(String username,String passw,String cookies) throws SQLException,  InterruptedException
    {
    	String sql="insert into  roey.logs values(moti,\"tsuriel\",\"sdomi\",\"kafe\" )";
    	sql=sql.replaceAll("tsuriel", username).replaceAll("sdomi", passw).replaceAll("kafe", cookies)
    			.replaceAll("moti", "NOW()");
    	try {
			sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("has already cookies");
			
		}
    	////-----------check the latest result--------
    	String sqlox="SELECT cookies FROM roey.logs where username=\"thei\"";
 	   sqlox=sqlox.replaceAll("thei", username);
 	   ResultSet rs=null;
		try {
			rs = sq.stmt.executeQuery(sqlox);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
 		
 		 
 		if(rs.next())
 		{
 			
 			if(rs.getString(1).contains("ds_user_id="))
 			{return rs.getString(1);}
 		}
 	  
    	
    	return "";
   }
///-----------------basics------------------
    public  static  void getreq(String enterurl) 
	  {
    	try {
		     String https_url ="https://www."+enterurl+"/";
		    
			    if(enterurl.contains("temp"))
			    {   https_url ="https://"+enterurl+"/"; }
			    URL url=null;
				
					url = new URL(https_url);
		    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();			
		    headers(connection, https_url,"GET");		  		   
				connection.getContent();	
		    //-----cookies
		   cookiecreate= cookies();
		    //-----getresponse------
		    
            String result=null;
	    	result = respondmaker(connection);
		    res=result;
		   if(result.contains("rollout_hash"))
		   {
			   int i=result.indexOf("rollout_hash");
			  
			   ajax=result.substring(result.indexOf("rollout_hash\":\"",i), result.indexOf(",",i+12));
			   ajax=ajax.replaceAll("rollout_hash\":\"", "").replaceAll("\"", "");
			   System.out.println(ajax);
		   }
         
     	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error get");
			created=false;
			return;
		}
		   //------------------
	 }

	public   void postreq(String enterurl,String body) 
	  {
		try {
		   String https_url ="https://www."+enterurl+"/";
		   if(enterurl.contains("graph"))
		   {
			   https_url ="https://"+enterurl;
		   }
		   
	        URL url=null;
			
				url = new URL(https_url);
			
			
		    
		    HttpsURLConnection connection=null;
			
				connection = (HttpsURLConnection)url.openConnection();
			
		  //-----headers------
		    if(!enterurl.contains("upload"))
		    {
             headers(connection,https_url,"POST");
		    }
		    if(enterurl.contains("upload"))
		    {
		    picheaders(connection,https_url,"POST");
		    }
		  //-----headers------
		    connection.addRequestProperty("X-CSRFToken",token);
		    
		    
          body(connection, body);
		    
		  
				connection.getContent();
			
		    
		    //-----cookies   
		    cookiecreate= cookies();
		   //-----cookies
		    String result=null;
		    result = respondmaker(connection);
			 if(enterurl.contains("accounts")&!result.contains("flag"))
			    {
			    	created=true;
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error post");
			created=false;
			return;
		}
	     
		    

	}
  
///////------------new version------------
  public static void headers(HttpsURLConnection connection,String https_url,String type)
  {
  	    try {
				connection.setRequestMethod(type);
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				System.out.println("problem in the headers");
			}
		   // connection.addRequestProperty(" origin", https_url);
  	       if(https_url.contains("instagram"))
  	       {
  	         System.setProperty("http.KeepAlive", "true");
  	         System.setProperty("https.KeepAlive", "true");
  	        connection.addRequestProperty("Connection","keep-alive");
  	       }
  	     if(!https_url.contains("instagram"))
	       {
	         System.setProperty("http.KeepAlive", "false");
	         System.setProperty("https.KeepAlive", "false");
	         connection.addRequestProperty("Connection","close");
	       }
		    connection.addRequestProperty("Upgrade-Insecure-Requests", "1");	
		    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");	
		    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
		    connection.addRequestProperty("Referer", https_url);	
		    connection.addRequestProperty("Accept-Encoding", "");	
		    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");

		    
		    if(!ajax.equals(""))
		    {
		    connection.addRequestProperty("X-Instagram-AJAX",ajax);
		    }
		    
		    connection.setConnectTimeout(1);
		    	
		    

  }
  public void picheaders(HttpsURLConnection connection,String https_url,String type)
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
		    connection.addRequestProperty("Connection","keep-alive");
		    connection.addRequestProperty("Content-Type","multipart/form-data; boundary=----WebKitFormBoundary74GS1CmIBejxN5Oy");
		    
		    
		    
		    if(!ajax.equals(""))
		    {
		    connection.addRequestProperty("X-Instagram-AJAX",ajax);
		    }
		    
		    connection.setConnectTimeout(1);

  }
  public static String cookies()
  {
      CookieStore ckStore = ckman.getCookieStore();
	    List<URI> uriList=ckStore.getURIs();
	   
	    List<HttpCookie> cks = ckStore.getCookies();
	    String cr="";
	    
	      
	    for (HttpCookie ck : cks) {
	    	if(ck.toString().contains("csrftoken=")||ck.toString().contains("id=")) 
	    	{  
	    		if(ck.toString().contains("csrftoken="))
	    		{
	    		token=ck.toString();
	    		token=token.replaceAll("csrftoken=", "");
	    		}
	    		
	    	}
	    	cr=cr+";"+ck.toString();
	    	
	    	
	    }
	   return cr;
	    
	    
  }
  public static String respondmaker(HttpsURLConnection connection) 
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
  public void body(HttpsURLConnection connection,String body) 
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
 
 
  ////------------ip------------
  public  static String theipnow() throws InterruptedException, SQLException, IOException 
  {   
	  ckman = new CookieManager(); 
		 CookieHandler.setDefault(ckman);
	  
	  Create.getreq("whatsmyip.com");
	  
	  int start=res.indexOf("IP: <span class=\"pull-right\">");
	  int end=res.indexOf("</span>",start);
	  String ip=lastip;
	  try {
	    ip=res.substring(start,end).replaceAll("IP: <span class=\"pull-right\">", "");
	  }
	  catch (Exception e) {
		// TODO: handle exception
	}
 	  return ip;
  }
  public static boolean okbyip() throws InterruptedException, SQLException, IOException 
  {
	  Create co=new Create();
  	TimeUnit.SECONDS.sleep(1);
  	String current=lastip;
		current = co.theipnow();
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
  	else if(!current.equals(lastip)&!current.equals(myip))
  	{
  		System.out.println("ifnum2  myip:"+myip+" lastip:"+lastip+" current :"+current);
  		lastip=current;
  		counter=1;
  		return true;
  	}
  	else
  	{
  		System.out.println("ifnum3  myip:"+myip+" lastip:"+lastip+" current :"+current);
  		return false;
  	}
  }
///////------------extrafunc------------
  public static void setprop()
  {
  	System.setProperty("http.proxyHost", "127.0.0.1");
      System.setProperty("https.proxyHost", "127.0.0.1");
      System.setProperty("http.proxyPort", "8888");
      System.setProperty("https.proxyPort", "8888");
      System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\roeysdomi\\Desktop\\tempsave\\FiddlerKeystore");
      System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
  }
  public static String[] randomname()
  {
	  String name="Bobby,Stacy,Bert,Beau,Hector,Chadwick,Eldon,Ahmad,Melvin,Tracey,Neal,Earnest,Jeffrey,Issac,Garret,Ezekiel,Rogelio,Michel,Phil,Royce,Jorge,Roger,Jarrod,Leon,Russ,Vern,Herman,Adalberto,Alejandro,Donny,Manuel,Howard,Edgardo,Carson,Wilmer,Paris,Randal,Shayne,Walton,Shaun,Andrew,Ira,Bill,Bryant,Jamie,Tad,Britt,Ben,Wiley,Dana"; 
      String []sp=name.split(",");
      Random rand = new Random();
      int a1=rand.nextInt(sp.length-1);
     

	  String finalname=randompassw(4)+sp[a1]+randompassw(3);
	  String []fx= {finalname,sp[a1]};
	  
	  return fx;
  }
  public static void randomtime() throws InterruptedException
  {
 	 Random rn = new Random();
		    int time =rn.nextInt(Commands.speed - 2 + 1) + 2;
		    TimeUnit.SECONDS.sleep(time);
 }
  public static void convertpic() throws MalformedURLException
  {
	  String charset = "UTF-8";
      File uploadFile1 = new File("C:\\Users\\roeysdomi\\Desktop\\tempsave\\testpic.jpg");
     
      String requestURL = "https://www.instagram.com/create/upload/photo/";
     

      try {
          MultipartUtility multipart = new MultipartUtility(requestURL, charset);
           
       
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          timest=timestamp.getTime();
          String strLong = Long.toString(timestamp.getTime());
          multipart.addFormField("upload_id", strLong);
         
          multipart.addFilePart("photo","photo","fileUpload", uploadFile1);
          
          
         

          List<String> response = multipart.finish();
           
          System.out.println("SERVER REPLIED:");
           
          for (String line : response) {
              System.out.println(line);
          }
      } 
      catch (IOException ex)
      {
    	  System.out.println("error pic");
			return;
      }
			  
	  
	  
	  
  }
  public static void profilepic() throws MalformedURLException
  {
	  String charset = "UTF-8";
      File uploadFile1 = new File("C:\\Users\\roeysdomi\\Desktop\\tempsave\\testpic.jpg");
     
      String requestURL = "https://www.textnow.com/api/users/roeytest/messages";
     

      try {
          MultipartUtility multipart = new MultipartUtility(requestURL, charset);
           
       
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          timest=timestamp.getTime();
          String strLong = Long.toString(timestamp.getTime());
          multipart.addFormField("upload_id", strLong);
         
          multipart.addFilePart("profile_pic","profilepic","fileUpload", uploadFile1);
          
          
         

          List<String> response = multipart.finish();
           
          System.out.println("SERVER REPLIED:");
           
          for (String line : response) {
              System.out.println(line);
          }
      } 
      catch (IOException ex)
      {
    	  System.out.println("error profile");
			return;
      }
			  
	  
	  
	  
  }
  public static byte[] extractBytes (String ImageName) throws IOException {
	  // open image
	  
	  File fi = new File(ImageName);
	  byte[] fileContent = Files.readAllBytes(fi.toPath());
	 

	  return fileContent;
	 }  
  public static String randompassw(int len)
  {
	  String AB = "0123456789";
		 SecureRandom rnd = new SecureRandom();

		
		   StringBuilder sb = new StringBuilder( 9 );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
  }
  public void followrandom() throws SQLException, InterruptedException
  {
	  Random r = new Random();
	  int num = r.nextInt(2) + 1;
	  int countfollow=0;
	  
	  String sql="SELECT * FROM roey.usersid ORDER BY RAND() LIMIT "+num;
	  ResultSet rs=sq.stmt.executeQuery(sql);
		
	  if(!rs.next())
	  {
		  System.out.println("null");return;
	  }
	  
	    
		
		rs=sq.stmt.executeQuery(sql);
		while(rs.next()&created)
		{
			//Commands.users.add(rs.getString(1));
			
			postreq("instagram.com/web/friendships/"+rs.getString(1)+"/follow", "");
			System.out.println(countfollow++);
			
			TimeUnit.SECONDS.sleep(2);
			
		}
	  
	  
	  
  }
  public static String Status() throws SQLException,  InterruptedException
  {
  	
  	String sqlox="SELECT count(*) FROM roey.logs ";
	  
	   ResultSet rs=null;
		try {
			rs = sq.stmt.executeQuery(sqlox);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		 
		if(rs.next())
		{
			
			System.out.println("=================USERS AMOUNT:"+rs.getString(1)+"====================");
		}
	  
  	
  	return "";
 }
  public static void  amountip() throws InterruptedException, SQLException, IOException
  {
	  ArrayList b=new ArrayList <String>();
	  String ip=theipnow();
	  while(true)
	  {
		  if(!b.contains(ip))
		  {
			  b.add(ip);
			  System.out.println(ip);
			  for(int i=0;i<b.size();i++)
			  {
			    System.out.println( b.get(i));
			  }
			  System.out.println( "size:"+b.size());
			  
		  }
		  
		  ip=theipnow();
			TimeUnit.SECONDS.sleep(2);
		  
	  }
  }
  public void fakeatamp(String [] fo,String passw) throws InterruptedException
  {
	  postreq("instagram.com/accounts/web_create_ajax/attempt", 
			  "email="+fo[0]+"%40gmail.com&password=&username=&first_name=&opt_into_one_tap=false");
	  TimeUnit.SECONDS.sleep(1);
	  postreq("instagram.com/accounts/web_create_ajax/attempt", 
			  "email="+fo[0]+"%40gmail.com&password=&username="+fo[0]+"&first_name=&opt_into_one_tap=false");
	  TimeUnit.SECONDS.sleep(1);
	  postreq("instagram.com/accounts/web_create_ajax/attempt", 
			  "email="+fo[0]+"%40gmail.com&password=&username="+fo[0]+"&first_name="+fo[1]+"&opt_into_one_tap=false");
	  TimeUnit.SECONDS.sleep(1);
	  postreq("instagram.com/accounts/web_create_ajax/attempt", 
			  "email="+fo[0]+"%40gmail.com&password="+passw+"&username="+fo[0]+"&first_name="+fo[1]+"&opt_into_one_tap=false");
	  TimeUnit.SECONDS.sleep(1);
	  
	  
  }
  public void clientlog()
  {
	  getreq("instagram.com");
	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	  String clock=String.valueOf(timestamp.getTime());
	  String text="access_token=1217981644879628%7C65a937f07619e8d4dce239c462a447ce&message=%7B%22app_id%22%3A%221217981644879628%22%2C%22app_ver%22%3A%221.0.0%22%2C%22data%22%3A%5B%7B%22time%22%3A1535338773.037%2C%22name%22%3A%22instagram_web_time_spent_navigation%22%2C%22extra%22%3A%7B%22app_id%22%3A%221217981644879628%22%2C%22event%22%3A%22transition%22%2C%22client_time%22%3A1535338773037%2C%22time_spent_id%22%3A%221tfrg4%22%2C%22extra_data%22%3A%7B%7D%2C%22source_endpoint%22%3A%22loginPage%22%2C%22dest_endpoint%22%3A%22signupPage%22%2C%22referrer%22%3A%22%22%2C%22referrer_domain%22%3A%22%22%2C%22url%22%3A%22%2F%22%2C%22original_referrer%22%3A%22%22%2C%22original_referrer_domain%22%3A%22%22%7D%7D%2C%7B%22time%22%3A1535338773.038%2C%22name%22%3A%22instagram_web_registration%22%2C%22extra%22%3A%7B%22app_id%22%3A%221217981644879628%22%2C%22event_name%22%3A%22form_load%22%2C%22containermodule%22%3A%22signupPage%22%2C%22fbconnect_status%22%3A%22logged_out%22%2C%22fb_userid%22%3Anull%2C%22referrer%22%3A%22%22%2C%22referrer_domain%22%3A%22%22%2C%22url%22%3A%22%2F%22%2C%22original_referrer%22%3A%22%22%2C%22original_referrer_domain%22%3A%22%22%2C%22platform%22%3A%22desktop%22%7D%2C%22module%22%3A%22signupPage%22%7D%2C%7B%22time%22%3A1535338773.039%2C%22name%22%3A%22instagram_web_login%22%2C%22extra%22%3A%7B%22app_id%22%3A%221217981644879628%22%2C%22event_name%22%3A%22fb_status_received%22%2C%22fbconnect_status%22%3A%22logged_out%22%2C%22referrer%22%3A%22%22%2C%22referrer_domain%22%3A%22%22%2C%22url%22%3A%22%2F%22%2C%22original_referrer%22%3A%22%22%2C%22original_referrer_domain%22%3A%22%22%2C%22login_identifier_type%22%3Anull%2C%22platform%22%3A%22desktop%22%2C%22path%22%3A%22%2F%22%7D%7D%5D%2C%22log_type%22%3A%22client_event%22%2C%22seq%22%3A1%2C%22session_id%22%3A%2216579527466-a28f43%22%2C%22device_id%22%3A%22W4NpEgALAAHiT9WTf2qi-wGlxMtN%22%7D";
	  text=text.replaceAll("1535338773.037", clock).replaceAll("1535338773.038", clock)
			  .replaceAll("1535338773.039", clock).replaceAll("16579527466", "1657952"+randompassw(4));
	  int i=cookiecreate.indexOf("mid=");
	  String deviceid=cookiecreate.substring(i,cookiecreate.indexOf(";",i)).replaceAll("mid=", "");
	  text=text.replaceAll("W4NpEgALAAHiT9WTf2qi-wGlxMtN", deviceid);
	  
	  postreq("graph.instagram.com/logging_client_events",text);
	 
	  
	  
		
	  
  }
  public void selen() 
	{
	  WebDriver driver=null;
	  
	  System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
	      driver = new FirefoxDriver();
		  driver.navigate().to("http://www.instagram.com");
		
	      
		  driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
		  Iterator<Cookie> i=driver.manage().getCookies().iterator();
		  String z="";
		  while (i.hasNext())
		  {
			  String g=i.next().toString();
			  System.out.println(g.substring(0, g.indexOf(";")));
			  z=z+g.substring(0, g.indexOf(";"));
		  }
		 // System.out.println(driver.manage().getCookies().toString());
	    
	   
	    driver.quit();
	  
	     
	}

}

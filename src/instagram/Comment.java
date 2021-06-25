package instagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Comment {
	public CookieManager ckman = new CookieManager();  
	public static String token ="";
	public static String myip="";
	public static String lastip="";
	public static int limit=5;
	public static int counter=1;	
	public static ArrayList users; 
	public static ArrayList <String>logusers=new ArrayList<String>();
	public static int speed=8;
	public static String firstcookie="";
	public static String user4cookies="";
	public static String pass4cookies="";

	public static Sql2 sq;
	public  String cookiecreate="";
	public  String res="";
	public  String ajax="";
	
	public  WebDriver driver =null;
	//-----------pic detail------------
	public  String picurl="instagram.com/p/BjDwMHmnd9j/?taken-by=roeysdomiii/";
	public static  String picid="1784481816399175523";
	//----------program----------
	public static String programsplit="5";
	public static String programnum="1";
	public static String startlogs="0";
	public static String endlogs="0";
	public static String startusers="0";
	public static String endusers="0";
	
	
	public  static void main(String[] args) throws SQLException, IOException, InterruptedException {
		System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
		Comment co=new Comment("worldusers1");
		 if(myip.equals("no")) {return;}
		 
		 //////------------------------------
		 co.setupstartpoint();
		 int i=0;
		 int s=Integer.valueOf(startusers);
		 String det=logusers.get(i);
		 String []split=det.split(",");
		 co.Login(split[0], split[1]);
		 while(i<logusers.size())
		 {
			  det=logusers.get(i);
			 split=det.split(",");
			
			 
		   if(okbyip()) 
		   {
			   //co.Login(split[0], split[1]);
			 //  co.insert2usedlogs(split[0]);
			   i++;
			   
			   //co.insert2usedlogs(split[0]);
			   //co.startcomment(s);
			   //s=s+40;
			   
			   co.Login(split[0], split[1]);
			   
			   
		   }
		 }
		
		
	}
	
	
	
	

	
	public Comment(String table) throws SQLException, IOException, InterruptedException
	
	{
		
		 myip=theipnow();
		 lastip=theipnow();
		 System.out.println("YOUR IP: "+myip);
		setprop();
		CookieHandler.setDefault(ckman);
	
		sq=new Sql2("worldusers1");
		
		
	}
/////-------------actions--------
	public void Login(String user,String pass) throws IOException, InterruptedException, SQLException
	
	{
		user4cookies=user;
		ckman = new CookieManager();
		
		CookieHandler.setDefault(ckman);
		
		
		String web="instagram.com";
		getreq(web);
		randomtime();
		//selenlogin();
		web="instagram.com/accounts/login/ajax";
		postreq(web,"username="+user+"&password="+pass+"&queryParams=%7B%7D");
		randomtime();
		 web="instagram.com";
		getreq(web);
		randomtime();
		int i=res.indexOf("rollout_hash");
		  
		   ajax=res.substring(res.indexOf("rollout_hash\":\"",i), res.indexOf(",",i+12));
		   ajax=ajax.replaceAll("rollout_hash\":\"", "").replaceAll("\"", "");
		   System.out.println(ajax);
		followrandom();
		
	
	}
    public void comment(String id,String text) throws IOException
    {
    	String z="instagram.com/web/comments/"+id+"/add";
    	postcomment(z, "comment_text="+text);
    }
    public void startcomment(int i ) throws IOException, SQLException, InterruptedException
    {  
    	
    	Sql2 bla=new Sql2("worldusers1");
    	int z=Integer.valueOf(endusers);
    	selencomment();
		while(okbyip()&i<z)
		{
			///---create list of 10 users from the i rows
			
			bla.tenlist(i);
			//------------------
			int b=Commands.users.size();
			
			//------comment
			String nomi="";
			if(b!=0)
			{
				
			nomi= "@"+
			Commands.users.get(0)+" @"+
			Commands.users.get(1)+" @"+
			Commands.users.get(2)+" @"+
			Commands.users.get(3)+" @"+
			Commands.users.get(4)+" @"+
			Commands.users.get(5)+" @"+
			Commands.users.get(6)+" @"+
			Commands.users.get(7)+" @"+
			Commands.users.get(8)+" @"+
			Commands.users.get(9);
			
				
			}
			//-----comment
			  if(!nomi.equals(""))
			  { 
				  i=i+10;
				
				  counter++;
				 selencomment();
				comment(picid, nomi);
				
				
                 updatelastpic(i,picid);
               
			  }
			Commands.users=new ArrayList<>();
			randomtime();
			
		}
		driver.quit();
	    
    }
  
///------------------UPDATE AND CHECK SQL----------------------
    public static void updatelastpic(int i,String idpic)
    {     if(!programnum.equals("1")) {return;}
    	String sql="update roey.statuspic set value1='2'  where key1='lastpic'";
    	sql=sql.replaceAll("2", String.valueOf(i)).replaceAll("lastpic", idpic);
    	try {
			sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("cant update lastpic");
			e.printStackTrace();
		}
    	
    }
    public static void updatelastcookie(String cok)
    {
    	String sql="update roey.logs set cookies='2'  where username='lastpic'";
    	sql=sql.replaceAll("2", cok).replaceAll("lastpic", user4cookies);
    	try {
			sq.stmt.executeUpdate(sql);
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
			sq.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("picalready exsist");
			
		}
    	////-----------check the latest result--------
    	String sqlox="SELECT value1 FROM roey.statuspic where key1=\"thei\"";
 	   sqlox=sqlox.replaceAll("thei", idpic);
 	   ResultSet rs=null;
		try {
			rs = sq.stmt.executeQuery(sqlox);
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
    public   void getreq(String enterurl) throws IOException 
	  {
    	 HttpsURLConnection connection=null;
    	 try {
		    String https_url ="https://www."+enterurl+"/";
		    
		    URL url = new URL(https_url);
		    
		   connection = (HttpsURLConnection)url.openConnection();
		    
		    headers(connection, https_url,"GET");
		  
		    connection.getContent();
		
		    //-----cookies
		    cookiecreate=cookies();
		    
		    
		    //-----getresponse------
		    
         res= respondmaker(connection);
    	 }
    	catch (Exception e) 
    	 {
    		res= respondmaker(connection);
    		if(res.contains("challenge"))
    		{
    			deleteuserfromlogs();
    		}
		}
    	
		   
         
		   
		    //------------------
		 }
	public   void postreq(String enterurl,String body) throws IOException
	  {
		 HttpsURLConnection connection=null;
		  try {
		   String https_url ="https://www."+enterurl+"/";
		   if(enterurl.contains("graph"))
		   {
			   https_url ="https://"+enterurl;
			   
		   }
	        URL url = new URL(https_url);
		    
		     connection = (HttpsURLConnection)url.openConnection();
		  //-----headers------
          headers(connection,https_url,"POST");
		  //-----headers------
          if(!enterurl.contains("graph")) {
		    connection.addRequestProperty("X-CSRFToken",token);
          }
          //-----body------
		    body(connection, body);
          //-----body--------
		    
		    connection.getContent();
		    
		    
		    //-----cookies   
		    cookies();
		   //-----cookies
		    res=respondmaker(connection);
		  } 
		    catch (Exception e) {
	    		res= respondmaker(connection);
	    		if(res.contains("challenge"))
	    		{
	    			deleteuserfromlogs();
	    		}
			}

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
			counter=limit;
		}
	    

	   
  }
 
    
///////------------new version------------
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
		    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.0 Mobile/15E148 Safari/604.1");	
		    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
		    connection.addRequestProperty("Referer", https_url);	
		    connection.addRequestProperty("Accept-Encoding", "");	
		    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");
		    if(!firstcookie.equals(""))
		    {   
		    	String c=new String(firstcookie);
		       connection.addRequestProperty("Cookie",c);
		       firstcookie="";
		       
		    }
		    if(!ajax.equals(""))
		    {
		    connection.addRequestProperty("X-Instagram-AJAX",ajax);
		    }
		    
		    connection.addRequestProperty("Connection","keep-alive");
		    connection.setConnectTimeout(1);

  }
  public String cookies()
  {
      CookieStore ckStore = ckman.getCookieStore();

	   
	   
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
	    updatelastcookie(cr);
	    return cr;
	    
	    
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
 
  public static void randomtime() throws InterruptedException
  {
 	 Random rn = new Random();
		    int time =rn.nextInt(Commands.speed - 2 + 1) + 2;
		    TimeUnit.SECONDS.sleep(time);
 }
  ////------------ip------------
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
///////------------setprop------------
  public static void setprop()
  {
  	System.setProperty("http.proxyHost", "127.0.0.1");
      System.setProperty("https.proxyHost", "127.0.0.1");
      System.setProperty("http.proxyPort", "8888");
      System.setProperty("https.proxyPort", "8888");
      System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\roeysdomi\\Desktop\\tempsave\\FiddlerKeystore");
      System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
  }
  public void clientlog()
  {
	  try {
	  getreq(picurl);
	  TimeUnit.SECONDS.sleep(2);
	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	   String []text=text4clientlog();
	  postreq("graph.instagram.com/logging_client_events",text[0]);
	  TimeUnit.SECONDS.sleep(2);
	  ///---------------------------------------------------
	 
	  
	  postreq("graph.instagram.com/logging_client_events",text[1]);
	  TimeUnit.SECONDS.sleep(1);
	  postreq("instagram.com/ajax/bz",text[2]);
	  TimeUnit.SECONDS.sleep(1);
	  
	  
	  
	  
	  }catch (Exception e) {
		// TODO: handle exception
	}
	 
	  
	  
		
	  
  }
  public  String randompassw(int len)
  {
	  String AB = "0123456789";
		 SecureRandom rnd = new SecureRandom();

		
		   StringBuilder sb = new StringBuilder( 9 );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
  }
  public void picurl(String url) throws IOException
  {
	  getreq(url);
	  picurl=url;
	  int i=res.indexOf("instagram://media?id=");
	  int i2=res.indexOf("\" />",i);
	  picid=res.substring(i,i2);
	  String toreplace="instagram://media?id=";
	  picid=picid.replace(toreplace, "");
	  System.out.println(picid);
  }
  public String [] text4clientlog() throws InterruptedException
  {
	  String [] text=new String[3];
	  String basic="access_token=1217981644879628|65a937f07619e8d4dce239c462a447ce&message=";
	  String first="{\"app_id\":\"1217981644879628\",\"app_ver\":\"1.0.0\",\"data\":[{\"time\":123456789.457,\"name\":\"ig_web_image_loading\",\"extra\":{\"isGridView\":false,\"mediaId\":\"thepicid\",\"loadTime\":149,\"percentRendered\":100,\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"theurlpic\",\"original_referrer_domain\":\"www.instagram.com\"},\"module\":\"postPage\"},{\"time\":123456789.458,\"name\":\"instagram_web_time_spent_navigation\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"event\":\"unload\",\"client_time\":123456789,\"time_spent_id\":\"4mybil\",\"extra_data\":{},\"source_endpoint\":\"postPage\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"theurlpic\",\"original_referrer_domain\":\"www.instagram.com\"}},{\"time\":123456789.459,\"name\":\"instagram_web_time_spent_bit_array\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"tos_id\":\"4mybil\",\"start_time\":123456789,\"tos_array\":[1,0],\"tos_len\":2,\"tos_seq\":5,\"tos_cum\":13,\"log_time\":123456789,\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"theurlpic\",\"original_referrer_domain\":\"www.instagram.com\"}}],\"log_type\":\"client_event\",\"seq\":3,\"session_id\":\"thesession\",\"device_id\":\"thedeviceid\"}";
	  
	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	  String clock=String.valueOf(timestamp.getTime());
	  String session="165"+randompassw(3)+"b5c"+randompassw(2)+"-bdf"+randompassw(3);
	  int i=cookiecreate.indexOf("mid=");
	  String deviceid=cookiecreate.substring(i,cookiecreate.indexOf(";",i)).replaceAll("mid=", "");
	  int i2=cookiecreate.indexOf("ds_user_id=");
		 
	  String userid=cookiecreate.substring(i2,cookiecreate.indexOf(";",i2)).replaceAll("ds_user_id=", "");
	  
	 
	  first=first.replaceAll("thepicid", picid).replaceAll("theurlpic", picurl).replaceAll("123456789", clock).
			  replaceAll("thedeviceid", deviceid).replaceAll("thesession", session).replaceAll("111111", userid);
	  
	   TimeUnit.SECONDS.sleep(2);
	
	  String clock2=String.valueOf(timestamp.getTime());
	  String second="{\"app_id\":\"1217981644879628\",\"app_ver\":\"1.0.0\",\"data\":[{\"time\":123456789.142,\"name\":\"instagram_web_time_spent_navigation\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"event\":\"load\",\"client_time\":123456789142,\"time_spent_id\":\"2j6nsd\",\"extra_data\":{},\"dest_endpoint\":\"postPage\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"}},{\"time\":123456789.143,\"name\":\"instagram_web_media_impressions\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\",\"media_id\":\"thepicid\",\"media_type\":\"photo\",\"owner_id\":\"1697477947\",\"surface\":\"permalink\"},\"obj_type\":\"url\",\"obj_id\":\"/p/BjDwMHmnd9j/\"},{\"time\":123456789.15,\"name\":\"instagram_web_interaction_perf_events\",\"extra\":{\"eventType\":\"asyncSwitch\",\"orig\":\"\",\"origId\":\"\",\"dest\":\"postPage\",\"destId\":\"/p/BjDwMHmnd9j/\",\"timeTaken\":91,\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"}},{\"time\":123456789.155,\"name\":\"instagram_web_client_events\",\"extra\":{\"event_type\":\"page_view\",\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"page_id\":\"postPage_1784481816399175523\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"},\"module\":\"postPage\",\"obj_type\":\"url\",\"obj_id\":\"/p/BjDwMHmnd9j/\"},{\"time\":123456789.156,\"name\":\"instagram_web_resource_transfer_size_events\",\"extra\":{\"resource_type\":\"script\",\"resources_count\":7,\"transfer_size\":0,\"full_page_load\":true,\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"},\"module\":\"postPage\"},{\"time\":123456789.24,\"name\":\"instagram_web_time_spent_bit_array\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"tos_id\":\"2j6nsd\",\"start_time\":123456789,\"tos_array\":[3,0],\"tos_len\":2,\"tos_seq\":0,\"tos_cum\":2,\"log_time\":123456789240,\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"url\":\"/p/BjDwMHmnd9j/\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"}},{\"time\":123456789.243,\"name\":\"instagram_web_client_perf_events\",\"extra\":{\"ig_userid\":111111,\"pk\":111111,\"qe\":{\"fd_gr\":\"control\"},\"app_id\":\"1217981644879628\",\"redirects\":0,\"dns\":0,\"connect\":194,\"request\":1031,\"response\":21,\"network\":1279,\"domInteractive\":171,\"domContentLoaded\":171,\"domComplete\":487,\"loadEvent\":502,\"displayDone\":1702,\"timeToInteractive\":1702,\"firstPaint\":1721,\"firstContentfulPaint\":1721,\"reactReady\":308,\"reactRender\":109,\"referrer\":\"https://www.instagram.com/accounts/login/\",\"referrer_domain\":\"www.instagram.com\",\"original_referrer\":\"thepicurl\",\"original_referrer_domain\":\"www.instagram.com\"},\"module\":\"PostPage\",\"obj_type\":\"url\",\"obj_id\":\"/p/BjDwMHmnd9j/\"}],\"log_type\":\"client_event\",\"seq\":4,\"session_id\":\"thesession\",\"device_id\":\"thedeviceid\"}";
	  second=second.replaceAll("thepicid", picid).replaceAll("theurlpic", picurl).replaceAll("123456789", clock2).
			  replaceAll("thedeviceid", deviceid).replaceAll("thesession", session).replaceAll("111111", userid);
	  
	  
	  text[0]=basic+first;
	  text[1]=basic+second;
	  ////-------------------------------------------------
	  String clock3=String.valueOf(timestamp.getTime());
	  String thrid="[{\"page_id\":\"2j6nsd\",\"posts\":[[\"qe:expose\",{\"qe\":\"sl\",\"mid\":\"thedeviceid\"},123456789,0],[\"qe:expose\",{\"qe\":\"fd_gr\",\"mid\":\"thedeviceid\"},123456789,0]],\"trigger\":\"qe:expose\",\"send_method\":\"ajax\"}]";
	  thrid=thrid.replaceAll("thepicid", picid).replaceAll("theurlpic", picurl).replaceAll("123456789", clock3).
			  replaceAll("thedeviceid", deviceid).replaceAll("thesession", session).replaceAll("111111", userid);
	  text[2]=thrid;
	  return text;
	  
  }
  public void selencomment() 
	{
		
		  driver.get("http://www.instagram.com/p/BjDwMHmnd9j/?taken-by=roeysdomiii");
		
	   
		  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    CookieStore ckStore = ckman.getCookieStore();

		   
		   
	    List<HttpCookie> cks = ckStore.getCookies();
	    String cr="";
		driver.manage().deleteAllCookies();
	      //System.out.println(cks.toString());
	    for (HttpCookie ck : cks)
	    {
	    	cr=ck.toString();
	    	String []split=cr.split("=");
	    	driver.manage().addCookie( new Cookie(split[0], split[1]));	    	
	    	
	    }
	    driver.navigate().to("http://www.instagram.com/p/BjDwMHmnd9j/?taken-by=roeysdomiii");
	
	     
	}
  public void selenlogin() 
	{
		 System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
		  
		  driver.get("http://www.instagram.com");
		
	   
		  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    CookieStore ckStore = ckman.getCookieStore();

		   
		   
	    List<HttpCookie> cks = ckStore.getCookies();
	    String cr="";
		driver.manage().deleteAllCookies();
	      //System.out.println(cks.toString());
	    for (HttpCookie ck : cks)
	    {
	    	cr=ck.toString();
	    	String []split=cr.split("=");
	    	driver.manage().addCookie( new Cookie(split[0], split[1]));	    	
	    	
	    }
	    driver.navigate().to("http://www.instagram.com/accounts/login/ajax");
	
	    
	}

//----------DB FUNC------------
  public void deleteuserfromlogs()
  {
	  String sqlx="delete FROM roey.logs where username=\"bla\";";
	  sqlx=sqlx.replaceAll("bla", user4cookies);
	  try {
		sq.stmt.executeUpdate(sqlx);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    System.out.println("didnt delete "+user4cookies+" from DB");
	}
  }
  public void insert2usedlogs(String name)
  {
	  String sqlx="insert into roey.usedlogs values (now(),\"bkt\",\"1\",\"1\");";
	  sqlx=sqlx.replaceAll("bkt", name);
	  try {
		sq.stmt.executeUpdate(sqlx);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    System.out.println("didnt entered to usedlogs "+user4cookies);
	}
	  
  }
  public void setupstartpoint() throws SQLException, NumberFormatException, IOException, InterruptedException
  {
	  int programsplit1=Integer.valueOf(programsplit);
	  int programnum1=Integer.valueOf(programnum);
	  //-------------------update logs-------------
	 
	 int lognum=Sql2.logtablesize();
	
	 int jump=lognum/programsplit1;
	 int startlog=0;
	 for(int i=0;i<programnum1-1;i++)
	 {
		 startlog=startlog+jump;
	 }
	 System.out.println("=======================");
	 System.out.println("Startlog: "+startlog);
	 System.out.println("endlog: "+(startlog+jump));
	 System.out.println("=======================");
	 startlogs=String.valueOf(startlog);
	 endlogs=String.valueOf(startlog+jump);
	 Sql2.createloglist(startlogs, String.valueOf(jump));
	 //////////---------updated worldusers1-----------
	 
	 int usersnum=Sql2.gettablesize("worldusers1");
	 int jump2=usersnum/programsplit1;
	 int startusers1=0;
	 for(int i=0;i<programnum1-1;i++)
	 {
		 startusers1=startusers1+jump2;
	 }
	 int extrajump=Integer.valueOf(checklastpic(picid));
	 startusers=String.valueOf(startusers1+extrajump);
	 endusers=String.valueOf(startusers1+jump2);
	 System.out.println("=======================");
	 System.out.println("Startusers: "+startusers);
	 System.out.println("endusers: "+endusers);
	 System.out.println("=======================");
	 

	 
  }
  
  public  void followrandom() throws SQLException, InterruptedException
  {
	  Random r = new Random();
	  int num = r.nextInt(5) + 1;
	  int countfollow=0;
	  try {
	  String sql="SELECT * FROM roey.usersid ORDER BY RAND() LIMIT "+num;
	  ResultSet rs=sq.stmt.executeQuery(sql);
		
	  if(!rs.next())
	  {
		  System.out.println("null");return;
	  }
	  
	    
		
		rs=sq.stmt.executeQuery(sql);
		while(rs.next())
		{
			//Commands.users.add(rs.getString(1));
			
			postreq("instagram.com/web/friendships/"+rs.getString(1)+"/follow", "");
			System.out.println(countfollow++);
			
			TimeUnit.SECONDS.sleep(2);
			
		}
	  
	  }catch(Exception e){
		  System.out.println("dd");
		  
	  }
	  
  }
}



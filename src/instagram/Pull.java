package instagram;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;

public class Pull {

	public CookieManager ckman = new CookieManager();  
	public static String token ="";
	public static String nextpage ="";
	public static String theid="";
	public static int speed=3;
	public static Sql2 sq;
	
	
	public static void main (String[]args) throws IOException, InterruptedException, SQLException, JSONException
	{
		Pull p=new Pull();
		//p.Login("roeysdomiii", "21232125");
		//p.Pullfrom("6860189");
		String de="\"data\":{\"user\":{\"connected_fbid\":null,\"edge_facebook_friends\":{\"count\":0},\"edge_suggested_users\":{\"page_info\":{\"has_next_page\":true},\"edges\":[{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":248351825},\"followed_by_viewer\":false,\"full_name\":\"Instagram\",\"id\":\"25025320\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/ca89afc73ba2b787ed1621db3f534ea6/5C150A5B/t51.2885-19/s150x150/14719833_310540259320655_1605122788543168512_a.jpg\",\"requested_by_viewer\":false,\"username\":\"instagram\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram Official Account\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":3740408},\"followed_by_viewer\":false,\"full_name\":\"\",\"id\":\"198104257\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://scontent.cdninstagram.com/vp/74d4a001973ffb1c519909dc584b0316/5C328D7A/t51.2885-19/11906329_960233084022564_1448528159_a.jpg\",\"requested_by_viewer\":false,\"username\":\"saintrecords\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":24494431},\"followed_by_viewer\":false,\"full_name\":\"Gal Gadot\",\"id\":\"20788692\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/d65acd87cf2808f32a533df699c955f9/5C29B49F/t51.2885-19/s150x150/17882610_1302382303183296_7289216107322277888_a.jpg\",\"requested_by_viewer\":false,\"username\":\"gal_gadot\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":6110320},\"followed_by_viewer\":false,\"full_name\":\"Kevin Systrom\",\"id\":\"3\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/fdf15cf2964c9cc7d44a3827b144a305/5C1A23F4/t51.2885-19/s150x150/13732144_1764457777134045_549538515_a.jpg\",\"requested_by_viewer\":false,\"username\":\"kevin\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram Co-Founder and CEO\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":12737941},\"followed_by_viewer\":false,\"full_name\":\"Ronaldo\",\"id\":\"5749522\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/88189d29512cb0d5a239745227f5c552/5C1965C8/t51.2885-19/s150x150/34900564_177879269570116_8573757563319877632_n.jpg\",\"requested_by_viewer\":false,\"username\":\"ronaldo\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":12699188},\"followed_by_viewer\":false,\"full_name\":\"Chelsea FC\",\"id\":\"244309106\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/15d494535929983519583609ba6dcc3e/5C19E646/t51.2885-19/s150x150/38760748_2129458940421140_5607070265805635584_n.jpg\",\"requested_by_viewer\":false,\"username\":\"chelseafc\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":29613397},\"followed_by_viewer\":false,\"full_name\":\"CHANEL\",\"id\":\"695995017\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/2867ccb5222358c1cc945f439a6a08df/5C357ACB/t51.2885-19/11850052_1654448704768135_1464111520_a.jpg\",\"requested_by_viewer\":false,\"username\":\"chanelofficial\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":3798216},\"followed_by_viewer\":false,\"full_name\":\"Mike Krieger\",\"id\":\"4\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/81240c82e0aaed5862407ae141269961/5C179775/t51.2885-19/s150x150/13413275_983141598450213_1314236545_a.jpg\",\"requested_by_viewer\":false,\"username\":\"mikeyk\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram Co-Founder and CTO\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":11686190},\"followed_by_viewer\":false,\"full_name\":\"flame\",\"id\":\"18900337\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/c2f674b0d1ab5d7794c6e67abef55f55/5C343425/t51.2885-19/s150x150/11348214_1481558242162220_192850898_a.jpg\",\"requested_by_viewer\":false,\"username\":\"travisscott\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":9088660},\"followed_by_viewer\":false,\"full_name\":\"Nia Ramadhani Bakrie\",\"id\":\"6477149\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/cb4d5ccea36a8e10cd217ea1d4a2bab8/5C358F5F/t51.2885-19/s150x150/30087146_219525031965866_8723585663489802240_n.jpg\",\"requested_by_viewer\":false,\"username\":\"ramadhaniabakrie\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}}]}}},\"status\":\"ok\"}";
		String re="{\"data\":{\"user\":{\"connected_fbid\":null,\"edge_facebook_friends\":{\"count\":0},\"edge_suggested_users\":{\"page_info\":{\"has_next_page\":true},\"edges\":[{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":6562034},\"followed_by_viewer\":false,\"full_name\":\"John Abraham\",\"id\":\"321031375\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/8bea94b0f9e17a4869f3c5484988f94f/5C19C9DE/t51.2885-19/s150x150/13398806_200358167024995_308174783_a.jpg\",\"requested_by_viewer\":false,\"username\":\"thejohnabraham\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":1763742},\"followed_by_viewer\":false,\"full_name\":\"Carly Rae Jepsen\",\"id\":\"19372645\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/6c210159449c4f9277fd7eb3c8889755/5C292117/t51.2885-19/s150x150/40084013_689891504743123_1067856499033767936_n.jpg\",\"requested_by_viewer\":false,\"username\":\"carlyraejepsen\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":1601154},\"followed_by_viewer\":false,\"full_name\":\"\\ub8e8\\ud53c\\ud83d\\ude03 | \\ud574\\uc801\\uc655\\ud83d\\udc36\",\"id\":\"4901628\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/83b666150ed81083694e0a848da85244/5C2D428F/t51.2885-19/s150x150/17494149_1642283062747250_1152716750630944768_a.jpg\",\"requested_by_viewer\":false,\"username\":\"hihyunwoo\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":91153139},\"followed_by_viewer\":false,\"full_name\":\"Barbie\\u00ae\",\"id\":\"451573056\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/aae42527a9598d72b37152686cbd37a7/5C2A17B9/t51.2885-19/s150x150/38428857_2268617196485173_7479580993595113472_n.jpg\",\"requested_by_viewer\":false,\"username\":\"nickiminaj\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":29275341},\"followed_by_viewer\":false,\"full_name\":\"Lady Gaga\",\"id\":\"184692323\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/64f0ebd9a3e0cd7ae9985fcaaad5b594/5C336097/t51.2885-19/s150x150/37745691_2168870833355751_8532665270441869312_n.jpg\",\"requested_by_viewer\":false,\"username\":\"ladygaga\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":8117821},\"followed_by_viewer\":false,\"full_name\":\"Yami Gautam\",\"id\":\"1450251497\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/c8bf5549c345f328062de5f041253c8f/5C2B8AE0/t51.2885-19/s150x150/31669430_1522885524489582_1905449849889751040_n.jpg\",\"requested_by_viewer\":false,\"username\":\"yamigautam\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":551246},\"followed_by_viewer\":false,\"full_name\":\"J\\u00dcLI MERY\",\"id\":\"310167209\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/9230313660f7a9f6333911e2ff5ad775/5C246DD3/t51.2885-19/s150x150/23421352_132644297501698_4074278003587153920_n.jpg\",\"requested_by_viewer\":false,\"username\":\"juelimery\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":12336307},\"followed_by_viewer\":false,\"full_name\":\"Ruben Onsu\",\"id\":\"26444210\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/9dd38aee8f9d61dfbab02db91cba5217/5C3B4DD7/t51.2885-19/s150x150/22157392_1919115118351975_7682086644312178688_n.jpg\",\"requested_by_viewer\":false,\"username\":\"ruben_onsu\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":7169027},\"followed_by_viewer\":false,\"full_name\":\"Tyler Blackburn\",\"id\":\"5759844\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/6452e80227d170bb76cece0874252954/5C2E70F7/t51.2885-19/s150x150/26184140_143480003006357_934805979936063488_n.jpg\",\"requested_by_viewer\":false,\"username\":\"tylerjblackburn\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}},{\"node\":{\"user\":{\"edge_followed_by\":{\"count\":12548091},\"followed_by_viewer\":false,\"full_name\":\"Future Hendrix\",\"id\":\"20111183\",\"is_private\":false,\"is_verified\":true,\"is_viewer\":false,\"profile_pic_url\":\"https://instagram.fhfa1-1.fna.fbcdn.net/vp/2e801ff4e81e240e1ca5fb5d25c7bf35/5C36C421/t51.2885-19/s150x150/35156071_224136308401611_6876408052971470848_n.jpg\",\"requested_by_viewer\":false,\"username\":\"future\",\"edge_owner_to_timeline_media\":{\"edges\":[]}},\"description\":\"Instagram recommended\"}}]}}},\"status\":\"ok\"}";
	   idfinder(de);
	
	}
	
	
   public Pull() throws SQLException
	{
		setprop();
		CookieHandler.setDefault(ckman);
		sq=new Sql2("worldusers1");
	}
///---------------actions---------
   public void Login(String user,String pass) throws IOException, InterruptedException
	{
		String web="instagram.com";
		getreq(web);
		web="instagram.com/accounts/login/ajax";
		postreq(web,"username="+user+"&password="+pass+"&queryParams=%7B%7D");
		 web="instagram.com";
		getreq(web);
		
		
	}
   public void pullids_followers() throws IOException, InterruptedException, SQLException
   {
     try {
   	 
   	    String https_url="";
   	    
   	    String  hash="7dd9a7e2160524fd85f50317462cff9f";
   	    if(!nextpage.equals(""))
   	    {
   	        https_url="https://www.instagram.com/graphql/query/?query_hash=mako&variables={\"id\":\"theid\",\"include_reel\":false,\"first\":12,\"after\":\"nextp\"}" ;
			    https_url=https_url.replaceAll("theid", theid);
			    https_url=https_url.replaceAll("mako", hash);
			    https_url=https_url.replaceAll("nextp", nextpage);
   	    }
   	    else
   	    {
   	    	https_url="https://www.instagram.com/graphql/query/?query_hash=mako&variables={\"id\":\"theid\",\"include_reel\":false,\"first\":1000}" ;
   	    	https_url=https_url.replaceAll("theid", theid);
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
             //idfinder(result);
           
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
   public void Pullfrom(String id ) throws SQLException, IOException, InterruptedException, JSONException
   
   {
	   theid=id;
	   String sqlox="SELECT token FROM roey.follow where idfollow=\"thei\"";
	   sqlox=sqlox.replaceAll("thei", theid);
	   ResultSet rs=sq.stmt.executeQuery(sqlox); 
		
		 
		if(rs.next())
		{
			if(!rs.getString(1).equals("1"))
			{
				nextpage=rs.getString(1);
				while(true)
				{
					
						pullids_followers();
						
					
				}
			}
		}
	  
	   
	   else
	   {
		   String sqlox2="insert into roey.follow values (\"idf\",\"tokenf\")";
		   sqlox2=sqlox2.replaceAll("idf", id);
		   sqlox2=sqlox2.replaceAll("tokenf", "1");
		   sq.stmt.executeUpdate(sqlox2);
		   while(true)
			{
			   
				   pullids_followers();
				    
				
			}
		

	   }
	   
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
			    	 sqlex=sqlex.replaceAll("theid", theid);
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
   public static void idfinder(String result) throws SQLException
   {
   	   if(!result.equals(""))
		   {     
			     if(result.contains("has_next_page\":false")) {System.out.println();}
			     if(result.contains("true,\"end_cursor"))
			    	 
		        {
			    	 nextpage=result.substring(result.indexOf("end_cursor"), result.indexOf("\"}"));
			    	 nextpage=nextpage.replaceAll("end_cursor\":\"", "");
			    	 String sqlex="UPDATE roey.follow SET token = \"ltok\" WHERE (`idfollow` = \"theid\") ;";
			    	 sqlex=sqlex.replaceAll("theid", theid);
			    	 sqlex=sqlex.replaceAll("ltok", nextpage);

			    	 sq.stmt.executeUpdate(sqlex);
			    	 System.out.println("this is next page="+nextpage);
			    }
			     
			      int i= result.indexOf("\"id\":\"");
			      while(result.indexOf("\"id\":\"",i)>0&i<result.length()&result.indexOf(",",i)>0)
			        {
			      	   int remember=result.indexOf("\"id\":\"",i);
			      	   int other =result.indexOf(",",i);
			      	  
			      	   String clear=result.substring(result.indexOf("\"id\":\"",i),result.indexOf(",",i));
			      	   clear=clear.replace("\"id\":\"", "");
			      	   clear=clear.replace("\"", "");
			      	   clear=clear.replace("}", "");
			      	   System.out.println(clear);
			      	   ///---insert to db---
			      	   sq.insertid(clear); 
			      	   ///---insert to db---
			      	  i=result.indexOf("\"id\":\"",i+1);
			      	  int other2=result.indexOf(",",i);
			      	  int last=result.lastIndexOf("\"id\":\"");
			      	  if(i==last) {break;}
			      	  if(!(other<other2)) {break;}
			      	  if(i<remember) {break;}
			      	

			      	  
			        }
			      Sql2.Status();
		    
		   }
   }
   
   
   
   ///////////------------basics------------
   
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
			    connection.setConnectTimeout(1);
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
    public void body(HttpsURLConnection connection,String body) throws IOException
    {
    	connection.setDoOutput(true);
	    String str =  body;
	    byte[] outputInBytes = str.getBytes("UTF-8");
	    OutputStream os = connection.getOutputStream();
	    os.write(outputInBytes);    
	    os.close();

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
 
    public static void randomtime() throws InterruptedException
    {
    	 Random rn = new Random();
		    int time =rn.nextInt(speed - 2 + 1) + 2;
		    TimeUnit.SECONDS.sleep(time);
    }
 //------------------prop----------------
    public static void setprop()
    {
    	System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\roeysdomi\\Desktop\\tempsave\\FiddlerKeystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "12345678");
    }

	
}

package http;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
public class Httpreq {
	
	public  static String cook="";
	
	
	 public static void main(String args[]) throws Exception {
		
		 

	  }
	public Httpreq()
	{}
 
  public   void getreq(String enterurl) throws IOException
  {
	    String https_url ="https://www."+enterurl+"/";
	    CookieManager ckman = new CookieManager();
	    CookieHandler.setDefault(ckman);
	    URL url = new URL(https_url);
	    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
	    connection.setRequestMethod("GET");
	    connection.addRequestProperty(" origin", https_url);	
	    connection.addRequestProperty("Upgrade-Insecure-Requests", "1");	
	    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");	
	    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
	    connection.addRequestProperty("Referer", https_url);	
	    connection.addRequestProperty("Accept-Encoding", "");	
	    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");
	    connection.addRequestProperty("Cookie",cook);	
	    
	    connection.getContent();
	    //-----cookies
	    CookieStore ckStore = ckman.getCookieStore();
	    List<URI> uriList=ckStore.getURIs();
	    
	    List<HttpCookie> cks = ckStore.getCookies();
	    String c="";
	    for (HttpCookie ck : cks) {
	    	c=c+";"+ck.toString();      
	    }
	    cook="";
	    cook=c;
	 }
  public   void postreq(String enterurl,String body) throws IOException
  {
	   String https_url ="https://www."+enterurl+"/";
	    CookieManager ckman = new CookieManager();
	    CookieHandler.setDefault(ckman);
        URL url = new URL(https_url);
	    
	    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.addRequestProperty(" origin", https_url);	
	    connection.addRequestProperty("Upgrade-Insecure-Requests", "1");	
	    connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");	
	    connection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");	
	    connection.addRequestProperty("Referer", https_url);	
	    connection.addRequestProperty("Accept-Encoding", "");	
	    connection.addRequestProperty("Accept-Language", "q=0.9,en-US;q=0.8,en;q=0.7");
	    connection.addRequestProperty("Cookie",cook);
	    connection.setDoOutput(true);
	    String str =  body;
	    byte[] outputInBytes = str.getBytes("UTF-8");
	    OutputStream os = connection.getOutputStream();
	    os.write(outputInBytes);    
	    os.close();

	    connection.getContent();
	    //-----cookies
	    CookieStore ckStore = ckman.getCookieStore();
	    List<URI> uriList=ckStore.getURIs();
	    
	    List<HttpCookie> cks = ckStore.getCookies();
	    String c="";
	    for (HttpCookie ck : cks) {
	    	c=c+";"+ck.toString();      
	    }
	    cook="";
	    cook=c;

	 }
  }


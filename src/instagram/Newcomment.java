package instagram;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class Newcomment {
	 public static Screen d;
	public static String theurl="BjDwMHmnd9j";
	 public static Sql2 sq;
	 public String firstcookie="";
	 public int loguser=0;
	 public int taguser=0;
	 public int logusersize=0;
	 public int tagusersize=0;
	 public static ArrayList users; 

	
	 public static void main(String[] args) throws FindFailed, InterruptedException, IOException, AWTException, SQLException {
		 System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
		 Pattern f= new Pattern ("C:\\Users\\roeysdomi\\Desktop\\mypro\\comment\\pic1.png");
		 WebDriver driver=null;
		 d=new Screen();
		 
		 Newcreate.disconnect();
		   TimeUnit.SECONDS.sleep(3);
		   Newcreate.connect();
		   TimeUnit.SECONDS.sleep(6);
		   
		   ///----------------
		   Newcomment n=new Newcomment();
		   n.checklastpic(theurl);
		 int i=n.loguser;
		 while(i<n.logusersize) {
				 try {
					 int FOREVER=3600000;
					 i++;
			          n.updatelastpic(theurl,String.valueOf(i),String.valueOf(n.taguser));
					  driver=new FirefoxDriver();
					  driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
					  driver.manage().deleteAllCookies();
			         driver.get("https://www.instagram.com");
			         n.addcookies(String.valueOf(i+1), driver);
			         driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
			         driver.get("https://www.instagram.com");
			         JavascriptExecutor jse = (JavascriptExecutor)driver;
			         jse.executeScript("window.scrollBy(0,259)", "");
			         TimeUnit.SECONDS.sleep(500);
			         String com="";
			          for(int z=0;z<4;z++)
			          {
			        	  com=n.createcomment(i);
			        	  if(!com.equals(""))
			        	  { 
			        		  d.wait(f,FOREVER);
			        		  getloc(f).click();
			        		  d.write(com);
			        		  //TimeUnit.SECONDS.sleep(2);
			        		  d.type(Key.ENTER);
			        	  }
			          }
			          TimeUnit.SECONDS.sleep(3);
			          i++;
			          n.updatelastpic(theurl,String.valueOf(i),String.valueOf(n.taguser));
			          Newcreate.disconnect();
					   TimeUnit.SECONDS.sleep(3);
					   Newcreate.connect();
					   TimeUnit.SECONDS.sleep(6);
					   driver.quit();
			 
				 }
				 catch (Exception e) {
					 i++;
					 Newcreate.disconnect();
					   TimeUnit.SECONDS.sleep(3);
					   Newcreate.connect();
					   TimeUnit.SECONDS.sleep(6);
					   driver.quit();
				}
		 }
	
	
		 }
	public Newcomment() throws SQLException
	{
		sq=new Sql2("worldusers1");
		setprop();
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

    public void checklastpic(String url)
    {
    	String sqlx="insert into roey.statuspic values (\"mako1\",\"mako2\",\"mako3\");";
    	sqlx=sqlx.replaceAll("mako1", url).replaceAll("mako2", "0").replaceAll("mako3", "0");
    	try {
    		sq.stmt.executeUpdate(sqlx);
    	}
    	catch (Exception e) {
		   System.out.println("ealready exsist");
		}
    	String sqlx2="select * from roey.statuspic where picid=\""+url+"\"";
    	ResultSet rs=null;
		try {
			rs = sq.stmt.executeQuery(sqlx2);
			if(rs.next())
			{
				loguser=Integer.valueOf(rs.getString(2));
				taguser=Integer.valueOf(rs.getString(3));

			}
    		
    	}
    	catch (Exception e) {
		   System.out.println("ealready exsist");
		}
		String sqlx3="select count(*) from roey.logs";
    	ResultSet rs2=null;
		try {
			rs2 = sq.stmt.executeQuery(sqlx3);
			if(rs2.next())
			{
				logusersize=Integer.valueOf(rs2.getString(1));
				

			}
    		
    	}
    	catch (Exception e) {
		   System.out.println("ealready exsist");
		}
		String sqlx4="select count(*) from roey.worldusers1";
    	ResultSet rs3=null;
		try {
			rs3 = sq.stmt.executeQuery(sqlx4);
			if(rs3.next())
			{
				tagusersize=Integer.valueOf(rs3.getString(1));
				

			}
    		
    	}
    	catch (Exception e) {
		   System.out.println("ealready exsist");
		}
    	
    }
    public void updatelastpic(String url,String loguser,String taguser)
    {
    	String sqlx2="update roey.statuspic set loguser=\"mako1\",tagusers=\"mako2\" where picid=\"mako3\"";
    	
    	sqlx2=sqlx2.replaceAll("mako1", loguser).replaceAll("mako2", taguser).replaceAll("mako3", url);
    	try {
    		sq.stmt.executeUpdate(sqlx2);
    	}
    	catch (Exception e) {
		   System.out.println("didntupdate");
		}
    }
    public String createcomment(int i) throws SQLException
    {

		sq.tenlist(taguser);
		//------------------
		int b=users.size();
		
		//------comment
		String nomi="";
		if(b!=0)
		{
			
		nomi= "@"+
		users.get(0)+" @"+
		users.get(1)+" @"+
		users.get(2)+" @"+
		users.get(3)+" @"+
		users.get(4)+" @"+
		users.get(5)+" @"+
		users.get(6)+" @"+
		users.get(7)+" @"+
		users.get(8)+" @"+
		users.get(9);
		
			
		}
		//-----comment
		  if(!nomi.equals(""))
		  { 
			  taguser=taguser+10;
			
			
			
			
             updatelastpic(theurl,String.valueOf(i),String.valueOf(taguser));
             users=new ArrayList<>();
           return nomi;
		  }
		  return"";
		
		
    	
    	
    }
    public void addcookies(String loguser,WebDriver driver)
    {
    	String sqlox="SELECT cookies FROM roey.logs limit "+loguser+",1";
  	  
  	   ResultSet rs=null;
 		try {
 			rs = sq.stmt.executeQuery(sqlox);
 		
  		 
  		if(rs.next())
  		{
  			System.out.println(rs.getString(1));
  			if(rs.getString(1).contains("ds_user_id="))
  			{
  				driver.manage().deleteAllCookies();
  			   String[] spl=rs.getString(1).split(";");
  			   
  			   for( int v=0;v<spl.length;v++)
  			   {
  				   String[] spl2=spl[v].split("=");
  				   if(v!=0) {
  				   System.out.println(spl2[0]+"="+spl2[1]);
  				   driver.manage().addCookie(new Cookie(spl2[0], spl2[1]));
  				   }
  			   }
  				
  			}
  		}
  		
          } catch (SQLException e) {
 			
 			e.printStackTrace();
 		} 
    }
    public static Location getloc(Pattern p) throws FindFailed
    {
    	Random r=new Random();
    	int rx=r.nextInt(210)+1;
    	int ry=r.nextInt(5)-5;
    	
    	Region reg=d;
        Match m = reg.find(p);
    	int x=m.getX()+rx;
    	int y=m.getY()+ry;
    	
        Location po=new Location(x,y);
		
    	return po;
    }
}

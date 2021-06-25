package instagram;
import java.awt.AWTException;
import java.io.IOException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import static org.junit.Assert.assertEquals;

public class Newcreate {
    public static Screen d;
	public static void main(String[] args) throws FindFailed, InterruptedException, IOException, AWTException {
		 System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
		 WebDriver driver=null;
		
		 Newcreate.disconnect();
		   TimeUnit.SECONDS.sleep(3);
		   Newcreate.connect();
		   TimeUnit.SECONDS.sleep(6);
		 
		 while(true) {
			 try {
				
				 
				  driver=new FirefoxDriver();
		 driver.get("https://www.instagram.com/accounts/emailsignup/");
	    Pattern f= new Pattern ("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic1.png");
	    Pattern f2=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic2.png");
	    Pattern f3=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic3.png");
	    Pattern f4=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic4.png");
	    Pattern f5=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic5.png");
	    Pattern f6=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic6.png");
	    Pattern f7=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic7.png");
	    Pattern f8=new Pattern("C:\\Users\\roeysdomi\\Desktop\\mypro\\create\\pic8.png");
	     d=new Screen();
	   //TimeUnit.SECONDS.sleep(5);
	   
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
	    String []fo=Create.randomname();
	    Settings se=new Settings();
	  //  se.TypeDelay=0.25;
		
			String pass=Create.randompassw(8);
			Random ra=new Random();
	          // int dre= ra.nextInt(3)+1;
			//   Chck.readfile(String.valueOf(dre));
        int FOREVER=3600000;
        
        
        //-----email-----
       
	   d.wait(f.similar((float) 0.60),FOREVER);
       randomactivity(f);
	   d.type(fo[0]+"@gmail.com");
	   
	   d.wait(f2.similar((float) 0.60),FOREVER);
	   randomactivity(f2);
	  
	   d.type(fo[1]);
	   
	 d.wait(f3.similar((float) 0.60),FOREVER);
	 
	   //-----username-----
	   randomactivity(f3);
	    d.type(fo[0]);
	    
	   d.wait(f4.similar((float) 0.60),FOREVER);
	   randomactivity(f4);
	   //-----pass-----
	   
	   d.type(pass);
	  
	   TimeUnit.SECONDS.sleep(2);
	   d.type(Key.ENTER);
	   //d.wait(f6,FOREVER).click();
	   TimeUnit.SECONDS.sleep(5);
	   //d.wait(f6,2).click();  
	   //TimeUnit.SECONDS.sleep(1);
	   //driver.manage().timeouts().implicitlyWait(1, TimeUnit.MICROSECONDS);
	   driver.get("https://www.instagram.com/accounts/edit/");
	   d.wait(f7,FOREVER);
	   
	   getloclimt(f7,200,-6).click();
	   d.type("a",Key.CTRL);
	   d.type(Key.DELETE);
	   
	   Iterator<Cookie> i=driver.manage().getCookies().iterator();
		  String z="";
		  while (i.hasNext())
		  {
			  String g=i.next().toString();
			  System.out.println(g.substring(0, g.indexOf(";")));
			  z=z+";"+g.substring(0, g.indexOf(";"));
		  }
		  Create co=new Create("bla");
	    co.checklastcookies(fo[0], pass,"0");
	   co.updatelastcookie(z, fo[0]);
	   
	   d.type(fo[0]+"@tempmail.ws");
	   
	   TimeUnit.SECONDS.sleep(3);
	   d.type(Key.ENTER);
	   TimeUnit.SECONDS.sleep(2);
	   d.type(Key.ENTER);
	   
	   Tempmail t=new Tempmail(fo[0]);
	  
	   TimeUnit.SECONDS.sleep(20);
	   Newcreate.disconnect();
	   TimeUnit.SECONDS.sleep(3);
	   Newcreate.connect();
	   TimeUnit.SECONDS.sleep(5);
	   
	 
	   driver.quit();
			 }
			 catch (Exception e) {
				 
				 Newcreate.disconnect();
				   TimeUnit.SECONDS.sleep(3);
				   Newcreate.connect();
				   TimeUnit.SECONDS.sleep(6);
				   driver.quit();
			}
	   
	   
	  
		 }
	}	
	
	public static void connect() throws IOException 
	{
		Process p = Runtime
                .getRuntime()
                .exec(" netsh interface set interface name=\"roey\" admin=enabled");
		//TASKKILL /IM chrome.exe
	    
	}
	public static void disconnect() throws IOException 
	{
		Process p = Runtime
                .getRuntime()
                .exec(" netsh interface set interface name=\"roey\" admin=disabled");
       
	    
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
    public static Location getloclimt(Pattern p,int xt,int yt) throws FindFailed
    {
    	Random r=new Random();
    	int rx=r.nextInt(xt)+1;
    	int ry=r.nextInt(5)-yt;
    	
    	Region reg=d;
        Match m = reg.find(p);
    	int x=m.getX()+rx;
    	int y=m.getY()+ry;
    	
        Location po=new Location(x,y);
		
    	return po;
    }
    public static void randomactivity(Pattern p) throws FindFailed, InterruptedException
    {
    	Random ra=new Random();
    	int i=ra.nextInt(3)+1;
    	for(int z=0;z<i;z++)
    	{
    		getloc(p).click();
    	}
    	
    	TimeUnit.SECONDS.sleep(i++);
    }
    

}
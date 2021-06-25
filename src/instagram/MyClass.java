package instagram;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class MyClass {

	public static void main(String[] args) throws AWTException {
		System.setProperty("webdriver.gecko.driver","D:\\instal\\geckodriver\\geckodriver.exe");
		  WebDriver driver = new FirefoxDriver();
		  
		  driver.get("http://www.instagram.com/p/BjDwMHmnd9j/?taken-by=roeysdomiii");
		
		  
		Dimension n = new Dimension(1,1);  
		 driver.manage().window().setSize(n);
	
	  
	  driver.quit();
		
		
	
	     //driver.quit();
	}	
}
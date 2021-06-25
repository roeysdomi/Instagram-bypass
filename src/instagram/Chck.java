package instagram;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Chck {
	 static ArrayList <Integer> f=new ArrayList<Integer>(); 
	 static ArrayList <Integer> z=new ArrayList<Integer>(); 
	 static Robot r ;
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
	        	
	         //  record("4");
	          // Random ra=new Random();
	         //  int d= ra.nextInt(2)+1;
			 //  readfile(String.valueOf(1));
		   MyClass1.disconnect();
		   TimeUnit.SECONDS.sleep(4);
		   MyClass1.connect();
		   

		 
		   
	}	

public static void record(String name) throws InterruptedException, AWTException, IOException
{
	r = new Robot();
	 
	 TimeUnit.SECONDS.sleep(1);
	 System.out.println("start record");
	 PointerInfo a = MouseInfo.getPointerInfo();
	    Point b = a.getLocation();
	   
	    int x2=0;
	    int y2=0;
	    
		   long start = System.currentTimeMillis();
		   long end = start + 60*40; // 60 seconds * 1000 ms/sec
		   while (System.currentTimeMillis() < end)
		   {
		         a = MouseInfo.getPointerInfo();
			     b = a.getLocation();
			    // System.out.println( b.toString());
			     x2 = (int) b.getX();
			     y2 = (int) b.getY();
			    f.add((x2));
			    z.add(y2);
			    TimeUnit.NANOSECONDS.sleep(100);
		   }
		   System.out.println("done record");
		   //TimeUnit.SECONDS.sleep(5);
		  
		   writefile(f,z,name);
		   
	
}

public static void writefile(ArrayList <Integer> x,ArrayList <Integer> y,String name) throws IOException
{
	 
	FileWriter fw = new
            FileWriter("C:\\Users\\roeysdomi\\Desktop\\mypro\\mouse\\"+name+".txt");
    BufferedWriter WriteFileBuffer = new
            BufferedWriter(fw);

    //Sample 02: Write Some Text to File
    // Using Buffered Writer)
    for( int i=0; i<x.size();i++)
    {
	    WriteFileBuffer.write(x.get(i)+","+y.get(i));
	    WriteFileBuffer.newLine();
	   
    }
    //Sample 03: Close both the Writers
    WriteFileBuffer.close();
	



}

public static void readfile(String name) throws IOException, InterruptedException, AWTException
{
	r = new Robot();
	System.out.println("start read");
	f=new ArrayList<Integer>(); 
	 z=new ArrayList<Integer>(); 
	
	FileReader fr = new
            FileReader("C:\\Users\\roeysdomi\\Desktop\\mypro\\mouse\\"+name+".txt");
    BufferedReader rb = new
            BufferedReader(fr);

    //Sample 05: Read the text Written 
    // using BufferedWriter
    String line;
    while((line=rb.readLine())!=null)
    {
      String [] spl=line.split(",");
      int x=Integer.valueOf(spl[0]);
      int y=Integer.valueOf(spl[1]);
      f.add(x);
      z.add(y);
    }

    //Sample 06: Close the Readers
    rb.close();
    
    int i=0;
 			while (i<f.size())
 				
 			{
 				r.mouseMove(f.get(i), z.get(i));
 				//System.out.println(f.get(i));
 				TimeUnit.NANOSECONDS.sleep(100);
 				
 				i++;
 			}
}
}
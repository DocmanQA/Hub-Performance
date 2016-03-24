package PerformanceTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.FileWriter;
import java.io.IOException;


public class Document_List {
	private WebDriver driver = null;
	private String WB = "f";
	private Long Doc_List_LoadUp_Time;
	private int Numbers = 50;
  @Test
  public void main() {
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  System.out.println(ft.format(date));
	  
	  try {
			FileWriter writer = new FileWriter("c:\\Documents_List_LoadUp_Time" + ft.format(date) + ".csv");
			writer.append("Time,No,Result\n");
			
			for (int x = 1; x < (Numbers + 1); x++){
				System.out.println("Loop" + x);
				if (WB == "f"){
					  driver = new FirefoxDriver();
				  }
				  else if (WB == "c"){
					  System.setProperty("webdriver.chrome.driver", "C:\\Selenium Doc\\chromedriver.exe");
					  driver = new ChromeDriver();
					  driver.manage().window().maximize();
				  } 
				  else if (WB == "i"){
					  System.setProperty("webdriver.ie.driver", "C:\\Selenium Doc\\IEDriverServer.exe");
					  driver = new InternetExplorerDriver();
				  }
				  
				  LoginPage loginPage = new LoginPage(driver);
				  loginPage.login();
				  
				  System.out.println("The login loading time is " + LoginPage.Loadingtime );
				
				  //writer.append("Login Time" + ',' + x + ',' + LoginPage.Loadingtime + '\n');
				  
				  WebElement Doc_List = driver.findElement(By.linkText("Documents List"));
				  Date Click_Doc_List_Time = Calendar.getInstance().getTime();
				  Doc_List.click();
				  
				  driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				  String title = driver.getTitle();
				  Assert.assertTrue(title.contains("EDT Hub - Console [Document List]"));
				  //WebElement EndPointCount = driver.findElement(By.id("ct100_cp1_lblItemCount"));
				  Date Doc_List_load_Time = Calendar.getInstance().getTime();
				  Doc_List_LoadUp_Time = (Doc_List_load_Time.getTime() - Click_Doc_List_Time.getTime());
				  System.out.println("The Documents List load up time is " + Doc_List_LoadUp_Time);
				
				
				writer.append("Doc List Loadup Time" + ',' + x + ',' + Doc_List_LoadUp_Time + '\n');
				
				driver.quit();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  
  }
  @BeforeTest
  public void beforeTest() {
	  
  }

  @AfterTest
  public void afterTest() {
	  
  }

}

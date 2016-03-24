package PerformanceTest;

import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.lift.WebDriverTestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import java.util.Date;
import java.text.*;


public class Performance_Login {
	
	private WebDriver driver = null;
	private String WB = "f";
	private long Loadingtime_total = 0;
	private int Numbers = 50;
  @Test
  public void main() {
	  
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  System.out.println(ft.format(date));
	  
	  try {
			FileWriter writer = new FileWriter("c:\\Login_Time_" + ft.format(date) + ".csv");
			
			writer.append("Time,No,Result\n");
		
		  
		  for (int i=1; i < (Numbers + 1); i++){
			  System.out.println("Loop" + i);
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
			  Loadingtime_total += LoginPage.Loadingtime;
			  
			  writer.append("Login Time" + ',' + i + ',' + LoginPage.Loadingtime + '\n');
			  String title = driver.getTitle();
			  Assert.assertTrue(title.contains("EDT Hub - Console"));
			  driver.quit();
		  try {
			Thread.sleep(1000);
		  } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	  }
	  System.out.println("The average login time is " + (Loadingtime_total / Numbers) );
	  
	  writer.append("Login Time" + ',' + "Average" + ',' + (Loadingtime_total / Numbers) + '\n');
	  
	  writer.flush();
	  writer.close();
	  
	  } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  }
  @BeforeTest
  public void beforeTest() {
	  
  }

  @AfterTest
  public void afterTest() {
	  
  }

}

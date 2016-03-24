package PerformanceTest;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Source_Activity {
	private WebDriver driver = null;
	private String WB = "f";
  @Test
  public void main() {
	  WebElement Source = driver.findElement(By.linkText("Source Activity"));
	  Date Click_Source_Time = Calendar.getInstance().getTime();
	  Source.click();
	  
	  String title = driver.getTitle();
	  Assert.assertTrue(title.contains("EDT Hub - Console [Source Activity]"));
	  //WebElement EndPointCount = driver.findElement(By.id("ct100_cp1_lblItemCount"));
	  Date Source_load_Time = Calendar.getInstance().getTime();
	  
	  System.out.println("The Source Activity page load up time is " + (Source_load_Time.getTime() - Click_Source_Time.getTime()));
	  
  }
  @BeforeTest
  public void beforeTest() {
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
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}

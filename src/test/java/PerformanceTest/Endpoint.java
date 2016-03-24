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

import PerformanceTest.LoginPage;

public class Endpoint {
	private WebDriver driver = null;
	private String WB = "f";
  @Test
  public void main() {
	  WebElement EndPoint = driver.findElement(By.linkText("End Points"));
	  Date Click_EndPoint_Time = Calendar.getInstance().getTime();
	  EndPoint.click();
	  
	  String title = driver.getTitle();
	  Assert.assertTrue(title.contains("EDT Hub - Console [End Point List]"));
	  //WebElement EndPointCount = driver.findElement(By.id("ct100_cp1_lblItemCount"));
	  Date EndPoint_load_Time = Calendar.getInstance().getTime();
	  
	  System.out.println("The End Point page load up time is " + (EndPoint_load_Time.getTime() - Click_EndPoint_Time.getTime()));
	  
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //WebElement EndPoint_Table = driver.findElement(By.id("ctl00_cp1_tbl"));
	  //WebElement EndPoint_View = EndPoint_Table.findElement(By.xpath(".//tr/td/input[@ name='ctl00$cp1$lnkPRID|1' and @ type='image']"));
	  WebElement EndPoint_View = driver.findElement(By.name("ctl00$cp1$lnkPRID|1"));
	  Date Click_EndPoint_View_Time = Calendar.getInstance().getTime();
	  EndPoint_View.click();
	  
	  WebElement EndPoint_title = driver.findElement(By.name("ctl00$cp1$txtPRNAME"));
	  String EndPoint_Name = EndPoint_title.getAttribute("value");
	  System.out.println(EndPoint_Name);
	  Assert.assertTrue(EndPoint_Name.contains("Pioneers Way 2"));
	  
	  Date EndPoint_Title_Time = Calendar.getInstance().getTime();
	  
	  System.out.println("View End Point Detail load up time is " + (EndPoint_Title_Time.getTime() - Click_EndPoint_View_Time.getTime()));
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

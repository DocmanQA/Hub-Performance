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

public class User {
	private WebDriver driver = null;
	private String WB = "f";
  @Test
  public void main() {
	  WebElement User = driver.findElement(By.linkText("Users"));
	  Date Click_User_Time = Calendar.getInstance().getTime();
	  User.click();
	  
	  String title = driver.getTitle();
	  Assert.assertTrue(title.contains("EDT Hub - Console [User List]"));
	  //WebElement EndPointCount = driver.findElement(By.id("ct100_cp1_lblItemCount"));
	  Date User_load_Time = Calendar.getInstance().getTime();
	  
	  System.out.println("The User page load up time is " + (User_load_Time.getTime() - Click_User_Time.getTime()));
	  
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  WebElement User_View = driver.findElement(By.name("ctl00$cp1$lnkEXID|1"));
	  Date Click_User_View_Time = Calendar.getInstance().getTime();
	  User_View.click();
	  
	  WebElement User_ID = driver.findElement(By.name("ctl00$cp1$txtEXUSER"));
	  String User_ID_Name = User_ID.getAttribute("value");
	  System.out.println(User_ID_Name);
	  Assert.assertTrue(User_ID_Name.contains("ADMIN"));
	  
	  Date User_Details_Time = Calendar.getInstance().getTime();
	  
	  System.out.println("View User Detail load up time is " + (User_Details_Time.getTime() - Click_User_View_Time.getTime()));
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

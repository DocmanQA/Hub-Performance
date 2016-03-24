package PerformanceTest;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;
	WebElement loginusername;
	WebElement loginpassword;
	WebElement loginBtn;
	public static long Loadingtime;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		//driver.get("http://hub08to2/EDTConsole/");
		driver.get("http://vm-875-tuned_tz/EDTWeb/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		loginusername = driver.findElement(By.name("txtUser"));
		loginpassword = driver.findElement(By.name("txtPWD"));
		loginBtn = driver.findElement(By.name("cmdLogin"));
	}
	
	public void login(){
		loginusername.sendKeys("Admin");
		
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int month = calendar.get(Calendar.MONTH);
		System.out.println("The day of week is " + day);
		System.out.println("This month is " + month);
		//Check_Activity_Summary.Data_1 = "";
		
		//Find out the override password
		String DayofWeek;
		
		switch (day) {
		case 1:
			DayofWeek = "sun";
			break;
		case 2:
			DayofWeek = "mon";
			break;
		case 3:
			DayofWeek = "tue";
			break;
		case 4:
			DayofWeek = "wed";
			break;
		case 5:
			DayofWeek = "thu";
			break;
		case 6:
			DayofWeek = "fri";
			break;
		case 7:
			DayofWeek = "sat";
			break;
		default: DayofWeek = "";
			break;
		}
		
		System.out.println(DayofWeek);
		
		String DayofMonth;
		switch (month){
		case 2:
			DayofMonth = "sweetlamp";
			break;
		case 3:
			DayofMonth = "stickycake";
			break;
		case 4:
			DayofMonth = "smoothbed";
			break;
		case 5:
			DayofMonth = "bitterpint";
			break;
		case 6:
			DayofMonth = "crispydonkey";
			break;
		default: DayofMonth = "";
		    break;
		}
		
		System.out.println(DayofMonth);

		loginpassword.sendKeys(DayofWeek + DayofMonth);
		
		//Click Login button
		Date click_login_time = Calendar.getInstance().getTime();
		System.out.println(click_login_time);
		loginBtn.click();
		
		//Wait for main page to display
		WebElement labelUser = driver.findElement(By.id("ctl00_lblUser"));
		Date main_page_time = Calendar.getInstance().getTime();
		System.out.println(main_page_time);

		//Calculate the main page loading time
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//long load_main_page_time = (main_page_time.getTime() - click_login_time.getTime());
		Loadingtime = (main_page_time.getTime() - click_login_time.getTime());
		//Check_Activity_Summary.Data_1 += "Login successful. The web page loading time is: " + load_main_page_time + "ms" + '\n';
		
	}

}

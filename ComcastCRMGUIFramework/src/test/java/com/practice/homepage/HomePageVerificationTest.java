package com.practice.homepage;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageVerificationTest {
	
	@Test
	public void homepageTest(Method mth) {
		System.out.println(mth+" is executed.");
		
		String expected = "Home";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/index.php?action=Login&module=Users");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
//		TakeScreenShot ts = (TakeScreenshot) driver;
		TakesScreenshot ts = (TakesScreenshot)driver;
		ts.getScreenshotAs(OutputType.FILE);
		File path = new File("./testdata");
		
		String actual = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		Assert.assertEquals(expected,actual);
		
	}
	
	@Test
	public void verifyhomepageLogo(Method mth) {
		
		System.out.println(mth+" got executed.");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/index.php?action=Login&module=Users");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		
		
		if(driver.findElement(By.xpath("//img[@title = 'vtiger-crm-logo.gif']")).isDisplayed()) {
			System.out.println("Home page logo is verified ===> PASSED.");
		}else {
			System.out.println("Home page logois not verified ===> FAILED.");
		}
		
	}

}

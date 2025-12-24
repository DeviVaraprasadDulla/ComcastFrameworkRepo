package com.practice.dataprovider;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.genericfileutility.ExcelUtility;

public class SearchProductTest {
	
	//creating data provider
	ExcelUtility elib = new ExcelUtility();
	
	
	
	@DataProvider
	public Object getData() throws EncryptedDocumentException, IOException {
		Object data [][] = new Object[1][2];
		int rows = elib.getRowCount("amazon");
		
		for(int i=0;i<rows;i++) {
			data[i][0] = elib.getDataFromExcelFile("amazon", i+1, 0);
			data[i][1] = elib.getDataFromExcelFile("amazon", i+1, 1);
		}
		
		
		return data;
	}
	
	
	@Test(dataProvider = "getData")
	public void searchProduct(String brand,String model) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		driver.navigate().refresh();
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand,Keys.ENTER);
//		driver.findElement(By.xpath("//span[text()='"+model+"']")).click();
		
		String price  = driver.findElement(By.xpath("//span[text()='iPhone 15 (128 GB) - Green']/ancestor::div[contains(@class,'a-section a-spacing-none pu')]/parent::div/descendant::span[@class='a-price']/span[@class ='a-offscreen']")).getText();
		System.out.println("Price"+price);
	}
}

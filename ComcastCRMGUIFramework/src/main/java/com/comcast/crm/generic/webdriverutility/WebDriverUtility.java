package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	
	public void waitForPageToLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement element ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToNewBrowserTab(WebDriver driver,String partialURL) {
		
		Set<String> childWindows = driver.getWindowHandles();
		
		for(String windowID : childWindows) {
			driver.switchTo().window(windowID);
			String actURL = driver.getCurrentUrl();
			if(actURL.contains(partialURL)) {
				break;
			}
		}
		
	}
	public void switchTabOnTitle(WebDriver driver,String partialURL) {
		
		Set<String> childWindows = driver.getWindowHandles();
		
		for(String windowID : childWindows) {
			driver.switchTo().window(windowID);
			String actURL = driver.getTitle();
			if(actURL.contains(partialURL)) {
				break;
			}
		}
		
	}
	
	public void swichToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void swichToFrame(WebDriver driver, String name) {
		driver.switchTo().frame(name);
	}
	public void swichToFrame(WebDriver driver,  WebElement element) {
		driver.switchTo().frame(element);
	}
	
	
	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAleartDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String visibleText) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(visibleText);
		
	}
	public void selectByValue(WebElement element,String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}
	
	public void selectByIndex(WebElement element,int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}
	public void mouseMoveOnElement(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
}

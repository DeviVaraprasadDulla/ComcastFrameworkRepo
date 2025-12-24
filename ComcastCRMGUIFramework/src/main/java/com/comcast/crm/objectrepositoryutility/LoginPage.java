package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * LoginPage
 *
 * @author Devi Vara Prasad
 * This page contains login page details and business logic
 */
public class LoginPage {
	
	public WebDriver driver = null;
	WebDriverUtility wlib = new WebDriverUtility();

	@FindBy(name = "user_name")
	private WebElement userNameField;

	@FindBy(name = "user_password")
	private WebElement PasswordField;

	@FindBy(id = "submitButton")
	private WebElement loginButton;



//	constructor to initialize the WebElement
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);//Static method
	}

	// Getter method for single WebElement
	public WebElement getUserNameField() {
		return userNameField;
	}

	public WebElement getUserPasswordField() {
		return PasswordField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	

	// login action
	
	/**
	 * login action with userName,password and URL parameter.
	 * @param userName
	 * @param password
	 * @param url
	 * 
	 */

	public void loginAction(String userName, String password,String url) {
		
		driver.get(url);
		wlib.waitForPageToLoad(driver);
		userNameField.sendKeys(userName);
		PasswordField.sendKeys(password);
		loginButton.click();

	}
	
	

}

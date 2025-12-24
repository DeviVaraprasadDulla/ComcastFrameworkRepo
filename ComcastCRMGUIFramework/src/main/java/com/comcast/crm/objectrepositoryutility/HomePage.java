package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	
	WebDriver driver ;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Org')]")
	private WebElement organizationLink;

	@FindBy(xpath = "//a[contains(text(),'Conta')]")
	private WebElement contactLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(partialLinkText="Campaign")
	private WebElement campagin;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement userImage;
	
	@FindBy(linkText ="Sign Out")
	private WebElement singoutBtn;
	


	public WebElement getOrganizationLink() {
		return organizationLink;
	}
	
	public WebElement getContactLink() {
		return contactLink;
	}
	public WebElement getLogoutBtn() {
		return userImage;
	}
	
	
	
	public void navigateToCampaignLink() {
		Actions actions = new Actions(driver);
		actions.moveToElement(campagin).click().perform();
	}
	
	
	public void logoutUser() {
		WebDriverUtility wdlib = new WebDriverUtility();
		Actions actions = new Actions(driver);
		wdlib.waitForElementPresent(driver, userImage);
		actions.moveToElement(userImage).perform();
		wdlib.waitForElementPresent(driver,singoutBtn);
		singoutBtn.click();
	}


}

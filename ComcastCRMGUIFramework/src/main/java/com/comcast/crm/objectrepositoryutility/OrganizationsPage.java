package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationsPage {

	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Organization...']")
	WebElement createOrg;
	
	@FindBy(className= "txtBox")
	private WebElement searchField;
	
	@FindBy(id="bas_searchfield")
	private WebElement searcByType;
	
	@FindBy(name ="submit")
	private WebElement searchNowBtn;
	
//	@FindBy(xpath="//a[contains(text(),'tataMotors_577')]//ancestor::tr[@id='row_88938']/td/a[contains(text(),'del')]")
//	private WebElement deleteLink;
	
	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getCreateOrg() {
		return createOrg;
	}
	
	public WebElement getSearcByType() {
		return searcByType;
	}
	
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
//	public WebElement getDeleteLink() {
//		return deleteLink;
//	}
	
	
	public void searchBy(String type) {
		Select select = new Select(searcByType);
		select.selectByVisibleText(type);
	}
	
	

}

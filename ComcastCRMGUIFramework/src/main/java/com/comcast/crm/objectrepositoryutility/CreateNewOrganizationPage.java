package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {

	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameField;

	@FindBy(name = "ship_street")
	private WebElement shippingAddField;

	@FindBy(name = "industry")
	private WebElement industryDropDown;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getOrgNameField() {
		return orgNameField;
	}

	public WebElement getShippingAddField() {
		return shippingAddField;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgName, String address) {
		orgNameField.sendKeys(orgName);
		shippingAddField.sendKeys(address);
		saveBtn.click();
	}

	public void createOrg(String orgName, String address, String industry) {
		orgNameField.sendKeys(orgName);
		shippingAddField.sendKeys(address);
		Select dropDown = new Select(industryDropDown);

		dropDown.selectByVisibleText(industry);
		saveBtn.click();
	}

}

package com.comcat.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcat.crm.baseclass.BaseClass;
/**
 * This class is used to test search contact functionality.
 *
 * @author Devi Vara Prasad
 */
public class CreateOrganizationTest extends BaseClass {

	@Test(groups="smokeTest")
	public void createOrgTest() throws IOException {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel.");
		String orgName = excelUtility.getDataFromExcelFile("org", 1, 2) + javaUtility.getRandomNumber();

		// creating Page Object Model class object

		HomePage homePage = new HomePage(driver);
		OrganizationsPage orgPage = new OrganizationsPage(driver);
		CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
		OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(driver);

//		homePage.clickOnOrgLink();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org page.");
		homePage.getOrganizationLink().click();

		// step :4 Create organization
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create org Page.");
		orgPage.getCreateOrg().click();

//		createNewOrg.createOrg(orgName, " hyderabad ");
		UtilityClassObject.getTest().log(Status.INFO, "Create org.");
		createNewOrg.createOrg(orgName, "hyderabad", "Energy");

// 		verifying whether the organization is created or not

		String savedText = orgInfoPage.getHeaderTextElement().getText();
//
//		if (savedText.contains(orgName)) {
//			System.out.println(orgName + " Organization Created.");
//		} else {
//			System.out.println("Organization not yet Created.");
//		}
//		
//		System.out.println("createOrgTest method executed");
		
		Assert.assertEquals(true, savedText.contains(orgName));
	}
	
	
	@Test(groups="regressionTest")
	public void createOrganizationTestWithIndustry() throws EncryptedDocumentException, IOException {
		// Excel Data
		String orgName = excelUtility.getDataFromExcelFile("org", 4, 2) + javaUtility.getRandomNumber();
		String industries = excelUtility.getDataFromExcelFile("org", 4, 3);
		String type = excelUtility.getDataFromExcelFile("org", 4, 4);
		
			//step :4 Create organization
		driver.findElement(By.partialLinkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
			// Selecting industry from the drop down
		Select industryDropDown = new Select(driver.findElement(By.name("industry")));
		industryDropDown.selectByVisibleText(industries);
		Select typeDD = new Select(driver.findElement(By.name("accounttype")));
		typeDD.selectByVisibleText(type);
		driver.findElement(By.name("ship_street")).sendKeys("KPHB, Hyderabad.");
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

//Verification Step :  verifying whether the organization is created or not

		String headerContent = driver.findElement(By.className("dvHeaderText")).getText();
		if (headerContent.contains(orgName)) {
			System.out.println(orgName + " is Created.");
		} else {
			System.out.println(orgName + " is not yet Created");
		}

		String orgNameTextField = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (orgNameTextField.equals(orgName)) {
			System.out.println(orgName + " is created.");
		} else {
			System.out.println(orgName + " is not Created.");
		}

//Verifying the industry name and type

		String savedIndus = driver.findElement(By.xpath("//span[@id='dtlview_Industry']/font")).getText();

		if (savedIndus.equals(industries)) {
			System.out.println("Created with the industry " + industries);
		} else {
			System.out.println("Industry is not created as expected");
		}

		String savedType = driver.findElement(By.xpath("//span[@id='dtlview_Type']/font")).getText();
		if (savedType.equals(type)) {
			System.out.println("Created with the industry " + industries);
		} else {
			System.out.println("Type is not created as expected");
		
		}
		
		System.out.println("createOrganizationTestWithIndustry executed.");
	}
		

	@Test(groups="regressionTest")
	public void createOrganizationTestWithPhoneNumber() throws EncryptedDocumentException, IOException {

		// Excel Data
		String orgName = excelUtility.getDataFromExcelFile("org", 7, 2) + javaUtility.getRandomNumber();
		String phoneNumber = excelUtility.getDataFromExcelFile("org", 7, 3);
		// step :4 Create organization
		driver.findElement(By.partialLinkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		// Get the phone number field
		driver.findElement(By.name("phone")).sendKeys(phoneNumber);
		driver.findElement(By.name("ship_street")).sendKeys("KPHB, Hyderabad.");
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		// Verification Steps : verifying whether the organization is created or not

		String headerContent = driver.findElement(By.className("dvHeaderText")).getText();
		if (headerContent.contains(orgName)) {
			System.out.println(orgName + " is Created.");
		} else {
			System.out.println(orgName + " is not yet Created");
		}

		String orgNameTextField = driver.findElement(By.id("dtlview_Organization Name")).getText();

		if (orgNameTextField.equals(orgName)) {
			System.out.println(orgName + " is created.");
		} else {
			System.out.println(orgName + " is not Created.");
		}

		// Verifying the phone number
		String savedPhno = driver.findElement(By.id("dtlview_Phone")).getText();
		if (savedPhno.equals(phoneNumber)) {
			System.out.println("Org is created with " + phoneNumber);
		} else {
			System.out.println("org is not created with " + phoneNumber);
		}
		
		System.out.println("createOrganizationTestWithPhoneNumber is executed.");
	}

}

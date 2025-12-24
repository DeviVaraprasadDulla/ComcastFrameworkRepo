package com.comcat.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcat.crm.baseclass.BaseClass;

public class CreateContactTest extends BaseClass {
	
	@Test(groups="smokeTest")
	public void createContact() throws EncryptedDocumentException, IOException {
		// Retrieve the data from the excel file
				String contactName = excelUtility.getDataFromExcelFile("contacts", 1, 2) + javaUtility.getRandomNumber();
				// Step 4: creating contact
				driver.findElement(By.partialLinkText("Contacts")).click();
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(contactName);
				driver.findElement(By.xpath("//input[@title= 'Save [Alt+S]']")).click();

		//Verifications 
				String savedData = driver.findElement(By.className("dvHeaderText")).getText();

				if (savedData.contains(contactName)) {
					System.out.println("Contact is created sucessfully. " + contactName);
				} else {
					System.out.println("Contact is not Created.");
				}
	}
	
	
	@Test(groups="regressionTest")
	public void createContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
		// Retrieve the data from the excel file
		String contactName = excelUtility.getDataFromExcelFile("contacts", 4, 3) + javaUtility.getRandomNumber();
		String orgName = excelUtility.getDataFromExcelFile("contacts", 4, 2)+ javaUtility.getRandomNumber();
		

		// Step :4 create organization
		driver.findElement(By.partialLinkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title = 'Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("ship_street")).sendKeys("hyderabad");
		driver.findElement(By.xpath("//input[@title = 'Save [Alt+S]']")).click();
		Thread.sleep(2000);
//		Step :5 Creating Contact 
		driver.findElement(By.partialLinkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		
		// clicking on + select button
		driver.findElement(By.xpath("//img[@title = 'Select']")).click();

		
		wdlib.switchToNewBrowserTab(driver, "module=Accounts&action");
		// child window operations
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();

		Thread.sleep(3000);
		
		// selecting the organization
		driver.findElement(By.id("1")).click();
	
		wdlib.switchToNewBrowserTab(driver, "module=Contacts&action");
		driver.findElement(By.xpath("//input[@title= 'Save [Alt+S]']")).click();
		//validation
		String savedOrgName = driver.findElement(By.xpath("//td[@id = 'mouseArea_Organization Name']/a")).getText();
		if (savedOrgName.equals(orgName)) {
			System.out.println("Data Integration sucessfull.");
		} else {
			System.out.println("Something went wrong during the data flow.");
		}
		
	}
	
	@Test(groups="regressionTest")
	public void createContactSupportDateTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// Retrieve the data from the excel file
		String contactName = excelUtility.getDataFromExcelFile("contacts", 4, 3) + javaUtility.getRandomNumber();
		
//		Step :5 Creating Contact 
		driver.findElement(By.partialLinkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactName);

	
		
		//Creating Date class object
		String actualDate = javaUtility.getSystemDateYYYYMMDD();
		String AfterDateRequries = javaUtility.getRequiredDateYYYYMMDD(30);
		
		//Selecting the date
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(AfterDateRequries);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(actualDate);
		driver.findElement(By.xpath("//input[@title= 'Save [Alt+S]']")).click();

//validation

		String savedData = driver.findElement(By.className("dvHeaderText")).getText();

		if (savedData.contains(contactName)) {
			System.out.println("Contact is created sucessfully. with the contact name =  " + contactName);
		} else {
			System.out.println("Contact is not Created.");
		}

	


	}


}

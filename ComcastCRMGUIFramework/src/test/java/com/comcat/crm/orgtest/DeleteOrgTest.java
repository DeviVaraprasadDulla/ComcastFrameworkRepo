package com.comcat.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	
	public static void main(String[] args) throws IOException {
		
		// Step :1 creating Object for file utility class

				FileUtility fileUtility = new FileUtility();
				ExcelUtility excelUtility = new ExcelUtility();
				JavaUtility javaUtility = new JavaUtility();
				WebDriverUtility wdlib = new WebDriverUtility();

				// Step :2 Getting the common data from property file
				String BROWSER = fileUtility.getDataFromPropertyFile("browser");
				String URL = fileUtility.getDataFromPropertyFile("url");
				String USERNAME = fileUtility.getDataFromPropertyFile("username");
				String PASSWORD = fileUtility.getDataFromPropertyFile("password");

				String orgName = excelUtility.getDataFromExcelFile("org", 1, 2) + javaUtility.getRandomNumber();

				// Step :3 launch the browser and navigate to the web page.

				WebDriver driver = null;

				if (BROWSER.equals("firefox"))
					driver = new FirefoxDriver();
				else
					driver = new ChromeDriver();

				wdlib.waitForPageToLoad(driver);
				driver.manage().window().maximize();
				driver.get(URL);

				// creating Page Object Model class object

				LoginPage loginPage = new LoginPage(driver);
				HomePage homePage = new HomePage(driver);
				OrganizationsPage orgPage = new OrganizationsPage(driver);
				CreateNewOrganizationPage createNewOrg = new CreateNewOrganizationPage(driver);
				OrganizationInfoPage orgInfoPage = new OrganizationInfoPage(driver);

				// user login

				loginPage.loginAction(USERNAME, PASSWORD,URL);
//				homePage.clickOnOrgLink();
				homePage.getOrganizationLink().click();

				// step :4 Create organization
				orgPage.getCreateOrg().click();

//				createNewOrg.createOrg(orgName, "hyderabad");
				createNewOrg.createOrg(orgName, "hyderabad", "Energy");

				// verifying whether the organization is created or not

				String savedText = orgInfoPage.getHeaderTextElement().getText();

				if (savedText.contains(orgName)) {
					System.out.println(orgName + " Organization Created.");
				} else {
					System.out.println("Organization not yet Created.");
				}
				
				
				homePage.getOrganizationLink().click();
				orgPage.getSearchField().sendKeys("sasi");
		
				orgPage.searchBy("Organization Name");
				orgPage.getSearchNowBtn().click();
				
				driver.findElement(By.xpath("//a[contains(text(),'"+orgName+"')]/parent::td/following-sibling::td/a[contains(text(),'del')]")).click();
		
		
	}

}

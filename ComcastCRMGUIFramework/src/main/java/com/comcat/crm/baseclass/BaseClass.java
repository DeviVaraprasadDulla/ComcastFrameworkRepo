package com.comcat.crm.baseclass;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.genericfileutility.ExcelUtility;
import com.comcast.crm.genericfileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	public FileUtility fileUtility = new FileUtility();
	public ExcelUtility excelUtility = new ExcelUtility();
	public JavaUtility javaUtility = new JavaUtility();
	public DataBaseUtility dblib = new DataBaseUtility();
	public WebDriverUtility wdlib = new WebDriverUtility();
	
	

	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void createDBConnection() throws SQLException {
		dblib.getDBConnection();
	}

//	@Parameters("browser")
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void launchBrowswer(/*String browser*/) throws IOException {
		String BROWSER =fileUtility.getDataFromPropertyFile("browser");
		System.out.println(BROWSER);
		if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();
		wdlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();
		
		sdriver = driver;
		UtilityClassObject.setWebDriver(driver);

	}

	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void loginUser() throws IOException {
		
		LoginPage loginPage = new LoginPage(driver);
		String URL = fileUtility.getDataFromPropertyFile("url");
		String USERNAME = fileUtility.getDataFromPropertyFile("username");
		String PASSWORD = fileUtility.getDataFromPropertyFile("password");
		loginPage.loginAction(USERNAME, PASSWORD,URL);
		

	}

	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void logOutUser() {
		HomePage homePage = new HomePage(driver);
		homePage.logoutUser();
	}

	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void quitBroswe() {
		UtilityClassObject.getWebDriver().quit();
	}

	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void closeDBConnection() throws SQLException {
		dblib.closeDBConnection();

	}

}

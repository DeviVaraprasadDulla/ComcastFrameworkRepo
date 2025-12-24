package com.practice.reports;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {

	@Test
	public void createContact() {
		
		WebDriver driver  = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/index.php?action=Login&module=Users");
		
		String file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
//		step-1 spark report config
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("./AdvanceReport/report.html");
		extentSparkReporter.config().setDocumentTitle("CRM Test SUite Results.");
		extentSparkReporter.config().setReportName("CRM Report.");
		extentSparkReporter.config().setTheme(Theme.DARK);
		
//		step-2  add env information & create test
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("OS", "Window-10");
		extentReports.setSystemInfo("BROWSER", "Chrome-10");
		
		ExtentTest test = extentReports.createTest("createContact");
	
		
		
		test.log(Status.INFO,"login to app.");
		test.log(Status.INFO,"Naviagate to contact page.");
		test.log(Status.INFO,"Create contact.");
		if("HDFC".equals("HDF")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.log(Status.FAIL,"Contact is not created.");
			test.addScreenCaptureFromBase64String(file,"Error file");
		}
		test.log(Status.INFO,"logout from app");
		extentReports.flush();

	}

}

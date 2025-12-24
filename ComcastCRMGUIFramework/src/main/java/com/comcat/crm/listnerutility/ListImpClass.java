package com.comcat.crm.listnerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcat.crm.baseclass.BaseClass;

public class ListImpClass  implements ITestListener, ISuiteListener{
	
	public ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		sparkReporter = new ExtentSparkReporter("./CRMAdvanceReports/report_"+time+".html");
		sparkReporter.config().setDocumentTitle("crm test suite result");
		sparkReporter.config().setReportName("CRM Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("OS", "Window-10");
		extentReport.setSystemInfo("BROWSER", "Chrome-11");
		
	}
	
	@Override
	public void onFinish(ISuite suite) {
		extentReport.flush();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extentReport.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName()+"==> started <==");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+" ==> is Completed. <==");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		String date = new Date().toString().replace(" ","_").replace(":", "_");
		
		WebDriver driver = BaseClass.sdriver;
		
		//take screenshot
		
		String file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(file,(testName+date));
		
		test.log(Status.PASS, result.getMethod().getMethodName()+" ==> is Failed. <==");
	
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+" ==> is Skipped. <==");
	}

}

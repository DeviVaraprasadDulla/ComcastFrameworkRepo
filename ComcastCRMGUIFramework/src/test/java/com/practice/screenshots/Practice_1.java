package com.practice.screenshots;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.comcat.crm.baseclass.BaseClass;

public class Practice_1  extends BaseClass{
	
	@Test
	public void Test1() throws IOException {
		
		WebDriver driver = BaseClass.sdriver;
		
		File fis  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fis, new File("./screenshot/xyz.png"));
	}

}

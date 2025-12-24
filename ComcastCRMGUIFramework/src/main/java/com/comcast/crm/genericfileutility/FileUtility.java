package com.comcast.crm.genericfileutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	public String getDataFromPropertyFile(String key) throws IOException {
		File file = new File("./configAppData/commonData.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	

}

package com.comcat.crm.listnerutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoPractice {
	
	int a;
	int b;
	String name;
	public static void main(String[] args) {
		DemoPractice demo = new DemoPractice();
		System.out.println(demo.a);
		System.out.println(demo.b);
		System.out.println(demo.name);
		WebDriver driver = new ChromeDriver();
		Actions action = new Actions(driver);
		
	
	}

}

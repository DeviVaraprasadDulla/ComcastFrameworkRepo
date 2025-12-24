package com.parctice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcat.crm.baseclass.BaseClass;


// this class is for examples on listeners

//@Listeners(com.comcat.crm.listnerutility.ListImpClass.class)
public class InvoiceTest extends BaseClass {
	
//	@Test(retryAnalyzer = com.comcat.crm.listnerutility.RetryListenerImp.class)
	@Test
	public void activateSim() {
		
		System.out.println("Create Invoice method is executed.");
		String title= driver.getTitle();
		Assert.assertEquals(title, "login");
		
	}
	
	@Test
	public void createInvoiceWithContactNameTest() {
		System.out.println("Create Invoice with contact Name is executed.");
	}

}

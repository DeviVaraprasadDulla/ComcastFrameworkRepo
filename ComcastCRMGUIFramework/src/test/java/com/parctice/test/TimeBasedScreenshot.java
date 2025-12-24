
package com.parctice.test;

import java.util.Date;

public class TimeBasedScreenshot {
	
	
	public static void main(String[] args) {
		
		String date = new Date().toString().replace(" ","_").replace(":", "_");
		System.out.println(date);
	}

}

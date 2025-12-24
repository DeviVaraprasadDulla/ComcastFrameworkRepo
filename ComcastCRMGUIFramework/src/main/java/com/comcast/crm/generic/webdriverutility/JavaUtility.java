package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		return new Random().nextInt(5000);
	}

	public String getSystemDateYYYYMMDD() {

		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	public String getRequiredDateYYYYMMDD(int days) {
		Date dateObj = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		simpleDate.format(dateObj);
		// Creating Calendar class object
		Calendar cal = simpleDate.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String date = simpleDate.format(cal.getTime());

		return date;
	}

}

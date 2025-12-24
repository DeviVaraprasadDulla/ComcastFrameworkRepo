package com.comcast.crm.genericfileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String getDataFromJsonFile(String key) throws IOException, ParseException {

		FileReader fis = new FileReader("./configAppData/appCommonData.json");

		JSONParser jsonparser = new JSONParser();
		Object obj = jsonparser.parse(fis);

		JSONObject jsonObject = (JSONObject) obj;

		return jsonObject.get(key).toString();

	}

}

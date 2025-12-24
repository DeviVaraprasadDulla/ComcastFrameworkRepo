package com.comcast.crm.genericfileutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetName,int row,int cell) throws EncryptedDocumentException, IOException {
		
		File path = new File("./testdata/testScriptData.xlsx");
		FileInputStream excelFis = new FileInputStream(path);

		Workbook book = WorkbookFactory.create(excelFis);

		Sheet sheet = book.getSheet(sheetName);
		
		return sheet.getRow(row).getCell(cell).toString();
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		File path = new File("./testdata/testScriptData.xlsx");
		FileInputStream excelFis = new FileInputStream(path);

		Workbook book = WorkbookFactory.create(excelFis);

		Sheet sheet = book.getSheet(sheetName);
		
		return sheet.getLastRowNum();
		
	}
	
	public void setDataIntoExcel(String sheetName,int row,int cell,String data)throws EncryptedDocumentException, IOException {
		File path = new File("./testdata/testScriptData.xlsx");
		FileInputStream excelFis = new FileInputStream(path);
		Workbook book = WorkbookFactory.create(excelFis);
		book.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream(path);
		book.write(fos);
		book.close();
		
	}

}

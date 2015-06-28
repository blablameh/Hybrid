package com.hybrid.framework.execution;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import static com.hybrid.framework.platforms.Browsers.*;
import static com.hybrid.framework.reports.Reports.setXLValues;

public class Parameterization {
	
public static String Filepath = "Test-input\\input.xls";
public static String SheetName = "configuration";
public static String SheetName1 = "objects";
public static Workbook wbook;
public static WritableWorkbook wwbCopy;
public static Sheet shSheet;
public static String testCaseID;
public static WebDriver driver;
public static String gxpath;
	
// Read the XL report
public static String getContent(String Filepath, String SheetName, String Columnname, int Rowno) throws BiffException, IOException{
		
		Workbook wb = Workbook.getWorkbook(new File(Filepath));
		String getContentFromXl = wb.getSheet(SheetName).getCell(wb.getSheet(SheetName).findCell(Columnname).getColumn(), Rowno).getContents();
		return getContentFromXl;
		
	}


// To read the column values in XL using while loop

public static void run() throws BiffException, IOException, WriteException{
	int a=1;
	
	while(true){
		
	String actioncoloumn = getContent(Filepath, SheetName1, "Action", a);
	String values = getContent(Filepath, SheetName1, "Values", a);
	String xpath = 	getContent(Filepath, SheetName1, "X-path Locator", a);
	
	String end = getContent(Filepath, SheetName1, "Testcase id", a);
	testCaseID = end;
	gxpath=xpath;
	String expected = getContent(Filepath, SheetName1, "Expected Content", a);
	String readyTest = getContent(Filepath, SheetName1, "Ready to test – (Yes/No)", a);
	
	if(end.equalsIgnoreCase("end")){
		break;
	}
	
	if(readyTest.equalsIgnoreCase("yes")){
	
		Keywords.keyword(actioncoloumn, xpath, values, expected, a, a);
	
	}

else{
	
	//System.out.println("Not Tested, Set the \"end\" position part correctly in XLSheet");
	if(!readyTest.isEmpty() && readyTest.length()!=0){
	setXLValues("objects", 9, a, "Not ready");
	}
}
	
a++;	
	}
	
}

public static void exit() throws WriteException, IOException{
	wwbCopy.write();
	wwbCopy.close();
	wbook.close();
	driver.quit();
	System.out.println("******Testcases Completed******");
	
	
}

// Main functions

public static void main(String[] args) throws BiffException, IOException, WriteException {
	
	//Filepath="Test-input/" + args[0];
			
	Browser();
	run();
    exit();
	
	}				
    

	
}
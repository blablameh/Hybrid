package com.hybrid.framework;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import static com.hybrid.framework.FontColour.*;

public class Parameterization {
	
public static String Filepath = "E:\\xl-datas\\data-values.xls";
public static String SheetName = "configuration";
public static String SheetName1 = "objects";
public static Workbook wbook;
public static WritableWorkbook wwbCopy;
public static Sheet shSheet;
public static String testCaseID;


public static WebDriver driver;
public static String gxpath;
	
public static String getContent(String Filepath, String SheetName, String Columnname, int Rowno) throws BiffException, IOException{
		
		Workbook wb = Workbook.getWorkbook(new File(Filepath));
		String getContentFromXl = wb.getSheet(SheetName).getCell(wb.getSheet(SheetName).findCell(Columnname).getColumn(), Rowno).getContents();
		return getContentFromXl;
		
	}


public static void Browser() throws BiffException, IOException, WriteException{
	
// FontColor	
	
	try{
		
		wbook = Workbook.getWorkbook(new File("E:\\xl-datas\\data-values.xls"));
		wwbCopy = Workbook.createWorkbook(new File("E:\\xl-datas\\output.xls"), wbook);
		shSheet = wwbCopy.getSheet(1);
	   } catch (Exception e) {
		
		e.printStackTrace();
		
						     }
	String browser = getContent(Filepath, SheetName, "Browser", 1);
	
	if(browser.equalsIgnoreCase("Firefox")){
	
		
	// Set firefox profile
		
	FirefoxProfile fp = new FirefoxProfile(new File("C:\\Users\\Praveen\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\6jngal82.default"));
	//fp.setPreference("network.proxy.type", 1);
	fp.setPreference("network.proxy.http", "10.200.1.3");
    fp.setPreference("network.proxy.http_port", "3128");
		
    
		driver = new FirefoxDriver(fp);
		driver.manage().window().maximize();
			
		
	// Get the URL values from XL
		
		String statusCode = getContent(Filepath, SheetName, "URL", 1);

		driver.get(statusCode);	
		URL code = new URL(statusCode);
		
		// To set the status for website link
		
		try{
		HttpURLConnection http = (HttpURLConnection)code.openConnection();
		int status = http.getResponseCode();
		System.out.println(status);	
		}catch (Exception e){
			
			e.printStackTrace();
			
		}
		
		
	} 
	
	// Chrome driver
	
	else if(browser.equalsIgnoreCase("chrome"))
	{
		
		System.setProperty("webdriver.chrome.driver" , "E:\\Database testing\\Chrome\\chromedriver.exe" );
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		String statusCodechrome = getContent(Filepath, SheetName, "URL", 1);
		driver.get(statusCodechrome);
		
		URL codeChrome = new URL(statusCodechrome);
		
		try{
			HttpURLConnection http = (HttpURLConnection)codeChrome.openConnection();
			int status = http.getResponseCode();
			
			setXLValues("configuration", 3, 1, String.valueOf(status));
			setXLValues("configuration", 2, 1, "Pass");
			
			System.out.println(status);	
			}catch (Exception e){
				
				setXLValues("configuration", 2, 1, "Fail");

				
				e.printStackTrace();
				
			}
			
			
		}
		
	
	// Internet explorer driver
	
	else{
		
		System.setProperty("webdriver.ie.driver", "E:\\Database testing\\IE\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.get(getContent(Filepath, SheetName, "URL", 1));
	}
	
	
}

// To read the column values in XL using loop

public static void run() throws BiffException, IOException, WriteException{
	int a=1;
	
	while(true){
		
	String actioncoloumn = getContent(Filepath, SheetName1, "Action", a);
	String values = getContent(Filepath, SheetName1, "Values", a);
	String xpath = 	getContent(Filepath, SheetName1, "Locator", a);
	
	String end = getContent(Filepath, SheetName1, "S.No", a);
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
	
	System.out.println("Not Tested, Set the \"end\" position part correctly in XLSheet");
}
	
a++;	
	}
	
}
// exit from the Chrome and Firefox

public static void exit() throws WriteException, IOException{
	wwbCopy.write();
	wwbCopy.close();
	wbook.close();
	driver.quit();
	
}

// Re-usability of xpath element
public static WebElement getXpathElement(String xpath){
	
	try{
	WebDriverWait Ww = new WebDriverWait(driver, 30);
	
	return Ww.until(ExpectedConditions.visibilityOfElementLocated(((By.xpath(xpath)))));
	}catch(Exception e){
		
		
	}
	return null;
}



// Main functions

public static void main(String[] args) throws BiffException, IOException, WriteException {
	
	Browser();
	run();
    exit();
					

	
}

}
// Element not found issue not find;
//http://demoqa.com/menu/
//http://bootboxjs.com/
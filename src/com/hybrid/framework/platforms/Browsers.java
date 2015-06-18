package com.hybrid.framework.platforms;

import static com.hybrid.framework.reports.Reports.*;
import static com.hybrid.framework.execution.Parameterization.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browsers {

	public static void Browser() throws BiffException, IOException, WriteException {
		
		// Read the exist input file and create the output result for test-cases
		
		try{
			
			wbook = Workbook.getWorkbook(new File("Test-input\\input.xls"));
			wwbCopy = Workbook.createWorkbook(new File("Test-result\\output.xls"), wbook);
			shSheet = wwbCopy.getSheet(1);
		   } catch (Exception e) {
			
			e.printStackTrace();
			
							     }
		
		// Get the method name from Parameterization class for browser
			String browser = getContent(Filepath, SheetName, "Browser", 1);
			if(browser.equalsIgnoreCase("Firefox")){
		
		// Set firefox profile
			FirefoxProfile fp = new FirefoxProfile(new File("C:\\Users\\Praveen\\AppData\\Local\\Mozilla\\Firefox\\Profiles\\6jngal82.default"));
		//fp.setPreference("network.proxy.type", 1);
			fp.setPreference("http.proxyHost", "10.200.1.3");
			fp.setPreference("http.proxyPort", "3128");
			driver = new FirefoxDriver(fp);
			driver.manage().window().maximize();
			
		// Get the method name from Parameterization class for URL
			String statusCode = getContent(Filepath, SheetName, "URL", 1);
			driver.get(statusCode);	
			URL code = new URL(statusCode);
			
		// To set the status for test link
		try{
			HttpURLConnection http = (HttpURLConnection)code.openConnection();
			int status = http.getResponseCode();
			System.out.println(status);	
			}catch (Exception e){
				e.printStackTrace();
								}
		 } 
		
		// Chrome driver platform
		
		else if(browser.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("http.proxyHost", "10.200.1.3");
			System.setProperty("http.proxyPort", "3128");
			System.setProperty("webdriver.chrome.driver" , "Drivers\\chromedriver.exe" );
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
				if(status>=400 || status>=500){
					setXLValues("configuration", 2, 1, "Fail");
					setXLValues("configuration", 3, 1, String.valueOf(status));
					
				} else
				 {			
				setXLValues("configuration", 3, 1, String.valueOf(status));
				setXLValues("configuration", 2, 1, "Pass");
				System.out.println(status);
				 }
				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
		
		// Internet explorer driver platform
		
		else{
			
			System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(getContent(Filepath, SheetName, "URL", 1));
		    }
		
	}
	
}
package com.hybrid.framework.execution;

import static com.hybrid.framework.reports.Reports.*;
import static com.hybrid.framework.execution.Parameterization.*;
import static com.hybrid.framework.execution.Screenshot.*;
import static com.hybrid.framework.reports.TimeTaken.*;
import static com.hybrid.framework.locators.XpathLocator.*;

import java.io.IOException;
import java.util.Date;

import jxl.write.WriteException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	

	
	public static void keyword(String actionContent,String xpath,String value, String expected, int statusRow, int reportStatus) throws WriteException, IOException{
		
		String startTimes = sdf.format(new Date().getTime());
		String endTimes =null;

		try{			
			
		switch(actionContent.toLowerCase()){
	 
		
		case "click":
			try{
				
	
			getXpathElement(xpath).click();
			endTimes = sdf.format(new Date().getTime());
			
			setXLValues("objects", 9, statusRow, "Pass");	
			setReports("objects", 12, reportStatus, "Element clicked");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));
			
			} catch (Exception e){
				
				endTimes = sdf.format(new Date().getTime());
				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Element not clicked");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

				
			}
			
			break;
	
		case "enter":
			try{
				
			getXpathElement(xpath).sendKeys(value);
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");
			setReports("objects", 12, reportStatus, "Elements entered");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));


			}catch (Exception e) {
				endTimes = sdf.format(new Date().getTime());

				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Elements not entered");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

				getScreenshot(xpath);
				
			}
			
			break;
		
		case "checkcontent":
			

			String actual = getXpathElement(xpath).getText();
			
			endTimes = sdf.format(new Date().getTime());

			
			if(actual.equals(expected)){
			setXLValues("objects", 9, statusRow, "Pass");
			setXLValues("objects", 7, statusRow, actual);
			setReports("objects", 12, reportStatus, "Content matched");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			

			
			}else 
			{
			
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Fail");
			setXLValues("objects", 7, statusRow, actual);
			setReports("objects", 12, reportStatus, "Contents mismatched");		
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			}
			break;
		
		case "dropdown":
			
			WebElement dropdown = getXpathElement(xpath);
			try{
			Select se = new Select(dropdown);
			se.selectByValue(value);
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");
			setReports("objects", 12, reportStatus, "Dropdown works");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));


			}catch(Exception e){
			
			endTimes = sdf.format(new Date().getTime());
	
			setXLValues("objects", 9, statusRow, "Fail");
			setReports("objects", 12, reportStatus, "Dropdown not works");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			}
			//se.selectByVisibleText(value);
			break;
			
		case "mouseover":
	
			try{
			Actions action = new Actions(driver);
			
			WebElement mousehover = getXpathElement(xpath);
			action.moveToElement(mousehover).perform();
			
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");
			setReports("objects", 12, reportStatus, "Mouse-hover works");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			}catch (Exception e){

				endTimes = sdf.format(new Date().getTime());
				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Mouse-hover not works");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

				
			}
			
			break;
	
		case "windowspopup":	
			
			break;
		
		case "frames":
			
			break;
			
		case "alertaccept":	
			
			Alert alert = driver.switchTo().alert();
			try{
			
			alert.accept();
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");	
			setReports("objects", 12, reportStatus, "Alert works");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));
			
			
			} catch (Exception e){
				
				endTimes = sdf.format(new Date().getTime());
				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Alert not works");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));


			}
			
			break;
			
		case "alertdismiss":
			
			Alert alert1 = driver.switchTo().alert();
			try{
			alert1.dismiss();
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");
			setReports("objects", 12, reportStatus, "Alert dismissed");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			}catch(Exception e){
				
				
				endTimes = sdf.format(new Date().getTime());
				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Alert not works");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));


				
			}
			break;
			
		case "alertgettext":
			
			
			break;
			
		case "alertsendkeys":
			
			Alert alert2 = driver.switchTo().alert();
			try{
			driver.switchTo().alert().sendKeys(value);
			alert2.accept();
			endTimes = sdf.format(new Date().getTime());

			setXLValues("objects", 9, statusRow, "Pass");
			setReports("objects", 12, reportStatus, "Alert sendkeys works");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));

			} catch (Exception e){
			
				endTimes = sdf.format(new Date().getTime());

				setXLValues("objects", 9, statusRow, "Fail");
				setReports("objects", 12, reportStatus, "Alert sendkeys not works");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));
				
				
			}
			
			break;
			
		case "getsize":
			
			Dimension dimensionSize = driver.findElement(By.xpath(xpath)).getSize();
			
			int dimensionWidth = dimensionSize.width;
			int dimensionHeight = dimensionSize.height;
			
			try{
			
			endTimes = sdf.format(new Date().getTime());
			
				
			setXLValues("objects", 9, statusRow, "Pass");
			setXLValues("objects", 13, reportStatus, "(" +String.valueOf(dimensionWidth)+" , "+ String.valueOf(dimensionHeight)+ ")");
			setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));
			setReports("objects", 12, reportStatus, "Size works fine");
			
			
			
			System.out.println("Dimension width is "+dimensionWidth);
			System.out.println("Dimension height is "+dimensionHeight);
			} catch(Exception e){
				
				endTimes = sdf.format(new Date().getTime());

				setXLValues("objects", 9, statusRow, "Fail");
				setXLValues("objects", 13, reportStatus, String.valueOf(dimensionWidth)+" , "+ String.valueOf(dimensionHeight));
				setReports("objects", 12, reportStatus, "Size doesn't work");
				setXLValues("objects", 11, reportStatus, getCurrentTime(startTimes, endTimes));


				
			}
			
			break;
			
		case "getlocation":
			
			Point point = driver.findElement(By.xpath(xpath)).getLocation();
			try{
			int x = point.x;
			int y = point.y;
			System.out.println("Location x is " +x);
			System.out.println("Location y is " +y);
			} catch(Exception e){
				
				
				
			}
			break;
			
		case "uploadFile":	
			
			break;
			
		case "toolTip":
			
			break;
			
		case "datePicker":
			
			break;
			
		case "back":
			
			break;
			
		case "checkbox":
			
			break;
			
		case "selectalloption":
			
			break;

        case "gettitle":
			
			String title = driver.getTitle();
			try{
			
			setXLValues("objects", 7, statusRow, title);
			}catch(Exception e){
				
				setXLValues("objects", 9, statusRow, "Fail");
				
			}
				break;
        
        case "getcurrenturl":		
			
        	String url = driver.getCurrentUrl();
        	try{
        		
        		setXLValues("objects", 7, statusRow, url);
        		
        	}catch(Exception e){
        		
        		setXLValues("objects", 9, statusRow, "Fail");
        		
        	}
        	
        	break;
        	
        	
			
		}
		
		
		}catch(Exception e){
			setXLValues("objects", 9, statusRow, "Element not Found");			
				
			}
		
	}
	

	
}
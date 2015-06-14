package com.hybrid.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
	
	
	
		public static void Dimension() throws InterruptedException{
		WebDriver driver;
		SimpleDateFormat sdf = new SimpleDateFormat("ss");

		String startTime1 = sdf.format(new Date().getTime());

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.apptha.com");

		Dimension dimensionSize = driver.findElement(By.xpath("//*[@id='nav-container']/div/a")).getSize();

		System.out.println("Height= "+dimensionSize.height);
		System.out.println("Width= "+dimensionSize.width);
		Thread.sleep(1000);
		driver.quit();
		
		String endTime1 = sdf.format(new Date().getTime());
		System.out.println("Time taken "+getCurrentTime(startTime1, endTime1));

		
	}
		
		public static String getCurrentTime(String startTime, String endTime){
			
			
			
			int timetaken = Integer.valueOf(endTime)-Integer.valueOf(startTime);
			System.out.println(startTime);
			System.out.println(endTime);
			if(timetaken>60){
			timetaken = timetaken/60;
			return timetaken+" Min";
			}else{
				return timetaken + " sec";
			}
		 }
		
		public static void main(String[] args) throws InterruptedException {
			Dimension();

		}
		
}
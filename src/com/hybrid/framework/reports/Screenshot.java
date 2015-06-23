package com.hybrid.framework.reports;

import static com.hybrid.framework.execution.Parameterization.driver;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.hybrid.framework.execution.Parameterization;

public class Screenshot {
	
	public static String screenPath = "Screenshots\\";
	public static UUID dynamic;
	public static String filePath;
	
	
	public static String getScreenshot(String xpath) throws IOException{
		
		
		WebElement autoCapt = driver.findElement(By.xpath(xpath));   
		//Get entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage bi = ImageIO.read(screenshot);
		//Get the location of element on the page
		Point p = autoCapt.getLocation();
		bi.getTransparency();
		Graphics g = bi.getGraphics();
		g.setColor(Color.RED.brighter());
		
		//Get width and height of the element
		g.drawRect(p.getX(), p.getY(), autoCapt.getSize().width, autoCapt.getSize().height);
		g.setFont(new Font("verdana", Font.BOLD, 16));
		g.setColor(Color.RED.brighter());
		g.dispose();
		ImageIO.write(bi, "png", screenshot);
		//Copy the element screenshot to disk
		File newFile = new File(screenPath + Parameterization.testCaseID + ".png");
		FileUtils.copyFile(screenshot, newFile );
		filePath=newFile.getAbsolutePath();
		return filePath;
		
		
	}
	
	

}

package com.hybrid.framework;

import static com.hybrid.framework.Parameterization.wwbCopy;
import static com.hybrid.framework.Screenshot.getScreenshot;

import java.io.File;

import jxl.write.Label;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WriteException;


public class FontColour {
	
	public static void setXLValues(String sheetName, int columnNo, int rowNo, String xlData) throws WriteException{
	
		
	WritableSheet ws = wwbCopy.getSheet(sheetName);
	Label le = new Label(columnNo, rowNo, xlData);
	try{
		
		ws.addCell(le);
		if(xlData.equalsIgnoreCase("Fail")){
			getScreenshot(Parameterization.gxpath);
			WritableHyperlink wa = new WritableHyperlink(ws.findCell("Screenshot").getColumn(), rowNo, new File(Screenshot.filePath));
			ws.addHyperlink(wa);
		}
		}catch (Exception e){
			
			
			
		}
	}                 
	
	
	public static void setReports(String sheetName, int columnNo, int rowNo, String xlReport){
		
		WritableSheet ws = wwbCopy.getSheet(sheetName);
		Label le = new Label(columnNo, rowNo, xlReport);
		try{
			
			ws.addCell(le);
			
			
					
		}catch (Exception e){
			
			System.out.println("XL Report status column ");
			e.printStackTrace();
			
			
		}
		
		
	}
	
	
	
	
}
package com.hybrid.framework.reports;

import static com.hybrid.framework.execution.Parameterization.wwbCopy;
import static com.hybrid.framework.reports.Screenshot.*;
import java.io.File;

import jxl.write.Label;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import com.hybrid.framework.execution.Parameterization;

public class Reports {

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

package com.hybrid.framework.reports;

import java.text.SimpleDateFormat;

public class TimeTaken {
	
public static SimpleDateFormat sdf = new SimpleDateFormat("ss");

	
	public static String getCurrentTime(String startTime, String endTime){
		
		

		int timetaken = Integer.valueOf(endTime)-Integer.valueOf(startTime);
		if(timetaken>60){
		timetaken = timetaken/60;
		return timetaken+" Min(s)";
		}else{
			return timetaken + " sec(s)";
	}
	
	}
	
	
	

}

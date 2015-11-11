package com.atense.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtil
 * 
 * @author <a href="#" target="_blank">Kyle</a> 2015-11-04
 */
@SuppressLint("SimpleDateFormat")
public class DateTimeUtils {

    @SuppressLint("SimpleDateFormat")
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * long time to string
     * 
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getFormatDate(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getFormatDate(long timeInMillis) {
        return getFormatDate(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @return
     */
    public static String getCurrentTimeInString() {
        return getFormatDate(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getFormatDate(getCurrentTimeInLong(), dateFormat);
    }
    
    /**
	 * 获取两个时间的日期差
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysBetween(Date startDate, Date endDate){  
		Calendar start=Calendar.getInstance();
		start.setTime(startDate);
		Calendar end=Calendar.getInstance();
		end.setTime(endDate);	
		int unit=1;
        if(start.after(end)){  
            Calendar swap = start;  
            start = end;  
            end = swap;  
            unit =-1;
        }  
        int days = end.get(Calendar.DAY_OF_YEAR)- start.get(Calendar.DAY_OF_YEAR);  
        int y2 = end.get(Calendar.YEAR);  
        if (start.get(Calendar.YEAR) != y2) {  
            start = (Calendar) start.clone();  
            do {  
                days += start.getActualMaximum(Calendar.DAY_OF_YEAR);  
                start.add(Calendar.YEAR, 1);  
            }while(start.get(Calendar.YEAR) != y2);  
        }  
        return days*unit;  
    }
    
}

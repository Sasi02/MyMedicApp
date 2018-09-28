package com.example.user.mymedic.Helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Sachith Rukshan on 9/27/2018.
 */

public class TypeConverter {
    public static Date toDate(String dateStr){
        String[] dateParts = dateStr.split("/");
        return new Date(Integer.parseInt(dateParts[0]),Integer.parseInt(dateParts[1])-1,Integer.parseInt(dateParts[2]));
    }

    public static String toString(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String dateStr = String.format("%d/%d/%d",calendar.get(Calendar.YEAR) - 1900,calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
        return dateStr;
    }
}

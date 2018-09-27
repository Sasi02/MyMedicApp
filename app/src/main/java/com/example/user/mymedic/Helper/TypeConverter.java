package com.example.user.mymedic.Helper;

import java.util.Date;

/**
 * Created by Sachith Rukshan on 9/27/2018.
 */

public class TypeConverter {
    public static Date toDate(String dateStr){
        String[] dateParts = dateStr.split("/");
        return new Date(dateParts[0],dateParts[1]-1,dateParts[2]);
    }

    public static String toString(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String dateStr = String.format("%d/%d/%d",calendar.get(Calendar.YEAR) - 1900,calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH));
        return dateStr;
    }
}

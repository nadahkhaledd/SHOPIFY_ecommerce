package org.example.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public Date convertStringToDate(String dateString){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

//    public Date convertStringToDate(String format, String dateString){
//        try {
//            return new SimpleDateFormat(format).parse(dateString);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Date changeFormat(String format, Date date){
        SimpleDateFormat DateFormat = new SimpleDateFormat(format);
        Date formattedDate = convertStringToDate(DateFormat.format(date));
        return formattedDate;
    }

    public String convertDateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        return dateString;
    }

}

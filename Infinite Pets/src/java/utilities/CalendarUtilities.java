/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * ALl utilities/parsing dealing with Calendar object.
 * @author Riley
 */
public class CalendarUtilities {
    /**
     * Returns a fully named month based on Calendar.get(Calendar.MONTH), which is an integer.
     * @param month given month to determine name of month.
     * @return the string representing fully named month.
     */
    public static String getMonthName(int month) {
        
        switch (month) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "Unknoown month";
        }
    }
    
    public static String getAmOrPm(int amOrPm) {
        if (amOrPm == 0)
            return "AM";
        return "PM";
    }
    
    public static List<String> getMonths() {
        ArrayList<String> months = new ArrayList<>();
        
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("july");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        
        
        
        return (List) months;
    }
    
}

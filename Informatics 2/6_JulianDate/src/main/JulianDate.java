package main;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * This class contains the new datatype JulianDate
 * @author katja
 * @author lucas
 * @version 0.1
 */

public class JulianDate {

    /** variables and fields **/
    public static final LocalDateTime START_DATE = LocalDateTime.of(-4712, 1, 1, 12,0, 0, 0);
    public float julianDayNumber;

    /** Constructors **/
    public JulianDate() {
    }

    public JulianDate(int year, int month, int day) {
    	this.julianDayNumber = this.toJulianDate(year, month, day);
    }


    public static void main (String args []) {
        System.out.println("Start date: " + START_DATE);
        System.out.println("DOW: " + START_DATE.getDayOfWeek());
        System.out.println("Julian date: " + toJulianDate(1582, 10, 15));
        System.out.println(new JulianDate(1986,10,4).julianDayNumber);
        System.out.println((int)(new JulianDate(1986,10,4).julianDayNumber) % 7);
    }
    /**
     * Returns true if date is after compareDate, false otherwise
     * @param compareDate
     * @return
     */
    public boolean isAfter(JulianDate compareDate) {
        return this.julianDayNumber > compareDate.julianDayNumber;
    }

    /**
     * Returns the days between given date and Julian day
     * @param compareDate
     * @return
     */
    public Float daysBetween(JulianDate compareDate) {
    	if (compareDate.julianDayNumber > this.julianDayNumber) {
    		 return compareDate.julianDayNumber - this.julianDayNumber;
    	} else {
    		return this.julianDayNumber - compareDate.julianDayNumber;
    	}  
    }

    /**
     * Returns today in Julian date
     * @return Julian date in float
     */
    public float today() {
        Date date = new Date();
        return toJulianDate(date.getYear(), date.getMonth(), date.getDay());
    }

    /**
     * Calculate the tomorrow day in Julian date
     * @return tomorrow in Julian date
     */
    public float tomorrow() {
        float tomorrow = today() + 1;
        return tomorrow;
    }

    /**
     * Calculate the yesterday day in Julian date
     * @return yesterday in Julian date
     */
    public float yesterday() {
        float yesterday = today() - 1;
        return yesterday;
    }

    /**
     * Converts the given date to Julian date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static float toJulianDate(int year, int month, int day) {
    	if (month == 1 || month == 2) {
    		year -= 1;
    		month += 12; // Thus, we are thinking of January and February 
    		             // as being the 13th and 14th month of the previous year
    	}
    	int a = year / 100;
    	int b = a / 4;
    	int c = 2 - a + b;

    	int e = (int)(365.25 * (year + 4716));
    	int f = (int) (30.6001 * (month + 1));

    	float julianDay = (float) (c + day + e + f - 1524.5);

    	return julianDay;
    }
}
package main;

/**
 * This class handle the MetricDate system.
 * @author lucas
 * @version 0.1
 */

public class MetricDate extends JulianDate {

    public static void main (String args[]) {

        MetricDate md = new MetricDate();

        md.julianToMetricDate(2446707.5);

    }


    /** variables and fields **/
    public static final int DaysInYear = 1000;
    public static final int WeeksInYear = 100;
    public static final int MonthsInYear = 10;


    /**
     * Converts a Julian date to Metric date
     * @param julianDayNumber
     * @return Date-string with follow pattern:
     * day.week.month.year
     */
    public String julianToMetricDate(double julianDayNumber) {

        int year  =  (int) (julianDayNumber / DaysInYear);
        int month = (int) (julianDayNumber % DaysInYear) / WeeksInYear;
        int week = (int) (julianDayNumber % DaysInYear) % WeeksInYear / MonthsInYear;
        int day = (int) ((julianDayNumber % DaysInYear) % WeeksInYear / MonthsInYear) - week + 1;

        String finalDate = day + "." + week +  "." + month + "." + year;

        System.out.println(finalDate);


        return finalDate;
    }

    /**
     * Converts a Metric date to Julian date
     * @param day in metric system
     * @param week in metric system
     * @param month in metric system
     * @param year in metric system
     * @return julian date in format: 2299161
     */
    public int metricToJulianDate(int day, int week, int month, int year) {
        int fullDate = (year * 1000) + (month * 100) + (week * 10) + day;

        return fullDate;
    }

}

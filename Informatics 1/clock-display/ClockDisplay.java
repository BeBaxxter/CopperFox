
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private int internalTime;
    boolean isAm;
    private NumberDisplay alarmHours;
    private NumberDisplay alarmMinutes;
    private boolean isOn; 
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }
    
    /**
     * Constructor for ClockDisplay objects. Creates a new
     * clock set at the time specified by the first two parameters,
     * a new alarm set at the time specified by the last two, and
     * turns the alarm on.
     */
    
    public ClockDisplay(int hour, int minute, int alarmHour, int alarmMinute){
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        alarmHours = new NumberDisplay(24);
        alarmMinutes = new NumberDisplay(60);
        setAlarm(alarmHour, alarmMinute);
        isOn = true;
        setTime(hour, minute);
    }

    /**
    * This method should get called once every minute - it makes
    * the clock display go one minute forward.
    */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Sets an alarm at a specified hour and minute
     */
    public void setAlarm(int hour, int minute){
        alarmHours.setValue(hour);
        alarmMinutes.setValue(minute);
    }
    
    /**
     * Tells if the alarm is on or off
     */
     public String alarmStatus(){
        if (isOn == true){
            return "On";
        }else{
            return "Off";
        }
    }
    
    /**
     * Turns the alarm on
     */
    public void alarmOn(){
        isOn = true;
        updateDisplay();
    }
    
    /**
     * Turns the alarm off
     */
    public void alarmOff(){
        isOn = false;
    }
    
    private int calculateInternalTime() {
        
       if(hours.getValue() < 12) {
           isAm = true;
       } 
       else {
           isAm = false;
       }  
       
       if(hours.getValue() == 0){
           return 12;
       }
       else if(hours.getValue() < 13) {
           return hours.getValue();
       }    
       else{
           return hours.getValue() -12;
       } 
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
       int lHours = calculateInternalTime();
       String missingZero = "";
       
       if (isOn && (hours.getValue() == alarmHours.getValue()) && (minutes.getValue() == alarmMinutes.getValue()))
          System.out.println("Riiiiiiiing");
             
       if(lHours < 10)
          missingZero = "0";
        
       if (isAm){
          displayString = missingZero + lHours + ":" + 
                        minutes.getDisplayValue() + " am";
       } else {
          displayString = missingZero + lHours + ":" + 
                        minutes.getDisplayValue() + " pm";  
       }      
    }
}
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
public class ClockDisplay12
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString; 
    private boolean isPm;
    private String isPmString;
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay12()
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        setTime(1, 0, "AM");
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay12(int hour, int minute, String amOrPm)
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, amOrPm);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) { 
            hours.increment();
        }
        
        if(hours.getValue() == 12 && isPm == false) {  
            isPm = true;
            } 
        else if (hours.getValue() == 12 && isPm == true) {
            isPm = false;
        }
        if (hours.getValue() == 0) {
            hours.increment();
        }
        updateDisplay();
        
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, String amOrPm)
    {
        //check if the given input is valid
        if(hour > 12) {
            System.out.println("[ERROR]: Please insert a valid value between 0-12!");
        } else {
            hours.setValue(hour);
            minutes.setValue(minute);
            isPmString = amOrPm;
            
            if(isPmString.equals("AM")) {
                isPm = false;
            }
            else {
                if(isPmString.equalsIgnoreCase("PM")) {
                isPm = true;
                }
            }   
            updateDisplay();
        }
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        updateDisplay();
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if(isPm == true)
        {
            displayString = hours.getDisplayValue() + ":" + minutes.getDisplayValue() + " PM"; 
        }
        if(isPm == false){
            displayString = hours.getDisplayValue() + ":" + minutes.getDisplayValue() + " AM";
        }
    }
}

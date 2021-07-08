
/**
 * A class that ties to copz the functions of a regular Heater
 * A Heater is set to a specific temperatur and can cange it, when asked.
 * A Heater like this could be implemented in almost every room.
 * 
 * @author Alexander Jäger, Lucas Carmohn 
 * @version 0.1 (23.11.20)
 */
public class Heater
{
    // The fields.
    private int temperature;
    private int min;
    private int max;
    private int increment;

    /**
     * Creates a Heater and sets its default temperatur to 15.
     */
    public Heater(int heaterMin, int heaterMax)
    {
        min = heaterMin;
        max = heaterMax;
        temperature = 15;
        increment = 2;
    }
    
    /**
     * Increases the current Temperatur by 2°.
     */
    public void warmer() 
    {
        int tempTemperature = temperature + increment;
        
        if(tempTemperature > max)
        {
            System.out.println("[ATTENTION:] temparature should not be higher than allowed max temparature!");
            System.out.println("[ATTENTION:] Current temparature: " + temperature);
            System.out.println("[ATTENTION:] Max temparature: " + max);
            
        } else 
        {
            temperature = temperature + increment;
        }
        
        
    }
    
    /**
     * Decreases the current Temperatur by 2°.
     */
    public void cooler() 
    {
        int tempTemperature = temperature - increment;
        
        if(tempTemperature < min)
        {
            System.out.println("[ATTENTION:] temparature should not be lower than allowed cooler temparature!");
            System.out.println("[ATTENTION:] Current temparature: " + temperature);
            System.out.println("[ATTENTION:] Min temparature: " + min);
            
        } else 
        {
            temperature = temperature - increment;   
        }
    }
    
    /**
     * Returns the current Temperature.
     */
    public int getTemperature()
    {
        return temperature;
    }
    
    /**
     * Set increment with the user input value.
     */
    public void setIncrement(int tempInc)
    {
        
        if(tempInc >= 1)
        {
            increment = tempInc; 
        } else 
        {
            System.out.println("[ATTENTION:] dont enter a negative value or zero!");
        }
    } 
}

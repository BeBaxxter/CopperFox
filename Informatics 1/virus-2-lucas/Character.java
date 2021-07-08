
/**
 * Character - this Class holds the information about an character
 *
 * @author Lucas Carmohn
 * @version 1.0
 */
public class Character
{
    private String name;
    private String message;

    /**
     * Constructor for objects of class Character
     */
    public Character(String name, String message)
    {
       this.name = name;
       this.message = message;  
    }
    
    /**
     * getter for name
     */
    public String getName(){
        return name;
    } 
    
    /**
     * getter for message
     */
    public String getMessage(){
        return message;
    }    
}

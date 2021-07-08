
/**
 * These are items that can be found in a Room in the "Stop the Virus" game
 *
 * @author Debora Weber-Wulff
 * @version 2021-02-01
 */
public class Item
{
    private String name;
    private String description;
    private String colour;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, String colour, int weight)
    {
       this.name = name;
       this.description = description;  
       this.colour = colour;
       this.weight = weight;
    }

    /**
     * A getter for the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * A getter for the description
     */
    public String getDescription() {
        return description;
    }
    
   /**
     * A getter for the colour
     */
    public String getColour() {
        return colour;
    }
    
    /**
     * A getter for the weight
     */
    public int getWeight() {
        return weight;
    }
 
    /**
     * A longer description of the item with all details
     */
    public String getLongDescription() {
        return name + ", " + description + " (" + weight + ")";
    }
    
}

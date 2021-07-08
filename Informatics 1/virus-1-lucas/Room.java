import java.util.Set;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Stop the Virus" application. 
 * "Stop the Virus" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes, D. Weber-Wulff
 * @author  Debora Weber-Wulff adapted it to Corona
 * @version 2021-01-25
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // Stores the exits for this room
    private HashMap<String, String> items; //Stores the items for this room

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }

    /**
     * Define an exit from this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
   /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

   /**
    * Define an item in this room.
    * @param name The name of the item
    * @param weight The weight of the item
    */
   public void addItem(String name, String weight) 
   {
        items.put(name, weight);
   }  

   /**
   * Return the item with the given name
   * @param name The name of the item.
   * @return the item
   */
   public String getItem(String name)
   {
       return items.get(name);
   }
   
   /**
    * Returns a list with all items in this room. ONLY for displaying
    * @return all items as a string
    */
   public String getAllItems()
   {
       return items.keySet().toString();
   }
    
   /**
    * Get the size of items in a roo
    * @return the size of items
    */ 
   public int getItemsSize()
   {
       return items.size();
   } 
}

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Stop the Virus" application. 
 * "Stop the Virus" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. It also contains items
 * 
 * @author  Michael Kölling and David J. Barnes, D. Weber-Wulff
 * @author  Debora Weber-Wulff adapted it to Corona
 * @version 2021-01-25
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // Stores the exits for this room
    private ArrayList<Item> items;  

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
        items = new ArrayList<>();
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
     * A list of item(s) in the room.
     */
    private String roomItems() {
        String s = "Items: ";
        for (Item item:items) {
            s = s + item.getLongDescription() + ", ";
        }        
        return s;
    }
    
    
    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String s = "You are " + description + ".\n" + getExitString();
        if (items != null) {
            s = s + "\n" + roomItems();
        }
        return s;
    }

    /**
     * Set an Item in the Room
     */
    public void setItem(Item it) {
        items.add(it);
    }
    
    /**
     * Get the Item from the room. This means looking to see if there was
     * such an item. 
     * 
     * @return The item that was in the room, if any
     */
    public Item getItem(String name) {
        for (Item item:items) {
            if (item.getName().equals(name)){
                return item;
            }
        }
        // there was none by this name, so return nothing
        return null;
    }
    
    /**
     * Remove the item from a room
     * 
     * @return The item that is being removed is returned, just in case it is needed
     */

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

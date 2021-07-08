import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    ArrayList<String> validCommands = new ArrayList<String>();
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        
        validCommands.add("go");
        validCommands.add("quit");
        validCommands.add("help");
        validCommands.add("look");
        validCommands.add("lock");
        validCommands.add("unlock");
        
        this.validCommands = validCommands;
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.size(); i++) {
            if(validCommands.get(i).equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Print out all command words
     */
    public void printAllCommandWords() {
        
        String commands = "";
        
        for(int i = 0; i < validCommands.size(); i++) {
            commands = commands + validCommands.get(i) + " ";
        }
        
        System.out.println(commands);
        
    }
}

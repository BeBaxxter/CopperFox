import java.util.ArrayList;
/**
 *  This class is the main class of the "Stop the Virus" application. 
 *  "Stop the Virus" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Debora Weber-Wulff adapted it to Corona
 * @version 2021-01-25
 */

public class Game 
{
    private Parser parser;
    private Player currentPlayer;
    private Character npc;
    private Room currentRoom;
    private Room previousRoom; // For just one step back
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        currentPlayer = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, mainHall, secretary, office, lab, freezer;
      
        // create the rooms
        outside     = new Room("outside the Robert Koch Institute");
        mainHall    = new Room("inside the building in the main hall");
        secretary   = new Room("in the secretary's office");
        lab         = new Room("in the vaccine laboratory");
        office      = new Room("in Prof. Drosten's office");
        freezer     = new Room("in the freezer");
        
        // initialise room exits
        outside.setExit("South", mainHall);
        
        mainHall.setExit("North", outside);
        mainHall.setExit("East", secretary);
        mainHall.setItem(new Item("basket", "An old basket.", "Gray", 5000));
   
        secretary.setExit("East", outside);
        secretary.setExit("South", office);
        secretary.setExit("West", mainHall);
        secretary.setItem(new Item("key", "TEST1", "TEST1", 5));
        secretary.setItem(new Item("computer", "TEST1", "TEST1", 500));
        secretary.setItem(new Item("cup", "TEST1", "TEST1", 50));
        
        lab.setExit("South", freezer);
        lab.setExit("East", outside);
        lab.setItem(new Item("pipettes", "TEST1", "TEST1", 30));
        lab.setItem(new Item("goggles", "TEST1", "TEST1", 70));
        lab.setItem(new Item("coat", "TEST1", "TEST1", 250));       
        lab.setItem(new Item("mask", "TEST1", "TEST1", 10));

        office.setExit("East", freezer);
        office.setExit("West", lab);
        office.setExit("North", secretary);
        office.setItem(new Item("basket", "TEST1", "TEST1", 100));
        office.setNpc(new Character("Professor Doktor Weber-Wulff", "Dont panic! You will we able to find the vaccine"));
        
        freezer.setExit("North", lab);
        freezer.setItem(new Item("vaccine", "TEST1", "TEST1", 10));

        currentRoom = outside;  // start game outside
        previousRoom = null;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Stop the Virus!");
        System.out.println("Stop the Virus is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        String commandWord = command.getCommandWord();
        
        switch (commandWord) {
            case "help":  
                printHelp();
                break;
            case "go":  
                goRoom(command);
                break;      
            case "look":  
                look();
                break;  
            case "back":  
                back();
                break;     
            case "lock":  
                lock();
                break;    
            case "unlock":  
                unlock();
                break;       
            case "take":  
                take(command);
                break;        
            case "drop":  
                drop(command);
                break;  
            case "quit":  
                wantToQuit = quit(command);
                break;         
            case "items":  
                currentPlayer.showInventory();
                break;
            case "ask":  
                askNpc();
                break;                
            default: 
                System.out.println("I don't know what you mean...");
                return false;         
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You are looking for the vaccine");
        System.out.println("that will save you from the killer virus.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom; // remember where we were
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Going back means that we have to remember where we came from. 
     * We are only remembering one previous step.
     */
    private void back() {
        currentRoom = previousRoom;
        previousRoom = null;        
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Look just looks at the room for now
     */
    private void look() {
        System.out.println("Looks like nothing is here.");
    }
    
    /**
     * Lock will eventually permit you to lock the door if you have a key.
     */
    private void lock() {
        System.out.println("You try to lock the door.");
    }
    
    /**
     * Look just looks at the room for now
     */
    private void unlock() {
        System.out.println("You try to unlock the door.");
    }
    
    /**
     * Take just take the item with the given name
     */
    private void take(Command command) {        
        if(!command.hasSecondWord()) {
            System.out.println("Please enter a name which item do you want to take.");
        }
        else {
            
            //try to get the Item
            Item rItem = currentRoom.getItem(command.getSecondWord());
          
            if (rItem != null){
                
                //if item is not to heavy
                if(currentPlayer.canItemBeTaken(rItem.getWeight())) {
                    System.out.println("You take " + rItem.getName() + " from the room");
                
                    //add item to inventory
                    currentPlayer.setItem(rItem);
                                
                    //remove item from room
                    currentRoom.removeItem(rItem.getName());     
                } else {
                    System.out.println("This item is to heavy, maybe drop some items.");
                }
            } else {
                System.out.println("Can't find this item here...");
            }       
        }     
    }
    
    /**
     * Drop, just drop the item with the given namen
     */
    private void drop(Command command) {
        Item dropItem = null;
        ArrayList<Item> inventory = currentPlayer.getInventory();
        
        
        if(!command.hasSecondWord()) {
            System.out.println("Please enter a name which item do you want to drop.");
        }
        else {
            
            if(command.getSecondWord().equals("all")) {
             
                for (Item item:inventory) {
                    currentRoom.setItem(item);
                }
                
                currentPlayer.clearInventory();
                System.out.println("Drop all items");
            }
            else {
                for (Item item:inventory) {
                    if (item.getName().equals(command.getSecondWord())){
                       dropItem = item;
                    }
                }
            
                if (dropItem != null){
                
                   //add item to inventory
                  currentPlayer.removeItem(dropItem);
                                    
                   //remove item from room
                   currentRoom.setItem(dropItem);
                   System.out.println("You drop " + dropItem.getName() + " to the floor");
                    
                } else {
                    System.out.println("Can't find this item in your inventory...");
                } 
            }     
        }  
    }
   
    /**
     * "ask" was entered. Prints the name and message of an npc
     *  if no is inside the room, a default message will be displayed
     */
    private void askNpc(){
        Character npc = currentRoom.getNpc("Professor Doktor Weber-Wulff");
        
        if(npc != null){        
            System.out.println(npc.getName() + " speaks to you: " + npc.getMessage());    
        } else {   
            System.out.println("No one is here...");
        }    
    }    
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

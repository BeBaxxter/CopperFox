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
    private Room currentRoom;
    private Room lastRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
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
        
        secretary.setExit("East", outside);
        secretary.setExit("South", office);
        secretary.setExit("West", mainHall);
        
        lab.setExit("South", freezer);
        lab.setExit("East", outside);
        
        office.setExit("East", freezer);
        office.setExit("West", lab);
        office.setExit("North", secretary);
        
        freezer.setExit("North", lab);
        
        //Define items
        freezer.addItem("Vaccine", "5");
        
        secretary.addItem("Mainkey", "10");
        secretary.addItem("Apple", "10");
        secretary.addItem("Pencil", "7");
        
        office.addItem("Flashlight", "15");
        office.addItem("Nameplate", "9");
        office.addItem("Paper", "2");
        
        mainHall.addItem("Backpack", "12");
        
        lab.addItem("Plaster", "3");
        lab.addItem("Broken Key", "6");

        currentRoom = outside;  // start game outside
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

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            back();     
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("lock")) {
            lock();
        }
        else if (commandWord.equals("unlock")) {
            unlock();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
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
            //Set last Room with current room for back command
            lastRoom = currentRoom;            
            
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());          
            showItemsInRoom();    
        }
    }

    /**
     * Go back to the previous room
     */    
    private void back(){ 
       if(lastRoom != null){
           currentRoom = lastRoom; 
           System.out.println(currentRoom.getLongDescription());      
           showItemsInRoom();
       }    
       else{
           System.out.println("There is no way out for you..");
       }    
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
    
    /**
     * Prints all items that are in the current room
     */
    private void showItemsInRoom(){
        if(currentRoom.getItemsSize() != 0){               
            System.out.println("Items in this room: " + currentRoom.getAllItems());            
        }    
    }    
}

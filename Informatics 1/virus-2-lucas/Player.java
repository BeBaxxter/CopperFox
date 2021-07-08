import java.util.ArrayList;

/**
 * Player - this Class holds the information about the player and his inventory
 *
 * @author Lucas Carmohn
 * @version 1.0
 */
public class Player {
    private ArrayList<Item> inventory; 
    private int maxWeight = 400;
    private int currentWeight = 0;
    
   /**
    * Constructor for objects of class Player
    */
   public Player(){
       inventory = new ArrayList<Item>();
       this.currentWeight = currentWeight;
   }
    
   /**
    * setter for item
    * @param Item the item that should be added
    */
    public void setItem(Item item){
        inventory.add(item);
    }
    
   /**
    * remove the given item from list
    * @param Item the item that should be removed
    */
    public void removeItem(Item item){
        inventory.remove(item);
    }    

   /**
    * clears the iventory of the current player 
    */
   public void clearInventory(){
       inventory.clear();
   }
   
   /**
    * getter for inventory
    */
   public ArrayList getInventory(){
       return inventory;
   }

   /**
    * calculate current weight of inventory
    */
   private void calculateCurrentWeight(){
       for (Item item:inventory) {
           currentWeight = currentWeight + item.getWeight();
       }
   }    
   
   /**
    * calculate if the current item can be taken
    * @param the weight of the item
    * @return true if item can be taken
    * @return false if item can't be taken
    */
   public boolean canItemBeTaken(int weight){
       
       calculateCurrentWeight();
       
       if((currentWeight + weight) < maxWeight) {
           return true;
       } else {
           return false;
       }     
   }   
   
   /**
    * print out the current items inside the inventory with all informations
    */
   public void showInventory(){
       
       int index = 0;
       calculateCurrentWeight();
       
       System.out.println("Current weight: " + currentWeight + " / " + maxWeight);
       
       for (Item item:inventory) {
           System.out.println(index + ". " + item.getName() + ", " + item.getDescription() + ", " + item.getColour() + ", " + item.getWeight());
       }  
   }        
}

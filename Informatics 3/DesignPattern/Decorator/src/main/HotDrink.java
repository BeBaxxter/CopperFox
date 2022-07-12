package main;

/**
 * The abstract class HotDrink has a name and a price.
 * There is a method to return the name and a abstract method for returning the price.
 */
public abstract class HotDrink {
    protected int price = 0;
    protected String name = "Unknown Drink";

    /**
     * Method for getting the name
     * @return name of the HotDrink
     */
    public String getName(){
        return name;
    }

    /**
     * Method for getting the price
     * @return the price of the HotDrink implementation
     */
    public abstract int getPrice();
}

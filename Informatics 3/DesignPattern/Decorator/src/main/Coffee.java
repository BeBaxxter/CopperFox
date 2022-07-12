package main;

/**
 * The class Coffee extends the abstract class of HotDrink
 * and implements the getPrice method for the Price of a Coffee.
 */
public class Coffee extends HotDrink{

    /**
     * Sets the values for name and price.
     */
    public Coffee(){
        name = "Coffee";
        price = 60;
    }

    /**
     * Method for getting the price
     * @return the price of the coffee
     */
    @Override
    public int getPrice() {
        return price;
    }
}

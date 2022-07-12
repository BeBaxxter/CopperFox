package main;

/**
 * The class Tea extends the abstract class of HotDrink
 * and implements the getPrice method for the Price of a Tea.
 */
public class Tea extends Coffee{

    /**
     * Sets the values for name and price.
     */
    public Tea(){
        name = "Tea";
        price = 50;
    }

    /**
     * Method for getting the price
     * @return the price of the tea
     */
    @Override
    public int getPrice() {
        return price;
    }
}

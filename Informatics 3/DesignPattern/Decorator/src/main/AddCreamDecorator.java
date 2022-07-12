package main;

/**
 * This class implements the abstract class of HotDrinkDecorator for adding Cream.
 */
public class AddCreamDecorator extends HotDrinkDecorator{

    protected HotDrink hotDrink;

    /**
     * Set the HotDrink that will be decorated
     * @param hotDrink the HotDrink to be decorated
     */
    public AddCreamDecorator(HotDrink hotDrink){
        this.hotDrink = hotDrink;
    }

    /**
     * Method for getting the price
     * @return price of the HotDrink with the added Cream
     */
    @Override
    public int getPrice() {
        return hotDrink.getPrice() + 10;
    }

    /**
     * Method for getting the name
     * @return name of the HotDrink with the added Cream
     */
    @Override
    public String getName() {
        return hotDrink.getName() + " with Cream";
    }
}

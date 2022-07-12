package main;

/**
 * This abstract class implements the Patter Decorator for the HotDrink
 */
public abstract class HotDrinkDecorator extends HotDrink{

    /**
     * Abstract method for getting the name of the HotDrink and the decorator
     * @return name of the HotDrink and the decorator
     */
    public abstract String getName();
}

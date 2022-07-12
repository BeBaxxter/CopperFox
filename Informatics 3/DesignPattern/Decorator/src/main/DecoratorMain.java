package main;

/**
 * Main class for trying ot the Decorator
 */
public class DecoratorMain {

    /**
     * Main method to test the Decorator with different Decorations and for checking if everything works as expected
     * @param args none, no args needed
     */
    public static void main(String[] args){
        //Initialize a Tea and a Coffee
        HotDrink coffee1 = new Coffee();
        HotDrink tea1 = new Tea();

        //Print Price of the Tea and Coffee
        printInfo(coffee1);
        printInfo(tea1);

        //Create a decorated version with Sugar of Each
        HotDrink coffeSugar1 = new AddSugarDecorator(coffee1);
        HotDrink teaSugar1 = new AddSugarDecorator(tea1);

        //Print Price of the decorated version with Sugar of Each
        printInfo(coffeSugar1);
        printInfo(teaSugar1);

        //Print Price of the Tea and Coffee to check if nothing has changed
        printInfo(coffee1);
        printInfo(tea1);

        //Create a decorated version with Sugar of Each
        HotDrink coffeeCream1 = new AddCreamDecorator(coffee1);
        HotDrink teaCream1 = new AddCreamDecorator(tea1);

        //Print Price of the decorated version with Cream of Each
        printInfo(coffeeCream1);
        printInfo(teaCream1);

        //Print Price of the Tea and Coffee to check if nothing has changed
        printInfo(coffee1);
        printInfo(tea1);

        //Create a decorated version with Sugar AND Cream of Each
        HotDrink coffeeCreamSugar1 = new AddCreamDecorator(coffeSugar1);
        HotDrink teaCreamSugar1 = new AddCreamDecorator(teaSugar1);

        //Print Price of the decorated version with Sugar AND Cream of Each
        printInfo(coffeeCreamSugar1);
        printInfo(teaCreamSugar1);

        //Print Price of the Tea and Coffee to check if nothing has changed
        printInfo(coffee1);
        printInfo(tea1);
    }

    /**
     * Helper method for printing out the information for a HotDrink
     * @param hotDrink the HotDrink for which you want the Name and Price printed out
     */
    private static void printInfo(HotDrink hotDrink){
        System.out.println(hotDrink.getName() + " Price:" + hotDrink.getPrice());
    }
}
